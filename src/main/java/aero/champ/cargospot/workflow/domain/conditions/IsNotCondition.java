package aero.champ.cargospot.workflow.domain.conditions;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

public class IsNotCondition extends Condition {

    private final Condition isCondition;

    private IsNotCondition(String field, Object value) {
        super(field, value);
        this.isCondition = IsCondition.of(field, value);
    }

    public static IsNotCondition of(String field, Object value) {
        return new IsNotCondition(field, value);
    }

    @Override
    public boolean evaluate(AbstractEvent event) {
        return !isCondition.evaluate(event);
    }
}
