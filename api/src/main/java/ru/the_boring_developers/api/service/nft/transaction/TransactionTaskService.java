package ru.the_boring_developers.api.service.nft.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.the_boring_developers.api.rest.vtb.VtbRestTemplate;
import ru.the_boring_developers.common.entity.vtb_api.transaction.TransactionStatusResponse;
import ru.the_boring_developers.common.repository.transaction.TransactionRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionTaskService implements DisposableBean {

    /** Исполнитель фоновой задачи переодической проверки статуса операции */
    private final ExecutorService transactionExecutor = Executors.newSingleThreadExecutor();

    /** Очередь запланированных задач проверки статуса операции */
    private final DelayQueue<DelayedOperation> transactionQueue = new DelayQueue<>();

    private final TransactionRepository transactionRepository;
    private final VtbRestTemplate vtbRestTemplate;

    /** Метод, вызывающийся после инициализации контекста, для поднятия фоновой задачи проверки статуса операции */
    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        List<DelayedOperation> transactions = transactionRepository.findNotFinished()
                .stream()
                .map(it -> DelayedOperation.getDelayedOperation(it.getId(), it.getExternalId()))
                .collect(Collectors.toList());
        transactionQueue.addAll(transactions);

        transactionExecutor.execute(() -> {
            log.info("Задача синхронизации статусов начата");
            if (!transactionQueue.isEmpty()) {
                log.info(transactionQueue.size() + " транзакций в очереди");
            }
            try {
                while (!Thread.interrupted()) {
                    DelayedOperation operation = transactionQueue.poll(1, TimeUnit.SECONDS);
                    if (operation != null) {
                        log.info(String.format("Начинаем задачу с id=%s hash=%s", operation.getId(), operation.getHash()));
                        try {
                            TransactionStatusResponse response = vtbRestTemplate.getTransactionStatus(operation.getHash());
                            if (!Objects.equals(response.getStatus(), "Pending")) {
                                transactionRepository.update(operation.getId(), response.getStatus());
                                continue;
                            }
                        } catch (Exception e) {
                            log.error(String.format("Ошибка обработки транзакции hash=%s %s", operation.getHash(), e.getMessage()));
                        }
                        if (operation.hasNext()) {
                            transactionQueue.add(operation.getNext());
                        } else {
                            String error = "Операция отклонена по таймауту";
                            transactionRepository.update(operation.getId(), error);
                        }
                    }
                }
            } catch (InterruptedException e) {
                log.info("Задача синхранизации транзакций остановлена");
            }
        });
    }

    @Override
    public void destroy() {
        transactionExecutor.shutdown();
    }

    public void addTransaction(Long id, String hash) {
        transactionQueue.add(DelayedOperation.getDelayedOperation(id, hash));
    }
}
