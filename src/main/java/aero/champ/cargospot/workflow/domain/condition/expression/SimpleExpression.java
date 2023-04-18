package aero.champ.cargospot.workflow.domain.condition.expression;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;
import aero.champ.cargospot.workflow.domain.event.fields.IField;
import aero.champ.cargospot.workflow.domain.event.fields.NumberField;
import aero.champ.cargospot.workflow.domain.event.fields.TextField;

import java.math.BigDecimal;

public class SimpleExpression implements Expression {

    private final Condition condition;

    private SimpleExpression(Condition condition) {
        this.condition = condition;
    }

    public static SimpleExpression of(Condition condition) {
        return new SimpleExpression(condition);
    }

    @Override
    public boolean evaluate(AbstractEvent event) {
        var field = event.getField(condition.getFieldName());
        var value = getValue(condition, event);
        return switch (condition.getOperator()) {
            case EQUALS -> field.isEquals(value);
            case NOT_EQUALS -> field.isNotEquals(value);
            case GREATER_THAN -> field.isGreaterThan(value);
            case LESS_THAN -> field.isLessThan(value);
            case IS_EMPTY -> field.isEmpty();
        };
    }

    @SuppressWarnings("unchecked")
    private <V> IField<V> getValue(Condition condition, AbstractEvent event) {
        if (condition.isValueIsField()) {
            return event.getField(condition.getValue());
        }
        if (condition.getType() == Condition.Type.NUMBER) {
            BigDecimal value = BigDecimal.ZERO;
            if (!condition.getValue().isBlank()) {
                value = new BigDecimal(condition.getValue());
            }
            return (IField<V>) NumberField.of(condition.getFieldName(), value);
        }
        return (IField<V>) TextField.of(condition.getFieldName(), condition.getValue());
    }
}
