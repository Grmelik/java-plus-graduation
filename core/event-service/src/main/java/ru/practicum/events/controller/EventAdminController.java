package ru.practicum.events.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.SearchEventAdminRequest;
import ru.practicum.dto.event.UpdateEventAdminRequest;
import ru.practicum.events.service.EventService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/admin/events")
@RequiredArgsConstructor
@Validated
public class EventAdminController {
    private final EventService eventAdminService;

    @GetMapping
    public List<EventFullDto> getEventsAdmin(@ModelAttribute @Valid SearchEventAdminRequest request) {
        int size = (request.size() != null && request.size() > 0) ? request.size() : 10;
        int from = request.from() != null ? request.from() : 0;
        PageRequest pageRequest = PageRequest.of(from / size, size);
        log.debug("Controller: getEventAdmin filters={}", request);
        return eventAdminService.getEventsAdmin(request, pageRequest);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventAdmin(@PathVariable Long eventId,
                                         @Valid @RequestBody UpdateEventAdminRequest updateRequest) {
        log.info("PATCH /admin/events/{} получен запрос: {}", eventId, updateRequest);

        return eventAdminService.updateEventAdmin(eventId, updateRequest);
    }
}
