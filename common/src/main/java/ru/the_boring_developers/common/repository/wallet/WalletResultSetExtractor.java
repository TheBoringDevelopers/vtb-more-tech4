package ru.the_boring_developers.common.repository.wallet;

import ru.the_boring_developers.common.entity.wallet.Wallet;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface WalletResultSetExtractor {
    Wallet extractValue(ResultSet rs) throws SQLException;
}
