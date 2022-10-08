package ru.the_boring_developers.common.repository.transaction;

import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.common.entity.user.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TransactionResultSetExtractor {

    Transaction extractValue(ResultSet rs) throws SQLException;
}
