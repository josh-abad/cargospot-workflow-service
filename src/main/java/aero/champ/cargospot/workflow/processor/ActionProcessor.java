package aero.champ.cargospot.workflow.processor;

import aero.champ.cargospot.workflow.domain.actions.RegisteredAction;
import aero.champ.cargospot.workflow.domain.actions.dto.ActionDto;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

@Component
public class ActionProcessor {

    private final List<ActionDto> events = new ArrayList<>();

    private boolean initialized = false;

    public List<ActionDto> findAllActions() {
        if (!initialized) {
            events.addAll(initializeEvents());
            initialized = true;
        }
        return new ArrayList<>(events);
    }

    private List<ActionDto> initializeEvents() {
        var reflections = new Reflections("aero.champ.cargospot.workflow.domain.actions");
        var subTypes = reflections.get(SubTypes.of(TypesAnnotated.with(RegisteredAction.class)).asClass());
        List<ActionDto> actions = new ArrayList<>();
        for (var subType : subTypes) {
            RegisteredAction action = subType.getAnnotation(RegisteredAction.class);
            actions.add(new ActionDto(action.name(), action.arguments()));
        }

        return actions;
    }
}
