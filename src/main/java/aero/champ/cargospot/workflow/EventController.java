package aero.champ.cargospot.workflow;

import aero.champ.cargospot.workflow.domain.event.CreateBookingEvent;
import aero.champ.cargospot.workflow.domain.event.EventHandler;
import aero.champ.cargospot.workflow.domain.event.dto.EventDto;
import aero.champ.cargospot.workflow.service.EmailService;
import aero.champ.cargospot.workflow.service.EventService;
import aero.champ.cargospot.workflow.service.RuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final RuleService ruleService;

    private final EventService eventService;

    private final EmailService emailService;

    public EventController(RuleService ruleService, EventService eventService, EmailService emailService) {
        this.ruleService = ruleService;
        this.eventService = eventService;
        this.emailService = emailService;
    }

    @CrossOrigin
    @GetMapping
    public List<EventDto> getEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/email")
    public void emailTest() {
        emailService.sendEmail("joshuatimothy.abad@gmail.com", "Test", "Test");
    }

    @EventHandler(event = CreateBookingEvent.class)
    @PostMapping
    public void handleCreateBookingEvent(@RequestBody CreateBookingEvent event) {
        ruleService.executeRulesFor(event);
    }
}
