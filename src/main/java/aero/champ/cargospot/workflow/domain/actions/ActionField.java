package aero.champ.cargospot.workflow.domain.actions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionField {

    String name();

    Type type() default Type.TEXT;

    enum Type {
        TEXT,
        NUMBER
    }
}
