package aero.champ.cargospot.workflow.domain.conditions;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

import java.math.BigDecimal;

public class GreaterThanCondition extends Condition {

    private GreaterThanCondition(String field, Object value) {
        super(field, value);
    }

    public static GreaterThanCondition of(String field, Object value) {
        return new GreaterThanCondition(field, value);
    }

    @Override
    public boolean evaluate(AbstractEvent event) {
        var field = event.getField(field());
        if (field.isNumber() && value() instanceof BigDecimal number) {
            return field.getNumber().compareTo(number) > 0;
        } else if (field.isText() && value() instanceof String text) {
            return field.getText().compareTo(text) > 0;
        }
        return false;
    }
}
