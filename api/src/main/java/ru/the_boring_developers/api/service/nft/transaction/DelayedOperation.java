package ru.the_boring_developers.api.service.nft.transaction;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedOperation implements Delayed {

    private static final List<Long> delayList = Arrays.asList(
            30000L,     // 1-ый запрос через 0:30 после регистрации перевода
            10000L,     // 2-ой запрос через 0:40 после регистрации перевода, 0:10 после 1-го запроса
            10000L,     // 3-ий запрос через 0:50 после регистрации перевода, 0:10 после 2-го запроса
            10000L,     // 4-ый запрос через 1:00 после регистрации перевода, 0:10 после 3-го запроса
            30000L,     // 5-ый запрос через 1:30 после регистрации перевода, 0:30 после 4-го запроса
            30000L,     // 6-ой запрос через 2:00 после регистрации перевода, 0:30 после 5-го запроса
            30000L,     // 7-ой запрос через 2:30 после регистрации перевода, 0:30 после 6-го запроса
            30000L,     // 8-ой запрос через 3:00 после регистрации перевода, 0:30 после 7-го запроса
            60000L,     // 9-ый запрос через 4:00 после регистрации перевода, 1:00 после 8-го запроса
            60000L,     // 10-ый запрос через 5:00 после регистрации перевода, 1:00 после 9-го запроса
            300000L,    // 11-ый запрос через 10:00 после регистрации перевода, 5:00 после 10-го запроса
            300000L,    // 12-ый запрос через 15:00 после регистрации перевода, 5:00 после 11-го запроса
            300000L,    // 13-ый запрос через 20:00 после регистрации перевода, 5:00 после 12-го запроса
            300000L,    // 14-ый запрос через 25:00 после регистрации перевода, 5:00 после 13-го запроса
            300000L     // 15-ый запрос через 30:00 после регистрации перевода, 5:00 после 14-го запроса
    );

    private static final long operationValidSeconds = delayList.stream()
            .map(delay -> delay / 1000) // Переводим в секунды
            .reduce(Long::sum)        // Суммируем все задержки
            .orElse(1800L);

    public static OffsetDateTime getExpirationTime() {
        return OffsetDateTime.now().plusSeconds(operationValidSeconds);
    }

    private final String hash;
    private final Long id;
    private int attempt;
    private long startTime;

    public static DelayedOperation getDelayedOperation(Long id, String hash) {
        return new DelayedOperation(id, hash, false, 0);
    }

    private DelayedOperation(Long id,
                             String hash,
                             boolean once,
                             int attempt) {
        this.id = id;
        this.hash = hash;
        if (attempt < delayList.size()) {
            this.attempt = attempt;
            startTime = once ? System.currentTimeMillis() : System.currentTimeMillis() + delayList.get(attempt);
        }
    }

    public String getHash() {
        return hash;
    }

    public boolean hasNext() {
        return attempt + 1 < delayList.size();
    }

    public DelayedOperation getNext() {
        if (!hasNext()) {
            throw new RuntimeException("Количество попыток выполнения задачи превышено");
        }
        attempt++;
        startTime += delayList.get(attempt);
        return this;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.startTime, ((DelayedOperation) o).startTime);
    }
}
