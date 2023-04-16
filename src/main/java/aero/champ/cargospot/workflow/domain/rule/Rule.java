package aero.champ.cargospot.workflow.domain.rule;

import aero.champ.cargospot.workflow.domain.conditions.Condition;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

public class Rule {

    private final AbstractEvent event;

    private final Condition condition;

    public Rule(AbstractEvent event, Condition condition) {
        this.event = event;
        this.condition = condition;
    }

    public boolean forEvent(AbstractEvent event) {
        return this.event.eventName().equals(event.eventName());
    }

    public void execute() {
        if (condition.evaluateAll(event)) {
            System.out.println("Conditions are met");
            System.out.println("Performing actions");
        }
    }
}
