package aero.champ.cargospot.workflow.processor;

import aero.champ.cargospot.workflow.domain.event.Event;
import aero.champ.cargospot.workflow.domain.event.EventField;
import aero.champ.cargospot.workflow.domain.event.dto.EventDto;
import aero.champ.cargospot.workflow.domain.event.dto.EventFieldDto;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.reflections.ReflectionUtils.Fields;
import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

@Component
public class EventProcessor {

    private final List<EventDto> events = new ArrayList<>();

    private boolean initialized = false;

    /**
     * Finds all events in the classpath
     *
     * <p>
     * This method uses reflection to find all classes annotated with {@link Event} and
     * all fields annotated with {@link EventField} in those classes. It then creates
     * a list of {@link EventDto} objects from the annotations. This list is cached
     * and returned on subsequent calls.
     *
     * @return a list of {@link EventDto} objects
     */
    public List<EventDto> findAllEvents() {
        if (!initialized) {
            events.addAll(initializeEvents());
            initialized = true;
        }
        return new ArrayList<>(events);
    }

    private List<EventDto> initializeEvents() {
        var reflections = new Reflections("aero.champ.cargospot.workflow.domain.event");
        var subTypes = reflections.get(SubTypes.of(TypesAnnotated.with(Event.class)).asClass());
        List<EventDto> events = new ArrayList<>();
        for (var subType : subTypes) {
            Set<Field> fields = reflections.get(Fields.of(subType, field -> field.isAnnotationPresent(EventField.class)).as(Field.class));
            Event event = subType.getAnnotation(Event.class);
            List<EventFieldDto> eventFields = fields.stream().map(field -> {
                EventField eventField = field.getAnnotation(EventField.class);
                return new EventFieldDto(field.getName(), eventField.name(), eventField.type());
            }).toList();
            events.add(new EventDto(event.name(), eventFields));
        }

        return events;
    }
}
