package aero.champ.cargospot.workflow.domain.event;

@Event(name = "Flight is updated")
public class FlightUpdatedEvent extends AbstractEvent {

    @EventField(name = "Flight Number")
    private final String flightNumber;

    public FlightUpdatedEvent(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String eventName() {
        return "Flight is updated";
    }
}
