package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import aero.champ.cargospot.workflow.domain.condition.expression.SimpleExpression;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;
import aero.champ.cargospot.workflow.repository.ConditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public boolean evaluate(Condition condition, AbstractEvent event) {
        return SimpleExpression.of(condition).evaluate(event);
    }

    public boolean evaluateAll(Iterable<Condition> conditions, AbstractEvent event) {
        for (var condition : conditions) {
            if (!evaluate(condition, event)) {
                return false;
            }
        }
        return true;
    }
}
