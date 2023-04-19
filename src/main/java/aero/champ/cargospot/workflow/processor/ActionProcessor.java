package aero.champ.cargospot.workflow.processor;

import aero.champ.cargospot.workflow.domain.actions.ActionForm;
import aero.champ.cargospot.workflow.domain.actions.RegisteredAction;
import aero.champ.cargospot.workflow.domain.actions.dto.ActionDto;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

@Component
public class ActionProcessor {

    private final ApplicationContext _appContext;

    private final List<ActionDto> events = new ArrayList<>();

    private boolean initialized = false;

    public ActionProcessor(ApplicationContext appContext) {
        _appContext = appContext;
    }

    public void executeAction(ActionForm action) {
        var reflections = new Reflections("aero.champ.cargospot.workflow.domain.actions");
        var subTypes = reflections.get(SubTypes.of(TypesAnnotated.with(RegisteredAction.class)).asClass());
        for (var subType : subTypes) {
            RegisteredAction annotation = subType.getAnnotation(RegisteredAction.class);
            if (annotation.name().equals(action.getName())) {
                try {
                    Object bean = _appContext.getBean(subType.getName());
                    Method runMethod = subType.getDeclaredMethod("run", String[].class);
                    runMethod.invoke(bean, (Object) action.getParameters());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("Could not find run method on action.", e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Could not execute run method on action.", e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Could not access run method on action.", e);
                }
            }
        }
    }

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
