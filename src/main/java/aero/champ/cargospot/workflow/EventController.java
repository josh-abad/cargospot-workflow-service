package aero.champ.cargospot.workflow;

import aero.champ.cargospot.workflow.domain.event.CreateBookingEvent;
import aero.champ.cargospot.workflow.domain.event.EventHandler;
import aero.champ.cargospot.workflow.domain.event.dto.EventDto;
import aero.champ.cargospot.workflow.service.EventService;
import aero.champ.cargospot.workflow.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final RuleService ruleService;

    private final EventService eventService;

    public EventController(RuleService ruleService, EventService eventService) {
        this.ruleService = ruleService;
        this.eventService = eventService;
    }

    @CrossOrigin
    @GetMapping
    public List<EventDto> getEvents() {
        return eventService.getAllEvents();
    }

    @EventHandler(event = CreateBookingEvent.class)
    @PostMapping
    public void handleCreateBookingEvent(@RequestBody CreateBookingEvent event) {
        ruleService.executeRulesFor(event);
    }
}
