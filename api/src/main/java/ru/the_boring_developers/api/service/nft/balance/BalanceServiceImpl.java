package ru.the_boring_developers.api.service.nft.balance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.the_boring_developers.api.rest.vtb.VtbRestTemplate;
import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;
import ru.the_boring_developers.common.repository.nft.NftRepository;
import ru.the_boring_developers.common.repository.wallet.WalletRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final VtbRestTemplate vtbRestTemplate;
    private final WalletRepository walletRepository;
    private final NftRepository nftRepository;

    @Override
    public List<Nft> balanceNft(Long userInfoId) {
        return vtbRestTemplate.balanceNft(
                walletRepository.find(userInfoId).getPublicKey()
        ).getBalance().stream()
                .map(responseItem -> {
                    Nft nft = nftRepository.find(responseItem.getURI());
                    nft.setTokens(responseItem.getTokens());
                    return nft;
                }).collect(Collectors.toList());
    }

    @Override
    public CoinBalanceResponse balanceCoin(Long userInfoId) {
        return vtbRestTemplate.balanceCoins(walletRepository.find(userInfoId).getPublicKey());
    }
}
