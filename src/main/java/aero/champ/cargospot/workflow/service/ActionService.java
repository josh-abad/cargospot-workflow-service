package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.actions.dto.ActionDto;
import aero.champ.cargospot.workflow.processor.ActionProcessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

    private final ActionProcessor actionProcessor;

    public ActionService(ActionProcessor actionProcessor) {
        this.actionProcessor = actionProcessor;
    }

    public List<ActionDto> getAllActions() {
        return actionProcessor.findAllActions();
    }
}
