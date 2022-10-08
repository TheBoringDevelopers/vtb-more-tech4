package ru.the_boring_developers.api.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.the_boring_developers.api.rest.vtb.VtbRestTemplate;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;
import ru.the_boring_developers.common.repository.wallet.WalletRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class NftServiceImpl implements NftService {

    private final VtbRestTemplate vtbRestTemplate;
    private final WalletRepository walletRepository;

    @Override
    public NftBalanceResponse balanceNft(Long userInfoId) {
        return vtbRestTemplate.balanceNft(walletRepository.find(userInfoId).getPublicKey());
    }

    @Override
    public CoinBalanceResponse balanceCoin(Long userInfoId) {
        return vtbRestTemplate.balanceCoins(walletRepository.find(userInfoId).getPublicKey());
    }

    @Override
    public TransferResponse transferRuble(Long userInfoId, Long toUserInfoId, BigDecimal amount) {
        return vtbRestTemplate.transferRuble(walletRepository.find(userInfoId).getPrivateKey(), walletRepository.find(toUserInfoId).getPublicKey(), amount);
    }

    @Override
    public TransferResponse transferMatic(Long userInfoId, Long toUserInfoId, BigDecimal amount) {
        return vtbRestTemplate.transferMatic(walletRepository.find(userInfoId).getPrivateKey(), walletRepository.find(toUserInfoId).getPublicKey(), amount);
    }

    @Override
    public TransferResponse transferNft(Long userId, Long toUserId, Long tokenId) {
        return vtbRestTemplate.transferNft(walletRepository.find(userId).getPrivateKey(), walletRepository.find(toUserId).getPublicKey(), tokenId);
    }
}
