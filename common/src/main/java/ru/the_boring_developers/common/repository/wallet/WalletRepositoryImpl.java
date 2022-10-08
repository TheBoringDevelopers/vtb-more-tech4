package ru.the_boring_developers.common.repository.wallet;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.the_boring_developers.common.entity.wallet.Wallet;

@Repository
@RequiredArgsConstructor
public class WalletRepositoryImpl implements WalletRepository {

    private final JdbcOperations jdbcOperations;
    private final WalletResultSetExtractor walletResultSetExtractor;

    @Override
    public Wallet findMain() {
        return jdbcOperations.query(
                "SELECT * FROM wallet WHERE user_info_id IS NULL",
                rs -> rs.next() ? walletResultSetExtractor.extractValue(rs) : null);
    }

    @Override
    public Wallet find(Long userInfoId) {
        return jdbcOperations.query(
                "SELECT * FROM wallet WHERE user_info_id = ?",
                ps -> {
                    int idx = 0;
                    ps.setLong(++idx, userInfoId);
                },
                rs -> rs.next() ? walletResultSetExtractor.extractValue(rs) : null);
    }
}
