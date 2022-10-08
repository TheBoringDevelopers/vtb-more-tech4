package ru.the_boring_developers.common.repository.transaction;

import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.common.entity.user.UserInfo;
import ru.the_boring_developers.common.repository.Repository;

import java.util.List;

public interface TransactionRepository extends Repository {
    Transaction find(String hash);
    List<Transaction> find(Long userInfoId);
    List<Transaction> findNotFinished();
    Long create(Transaction transaction);
    void update(Long transactionId, String status);
}
