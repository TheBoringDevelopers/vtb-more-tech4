package ru.the_boring_developers.api.service.nft.balance;

import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.math.BigDecimal;
import java.util.List;

public interface BalanceService {
    List<Nft> balanceNft(Long userInfoId);
    CoinBalanceResponse balanceCoin(Long userInfoId);
}
