package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import aero.champ.cargospot.workflow.domain.event.*;
import aero.champ.cargospot.workflow.domain.event.fields.IField;
import aero.champ.cargospot.workflow.domain.event.fields.NumberField;
import aero.champ.cargospot.workflow.domain.event.fields.TextField;
import aero.champ.cargospot.workflow.repository.ConditionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public boolean evaluate(Condition condition, AbstractEvent event) {
        var field = event.getField(condition.getFieldName());
        var value = getValue(condition);
        return switch (condition.getOperator()) {
            case EQUALS -> field.isEquals(value);
            case NOT_EQUALS -> field.isNotEquals(value);
            case GREATER_THAN -> field.isGreaterThan(value);
            case LESS_THAN -> field.isLessThan(value);
            case IS_EMPTY -> field.isEmpty();
        };
    }

    public boolean evaluateAll(Iterable<Condition> conditions, AbstractEvent event) {
        for (var condition : conditions) {
            if (!evaluate(condition, event)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private <V> IField<V> getValue(Condition condition) {
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
