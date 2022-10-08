package ru.the_boring_developers.common.repository.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.the_boring_developers.common.entity.transaction.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class TransactionResultSetExtractorImpl implements TransactionResultSetExtractor {

    @Override
    public Transaction extractValue(ResultSet rs) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(rs.getLong("id"));
        transaction.setUserIdFrom(rs.getLong("user_info_id_from"));
        transaction.setUserIdTo(rs.getLong("user_info_id_to"));
        transaction.setExternalId(rs.getString("external_id"));
        transaction.setStatus(rs.getString("status"));
        transaction.setType(rs.getString("type"));
        transaction.setAmount(rs.getString("amount"));
        return transaction;
    }
}
