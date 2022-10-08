package ru.the_boring_developers.api.service.nft.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.the_boring_developers.common.entity.transaction.Transaction;
import ru.the_boring_developers.common.entity.transaction.TransactionType;
import ru.the_boring_developers.common.entity.vtb_api.transfer.TransferResponse;
import ru.the_boring_developers.common.repository.transaction.TransactionRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionTaskService transactionTaskService;

    @Override
    public List<Transaction> find(Long userId) {
        return transactionRepository.find(userId);
    }

    @Override
    public Long create(TransactionType transactionType, BigDecimal value, Long userIdFrom, Long userIdTo, TransferResponse transferResponse) {
        if (transferResponse.getTransactionHash() == null) {
            return null;
        }
        Long id = transactionRepository.create(Transaction.builder()
                .externalId(transferResponse.getTransactionHash())
                .userIdFrom(userIdFrom)
                .userIdTo(userIdTo)
                .status("Pending")
                .type(transactionType.name())
                .amount(String.valueOf(value))
                .build());
        transactionTaskService.addTransaction(id, transferResponse.getTransactionHash());
        return id;
    }
}
