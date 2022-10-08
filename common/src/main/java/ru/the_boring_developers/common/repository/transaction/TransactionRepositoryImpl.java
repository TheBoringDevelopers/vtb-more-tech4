package ru.the_boring_developers.common.repository.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.the_boring_developers.common.entity.transaction.Transaction;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final JdbcOperations jdbcOperations;
    private final TransactionResultSetExtractor transactionResultSetExtractor;

    @Override
    public Transaction find(String hash) {
        return jdbcOperations.query(
                "SELECT * FROM transaction WHERE external_id = ?",
                ps -> {
                    int idx = 0;
                    ps.setString(++idx, hash);
                },
                rs -> rs.next() ? transactionResultSetExtractor.extractValue(rs) : null);
    }

    @Override
    public List<Transaction> find(Long userInfoId) {
        return jdbcOperations.query("SELECT * FROM transaction WHERE user_info_id_from = ?",
                ps -> {
                    int idx = 0;
                    ps.setLong(++idx, userInfoId);
                }, (rs, rowNum) -> transactionResultSetExtractor.extractValue(rs)
        );
    }

    @Override
    public List<Transaction> findNotFinished() {
        return jdbcOperations.query("SELECT * FROM transaction WHERE status = ?",
                ps -> {
                    int idx = 0;
                    ps.setString(++idx, "Pending");
                }, (rs, rowNum) -> transactionResultSetExtractor.extractValue(rs)
        );
    }

    @Override
    public Long create(Transaction transaction) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
            final PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO transaction (user_info_id_from, user_info_id_to, external_id, status, type, amount)" +
                            " VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            int idx = 0;
            ps.setLong(++idx, transaction.getUserIdFrom());
            ps.setLong(++idx, transaction.getUserIdTo());
            ps.setString(++idx, transaction.getExternalId());
            ps.setString(++idx, transaction.getStatus());
            ps.setString(++idx, transaction.getType());
            ps.setString(++idx, transaction.getAmount());
            return ps;
        }, keyHolder);
        return (Long) Objects.requireNonNull(keyHolder.getKeys()).get("id");
    }

    @Override
    public void update(Long transactionId, String status) {
        jdbcOperations.update("UPDATE transaction SET status = ? WHERE id = ?",
                ps -> {
                    int idx = 0;
                    ps.setString(++idx, status);
                    ps.setLong(++idx, transactionId);
                });
    }
}
