package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.request.RequestStatus;
import ru.practicum.repository.RequestRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/internal/requests")
@RequiredArgsConstructor
public class InternalRequestController {

    private final RequestRepository requestRepository;

    @GetMapping("/event/{eventId}/count/{status}")
    public Long countByStatus(@PathVariable Long eventId, @PathVariable RequestStatus status) {
        return requestRepository.countByEventIdAndStatus(eventId, status);
    }

    @PostMapping("/events/count/confirmed")
    public Map<Long, Long> getConfirmedRequestsCount(@RequestBody List<Long> eventIds) {
        List<Object[]> results = requestRepository.countConfirmedByEventIds(eventIds);
        return results.stream()
                .collect(Collectors.toMap(
                        row -> (Long) row[0],
                        row -> (Long) row[1]
                ));
    }
}
