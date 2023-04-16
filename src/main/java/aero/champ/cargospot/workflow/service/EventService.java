package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.event.dto.EventDto;
import aero.champ.cargospot.workflow.processor.EventProcessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventProcessor eventProcessor;

    public EventService(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    public List<EventDto> getAllEvents() {
        return eventProcessor.findAllEvents();
    }
}
