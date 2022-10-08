package ru.the_boring_developers.common.repository.wallet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.the_boring_developers.common.entity.wallet.Wallet;
import ru.the_boring_developers.common.utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class WalletResultSetExtractorImpl implements WalletResultSetExtractor {

    @Override
    public Wallet extractValue(ResultSet rs) throws SQLException {
        Wallet wallet = new Wallet();
        wallet.setId(rs.getLong("id"));
        wallet.setPublicKey(rs.getString("public_key"));
        wallet.setPrivateKey(rs.getString("private_key"));
        wallet.setUserInfoId(DbUtils.getLong(rs, "user_info_id"));
        return wallet;
    }
}
