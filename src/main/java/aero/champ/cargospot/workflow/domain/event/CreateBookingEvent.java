package aero.champ.cargospot.workflow.domain.event;

import java.math.BigDecimal;
import java.time.Instant;

@Event(name = "Booking is created")
public class CreateBookingEvent extends AbstractEvent {

    @EventField(name = "AWB Number")
    private final String awbNumber;

    @EventField(name = "AWB Weight", type = EventField.Type.NUMBER)
    private final BigDecimal weight;

    @EventField(name = "AWB Volume", type = EventField.Type.NUMBER)
    private BigDecimal volume;

    @EventField(name = "AWB SHC")
    private final String shc;

    @EventField(name = "Priority")
    private String priority;

    @EventField(name = "Allocation Code")
    private String allocationCode;

    @EventField(name = "Flight Departure Date", type = EventField.Type.DATE_TIME)
    private Instant flightDepartureTime;

    @EventField(name = "Origin")
    private String origin;

    @EventField(name = "Destination")
    private String destination;

    public CreateBookingEvent(String awbNumber, BigDecimal weight, String shc) {
        this.awbNumber = awbNumber;
        this.weight = weight;
        this.shc = shc;
    }

    @Override
    public String eventName() {
        return "Booking is created";
    }

    public void setAllocationCode(String allocationCode) {
        this.allocationCode = allocationCode;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
