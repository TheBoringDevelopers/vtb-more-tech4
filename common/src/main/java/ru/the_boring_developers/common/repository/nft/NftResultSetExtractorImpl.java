package ru.the_boring_developers.common.repository.nft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.common.repository.transaction.TransactionResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class NftResultSetExtractorImpl implements NftResultSetExtractor {

    @Override
    public Nft extractValue(ResultSet rs) throws SQLException {
        Nft nft = new Nft();
        nft.setUri(rs.getString("uri"));
        nft.setName(rs.getString("name"));
        nft.setMaticPrice(rs.getBigDecimal("matic_price"));
        nft.setImageUrl(rs.getString("image_url"));
        return nft;
    }
}
