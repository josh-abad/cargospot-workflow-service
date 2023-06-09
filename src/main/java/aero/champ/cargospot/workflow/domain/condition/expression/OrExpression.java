package aero.champ.cargospot.workflow.domain.condition.expression;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

import java.util.ArrayList;
import java.util.List;

public class OrExpression extends CompositeExpression {

    private OrExpression(Expression... expressions) {
        super(expressions);
    }

    public static OrExpression of(Expression... expressions) {
        return new OrExpression(expressions);
    }

    @Override
    public boolean evaluate(AbstractEvent event) {
        for (Expression expression : expressions()) {
            if (expression.evaluate(event)) {
                return true;
            }
        }
        return false;
    }
}
