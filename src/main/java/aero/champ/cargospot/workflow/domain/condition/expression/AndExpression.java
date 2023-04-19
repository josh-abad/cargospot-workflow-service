package aero.champ.cargospot.workflow.domain.condition.expression;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

public class AndExpression extends CompositeExpression {

    private AndExpression(Expression... expressions) {
        super(expressions);
    }

    public static AndExpression of(Expression... expressions) {
        return new AndExpression(expressions);
    }

    @Override
    public boolean evaluate(AbstractEvent event) {
        for (Expression expression : expressions()) {
            if (!expression.evaluate(event)) {
                return false;
            }
        }
        return true;
    }
}
