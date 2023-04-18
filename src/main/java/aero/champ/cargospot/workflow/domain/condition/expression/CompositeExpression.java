package aero.champ.cargospot.workflow.domain.condition.expression;

import java.util.ArrayList;
import java.util.List;

abstract class CompositeExpression implements Expression {

    private final List<Expression> expressions = new ArrayList<>();

    public CompositeExpression(Expression... expressions) {
        this.expressions.addAll(List.of(expressions));
    }

    protected List<Expression> expressions() {
        return new ArrayList<>(expressions);
    }
}
