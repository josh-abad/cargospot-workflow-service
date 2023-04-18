package aero.champ.cargospot.workflow.domain.condition.expression;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

import java.util.ArrayList;
import java.util.List;

public interface Expression {

    boolean evaluate(AbstractEvent event);

    static List<Expression> from(Condition condition) {
        List<Expression> expressions = new ArrayList<>();
        Expression last = null;
        while (condition.hasNext()) {
            Expression curr = SimpleExpression.of(condition);
            switch (condition.getRelation()) {
                case AND:
                    expressions.add(new AndExpression(SimpleExpression.of(condition), SimpleExpression.of(condition.getNext())));
                case OR:
                    expressions.add(new OrExpression(SimpleExpression.of(condition), SimpleExpression.of(condition.getNext())));
            }
            condition = condition.getNext();
        }
        return expressions;
    }
}
