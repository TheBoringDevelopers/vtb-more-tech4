package ru.the_boring_developers.api.service.nft;

import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.nft.NftBuyRequest;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.util.List;

public interface NftService {
    List<Nft> findAll(String type);
    TransferResponse buy(NftBuyRequest request);
}
