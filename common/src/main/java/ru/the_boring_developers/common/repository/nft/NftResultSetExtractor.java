package ru.the_boring_developers.common.repository.nft;

import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.transaction.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface NftResultSetExtractor {

    Nft extractValue(ResultSet rs) throws SQLException;
}
