package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.event.AbstractEvent;
import aero.champ.cargospot.workflow.domain.rule.Rule;
import aero.champ.cargospot.workflow.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    private final ConditionService conditionService;

    public RuleService(RuleRepository ruleRepository, ConditionService conditionService) {
        this.ruleRepository = ruleRepository;
        this.conditionService = conditionService;
    }

    public void executeRulesFor(AbstractEvent event) {
        List<Rule> rules = ruleRepository.findByEventName(event.eventName());
        for (Rule rule : rules) {
            if (conditionService.evaluateAll(rule.getConditions(), event)) {
                // TODO: execute actions
            }
        }
    }
}
