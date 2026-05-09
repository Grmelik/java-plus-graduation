package ru.practicum.service;

import com.google.protobuf.Timestamp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.practicum.client.CollectorClient;
import ru.practicum.ewm.stats.proto.ActionTypeProto;
import ru.practicum.ewm.stats.proto.UserActionProto;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class CollectorActionSender {
    private final CollectorClient collectorClient;

    @Async
    public void sendRegisterActionWithRetry(Long userId, Long eventId) {
        UserActionProto action = UserActionProto.newBuilder()
                .setUserId(userId)
                .setEventId(eventId)
                .setActionType(ActionTypeProto.REGISTER)
                .setTimestamp(Timestamp.newBuilder()
                        .setSeconds(Instant.now().getEpochSecond())
                        .setNanos(Instant.now().getNano())
                        .build())
                .build();

        int maxRetries = 3;
        long delayMs = 1000;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                collectorClient.sendUserAction(action);
                log.debug("Успешная отправка регистрации в Collector: userId={}, eventId={}, попытка={}",
                        userId, eventId, attempt);
                return;
            } catch (Exception e) {
                log.warn("Ошибка отправки регистрации в Collector (попытка {}/{}): {}",
                        attempt, maxRetries, e.getMessage());

                if (attempt == maxRetries) {
                    log.error("Не удалось отправить регистрацию в Collector после {} попыток: userId={}, eventId={}",
                            maxRetries, userId, eventId, e);
                } else {
                    try {
                        Thread.sleep(delayMs * attempt);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
    }
}