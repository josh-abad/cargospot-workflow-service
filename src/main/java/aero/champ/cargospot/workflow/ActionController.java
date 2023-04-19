package aero.champ.cargospot.workflow;

import aero.champ.cargospot.workflow.domain.actions.dto.ActionDto;
import aero.champ.cargospot.workflow.service.ActionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
public class ActionController {

    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @CrossOrigin
    @GetMapping
    public List<ActionDto> getActions() {
        return actionService.getAllActions();
    }

}
