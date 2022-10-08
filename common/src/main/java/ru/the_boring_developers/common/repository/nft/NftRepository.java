package ru.the_boring_developers.common.repository.nft;

import ru.the_boring_developers.common.entity.nft.Nft;
import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.common.repository.Repository;

import java.util.List;

public interface NftRepository extends Repository {
    Nft find(String uri);
}
