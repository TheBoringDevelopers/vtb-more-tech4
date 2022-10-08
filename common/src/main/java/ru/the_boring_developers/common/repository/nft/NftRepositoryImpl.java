package ru.the_boring_developers.common.repository.nft;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.repository.transaction.TransactionResultSetExtractor;

@Repository
@RequiredArgsConstructor
public class NftRepositoryImpl implements NftRepository {

    private final JdbcOperations jdbcOperations;
    private final TransactionResultSetExtractor transactionResultSetExtractor;

    @Override
    public Nft find(String uri) {
        return null;
    }
}
