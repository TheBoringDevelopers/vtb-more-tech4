package ru.the_boring_developers.api.service.nft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.the_boring_developers.api.rest.vtb.VtbRestTemplate;
import ru.the_boring_developers.api.service.nft.balance.BalanceService;
import ru.the_boring_developers.api.service.nft.transaction.TransactionService;
import ru.the_boring_developers.common.entity.nft.Currency;
import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.nft.NftBuyRequest;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;
import ru.the_boring_developers.common.entity.wallet.Wallet;
import ru.the_boring_developers.common.exception.DomainException;
import ru.the_boring_developers.common.repository.nft.NftRepository;
import ru.the_boring_developers.common.repository.wallet.WalletRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NftServiceImpl implements NftService {

    private final NftRepository nftRepository;
    private final VtbRestTemplate vtbRestTemplate;
    private final WalletRepository walletRepository;
    private final BalanceService balanceService;
    private final TransactionService transactionService;

    @Override
    public List<Nft> findAll(String type) {
        return nftRepository.findList(type);
    }

    @Override
    public TransferResponse buy(NftBuyRequest request) {
        Nft nft = nftRepository.find(request.getUri());
        Wallet userWallet = walletRepository.find(request.getUserId());
        CoinBalanceResponse coinBalanceResponse = balanceService.balanceCoin(request.getUserId());
        validate(request.getCurrency(), nft, coinBalanceResponse);
        Wallet mainWallet = walletRepository.findMain();
        BigDecimal amount = Currency.RUBLE == request.getCurrency() ? BigDecimal.valueOf(nft.getRublePrice()) : nft.getMaticPrice();
        TransferResponse nftTransferResponse = vtbRestTemplate.generateNft(userWallet.getPublicKey(), nft.getUri());
        TransferResponse rubleTransferResponse = vtbRestTemplate.transferRuble(userWallet.getPrivateKey(), mainWallet.getPublicKey(), amount);
        transactionService.create(request.getCurrency(), nft.getUri(),
                null, request.getUserId(), nftTransferResponse);
        transactionService.create(request.getCurrency(), Currency.RUBLE == request.getCurrency() ? nft.getRublePrice().toString() : nft.getMaticPrice().toString(),
                null, request.getUserId(), rubleTransferResponse);
        return nftTransferResponse;
    }

    private void validate(Currency currency, Nft nft, CoinBalanceResponse balance) {
        if (currency == Currency.RUBLE) {
            if (nft.getRublePrice() == null) {
                throw new DomainException("Невозможно купить за рубли");
            }
            if (BigDecimal.valueOf(nft.getRublePrice()).compareTo(balance.getCoinsAmount()) < 0) {
                throw new DomainException("Недостаточно средств");
            }
        }
        if (currency == Currency.MATIC) {
            if (nft.getMaticPrice() == null) {
                throw new DomainException("Невозможно купить за Matic");
            }
            if (nft.getMaticPrice().compareTo(balance.getMaticAmount()) < 0) {
                throw new DomainException("Недостаточно средств");
            }
        }
    }
}
