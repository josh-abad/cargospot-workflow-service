package aero.champ.cargospot.workflow.domain.condition.expression;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;

import java.util.ArrayList;
import java.util.List;

public class AndExpression extends CompositeExpression {

    public AndExpression(Expression... expressions) {
        super(expressions);
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
