package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.actions.SendEmailAction;
import aero.champ.cargospot.workflow.domain.actions.UpdateAllocAction;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;
import aero.champ.cargospot.workflow.domain.rule.Rule;
import aero.champ.cargospot.workflow.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    private final ConditionService conditionService;

    private final SendEmailAction sendEmailAction;

    private final UpdateAllocAction updateAllocAction;

    private final ActionService actionService;

    public RuleService(RuleRepository ruleRepository, ConditionService conditionService, SendEmailAction sendEmailAction, UpdateAllocAction updateAllocAction, ActionService actionService) {
        this.ruleRepository = ruleRepository;
        this.conditionService = conditionService;
        this.sendEmailAction = sendEmailAction;
        this.updateAllocAction = updateAllocAction;
        this.actionService = actionService;
    }

    public void executeRulesFor(AbstractEvent event) {
        List<Rule> rules = ruleRepository.findByEventName(event.eventName());
        for (Rule rule : rules) {
            if (conditionService.evaluateAll(rule.getConditions(), event)) {
                actionService.executeActionsFor(rule);
            }
        }
    }

    public Rule createRule(Rule newRule) {
        return ruleRepository.save(newRule);
    }

    public Iterable<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public Optional<Rule> getRule(String id) {
        return ruleRepository.findById(id);
    }

    public void deleteRule(String id) {
        ruleRepository.deleteById(id);
    }
}
