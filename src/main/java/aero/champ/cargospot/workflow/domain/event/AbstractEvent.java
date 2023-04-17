package aero.champ.cargospot.workflow.domain.event;

import aero.champ.cargospot.workflow.domain.event.fields.IField;
import aero.champ.cargospot.workflow.domain.event.fields.NumberField;
import aero.champ.cargospot.workflow.domain.event.fields.TextField;
import org.reflections.Reflections;
import org.reflections.Store;
import org.reflections.util.QueryFunction;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.reflections.ReflectionUtils.Fields;

public abstract class AbstractEvent {

    /**
     * Returns the value of the field with the given name.
     *
     * @param fieldName the name of the field to retrieve
     * @return the value of the field
     * @throws InvalidFieldNameException if the field name is not found in the event
     */
    @SuppressWarnings("unchecked")
    public <V> IField<V> getField(String fieldName) {
        var reflections = new Reflections();
        QueryFunction<Store, Field> query = Fields.of(this.getClass(), field -> field.isAnnotationPresent(EventField.class)).as(Field.class);
        Field field = reflections.get(query)
                .stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new InvalidFieldNameException(fieldName, this.getClass().getSimpleName()));
        try {
            EventField fieldAnnotation = field.getAnnotation(EventField.class);
            field.setAccessible(true);
            Object value = field.get(this);
            if (fieldAnnotation.type() == EventField.Type.NUMBER && value instanceof BigDecimal number) {
                return (IField<V>) NumberField.of(fieldName, number);
            }
            return (IField<V>) TextField.of(fieldName, (String) value);
        } catch (IllegalAccessException e) {
            throw new InvalidFieldNameException(fieldName, this.getClass().getSimpleName(), e);
        }
    }

    public abstract String eventName();
}
