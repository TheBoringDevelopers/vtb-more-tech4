package ru.the_boring_developers.api.service.nft.transaction;

import ru.the_boring_developers.common.entity.nft.Currency;
import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<Transaction> find(Long userId);
    Long create(Currency currency, String value, Long userIdFrom, Long userIdTo, TransferResponse transferResponse);
}
