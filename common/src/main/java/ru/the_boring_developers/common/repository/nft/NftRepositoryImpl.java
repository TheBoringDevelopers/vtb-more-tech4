package ru.the_boring_developers.common.repository.nft;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.the_boring_developers.common.entity.nft.Nft;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NftRepositoryImpl implements NftRepository {

    private final JdbcOperations jdbcOperations;
    private final NftResultSetExtractor nftResultSetExtractor;

    @Override
    public Nft find(String uri) {
        return jdbcOperations.query(
                "SELECT * FROM nft WHERE uri = ?",
                ps -> {
                    int idx = 0;
                    ps.setString(++idx, uri);
                },
                rs -> rs.next() ? nftResultSetExtractor.extractValue(rs) : null);
    }

    @Override
    public List<Nft> findList(String type) {
        return jdbcOperations.query("SELECT * FROM nft WHERE type = ?",
                ps -> {
                    int idx = 0;
                    ps.setString(++idx, type);
                }, (rs, rowNum) -> nftResultSetExtractor.extractValue(rs));
    }
}
