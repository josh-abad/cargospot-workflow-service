package aero.champ.cargospot.workflow.domain.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EventField {

    String name();

    Type type() default Type.TEXT;

    enum Type {
        TEXT, DATE_TIME, NUMBER
    }
}
