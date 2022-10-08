package ru.the_boring_developers.api.service.nft.transfer;

import ru.the_boring_developers.common.entity.transaction.TransactionType;
import ru.the_boring_developers.common.entity.vtb_api.balance.coin.CoinBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.balance.nft.NftBalanceResponse;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferRequest;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.math.BigDecimal;

public interface TransferService {

    TransferResponse transfer(TransactionType transactionType, Long userInfoId, Long toUserInfoId, BigDecimal value);
}
