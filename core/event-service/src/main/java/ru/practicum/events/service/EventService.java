package ru.practicum.events.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.SearchEventAdminRequest;
import ru.practicum.dto.event.SearchEventPublicRequest;
import ru.practicum.dto.event.UpdateEventAdminRequest;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.events.model.Event;

import java.util.List;

public interface EventService {
    List<EventShortDto> getEventsByOwner(Long userId, Pageable pageable);

    EventFullDto addEvent(Long userId, NewEventDto eventCreateDto);

    List<EventShortDto> allEvents(SearchEventPublicRequest request, Pageable pageable, String ip);

    EventFullDto getEventByOwner(Long userId, Long eventId, String ip);

    EventFullDto updateEvent(Long userId, Long eventId, UpdateEventUserRequest eventUpdateDto);

    EventFullDto eventById(Long evenId, String ip);

    List<EventFullDto> getEventsAdmin(SearchEventAdminRequest request, Pageable pageable);

    EventFullDto updateEventAdmin(Long eventId, UpdateEventAdminRequest updateRequest);

    Event getEventOrThrow(Long eventId);
}
