package aero.champ.cargospot.workflow.domain.conditions;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

public abstract class Condition {

    private final String field;

    private final Object value;

    private Condition next;

    public Condition(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public Condition and(Condition next) {
        this.next = next;
        return next;
    }

    public String field() {
        return field;
    }

    public Object value() {
        return value;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Condition next() {
        return next;
    }

    public abstract boolean evaluate(AbstractEvent event);

    public boolean evaluateAll(AbstractEvent event) {
        if (evaluate(event)) {
            if (hasNext()) {
                return next().evaluateAll(event);
            }
            return true;
        }
        return false;
    }
}
