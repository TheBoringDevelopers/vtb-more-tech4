package ru.the_boring_developers.common.repository.wallet;


import ru.the_boring_developers.common.entity.wallet.Wallet;
import ru.the_boring_developers.common.repository.Repository;

public interface WalletRepository extends Repository {
    Wallet findMain();
    Wallet find(Long userInfoId);
}
