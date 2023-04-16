package aero.champ.cargospot.workflow.domain.conditions;

import aero.champ.cargospot.workflow.domain.event.FieldValue;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

public class IsCondition extends Condition {

    private IsCondition(String field, Object value) {
        super(field, value);
    }

    public static IsCondition of(String field, Object value) {
        return new IsCondition(field, value);
    }

    @Override
    public boolean evaluate(AbstractEvent event) {
        FieldValue field = event.getField(field());
        if (field.isText()) {
            return field.getText().equals(value());
        }
        return field.getNumber().equals(value());
    }
}
