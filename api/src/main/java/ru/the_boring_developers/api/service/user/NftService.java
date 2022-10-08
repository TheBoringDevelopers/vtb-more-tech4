package ru.the_boring_developers.api.service.user;

import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.math.BigDecimal;

public interface NftService {

    NftBalanceResponse balanceNft(Long userInfoId);

    CoinBalanceResponse balanceCoin(Long userInfoId);

    TransferResponse transferRuble(Long userInfoId, Long toUserInfoId, BigDecimal amount);

    TransferResponse transferMatic(Long userInfoId, Long toUserInfoId, BigDecimal amount);

    TransferResponse transferNft(Long userId, Long toUserId, Long tokenId);
}
