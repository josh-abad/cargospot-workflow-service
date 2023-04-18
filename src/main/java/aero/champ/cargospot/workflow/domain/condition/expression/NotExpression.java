package aero.champ.cargospot.workflow.domain.condition.expression;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

public class NotExpression implements Expression {

    private final Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean evaluate(AbstractEvent event) {
        return !expression.evaluate(event);
    }
}
