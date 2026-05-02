package ru.practicum.feign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.dto.event.EventFullDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventClientService {

    private final EventClient eventClient;

    public EventFullDto getEvent(Long eventId) {
        log.info("Вызов feign-клиента Event (eventId={})", eventId);
        try {
            EventFullDto result = eventClient.getEvent(eventId);
            log.debug("Ответ от feign-клиента Event (eventId={}) -> eventId={}, title={}",
                    eventId, result.getId(), result.getTitle());
            return result;
        } catch (Exception e) {
            log.error("Ошибка с feign-клиентом Event (eventId={}): {}", eventId, e.getMessage());
            throw e;
        }
    }
}