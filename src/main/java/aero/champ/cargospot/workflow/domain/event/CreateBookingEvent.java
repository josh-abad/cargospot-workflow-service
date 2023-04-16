package aero.champ.cargospot.workflow.domain.event;

import java.math.BigDecimal;

@Event(id = 1, name = "Booking is created")
public class CreateBookingEvent extends AbstractEvent {

    @EventField(name = "AWB Number")
    private final String awbNumber;

    @EventField(name = "AWB Weight", type = EventField.Type.NUMBER)
    private final BigDecimal weight;

    @EventField(name = "AWB SHC")
    private final String shc;

    public CreateBookingEvent(String awbNumber, BigDecimal weight, String shc) {
        this.awbNumber = awbNumber;
        this.weight = weight;
        this.shc = shc;
    }

    @Override
    public String eventName() {
        return "Booking is created";
    }
}
