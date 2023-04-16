package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;
import aero.champ.cargospot.workflow.domain.event.FieldValue;
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

    private FieldValue getValue(Condition condition) {
        if (condition.getType() == Condition.Type.NUMBER) {
            return FieldValue.ofNumber(condition.getFieldName(), new BigDecimal(condition.getValue()));
        }
        return FieldValue.ofText(condition.getFieldName(), condition.getValue());
    }
}
