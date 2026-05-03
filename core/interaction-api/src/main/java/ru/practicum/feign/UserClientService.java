package ru.practicum.feign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.dto.user.UserShortDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserClientService {

    private final UserClient userClient;

    public UserShortDto getUser(Long userId) {
        log.info("Вызов feign-клиента User (userId={})", userId);
        try {
            UserShortDto result = userClient.getUser(userId);
            log.debug("Ответ от feign-клиента User (userId={}) -> user={}", userId, result.getName());
            return result;
        } catch (Exception e) {
            log.error("Ошибка с feign-клиентом User (userId={}) failed: {}", userId, e.getMessage());
            throw e;
        }
    }
}