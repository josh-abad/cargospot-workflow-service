package aero.champ.cargospot.workflow.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

@Event(name = "Booking is created")
public class CreateBookingEvent extends AbstractEvent {

    @JsonProperty("awbNumber")
    @EventField(name = "AWB Number")
    private final String awbNumber;

    @JsonProperty("weight")
    @EventField(name = "AWB Weight", type = EventField.Type.NUMBER)
    private final BigDecimal weight;

    @JsonProperty("volume")
    @EventField(name = "AWB Volume", type = EventField.Type.NUMBER)
    private BigDecimal volume;

    @JsonProperty("shc")
    @EventField(name = "AWB SHC")
    private final String shc;

    @JsonProperty("priority")
    @EventField(name = "Priority")
    private String priority;

    @JsonProperty("allocationCode")
    @EventField(name = "Allocation Code")
    private String allocationCode;

    @JsonProperty("flightDepartureTime")
    @EventField(name = "Flight Departure Date", type = EventField.Type.DATE_TIME)
    private Instant flightDepartureTime;

    @JsonProperty("origin")
    @EventField(name = "Origin")
    private String origin;

    @JsonProperty("destination")
    @EventField(name = "Destination")
    private String destination;

    @JsonProperty("flightCapacity")
    @EventField(name = "Flight Capacity", type = EventField.Type.NUMBER)
    private BigDecimal flightCapacity;

    @JsonProperty("id")
    private int id;

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

    public BigDecimal getFlightCapacity() {
        return flightCapacity;
    }

    public void setFlightCapacity(BigDecimal flightCapacity) {
        this.flightCapacity = flightCapacity;
    }

    public int getId() {
        return id;
    }
}
