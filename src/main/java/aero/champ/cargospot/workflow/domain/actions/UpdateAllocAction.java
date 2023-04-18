package aero.champ.cargospot.workflow.domain.actions;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RegisteredAction(name = "Update Allocation", arguments = {"Allocation Code"})
public class UpdateAllocAction implements Action {

    @Override
    public void run(String... args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        String allocationCode = args[0];
        String id = args[1];
        new RestTemplate().put("http://localhost:9998/cargospot/api/booking/v1/bookings/update-alloc/" + id, new AllocUpdate(allocationCode));
    }
}
