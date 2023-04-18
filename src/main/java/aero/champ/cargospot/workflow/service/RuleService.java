package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.actions.Action;
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

    public RuleService(RuleRepository ruleRepository, ConditionService conditionService, SendEmailAction sendEmailAction, UpdateAllocAction updateAllocAction) {
        this.ruleRepository = ruleRepository;
        this.conditionService = conditionService;
        this.sendEmailAction = sendEmailAction;
        this.updateAllocAction = updateAllocAction;
    }

    public void executeRulesFor(AbstractEvent event) {
        List<Rule> rules = ruleRepository.findByEventName(event.eventName());
        for (Rule rule : rules) {
            if (conditionService.evaluateAll(rule.getConditions(), event)) {
                if (rule.getActionName().equals(Action.SEND_EMAIL)) {
                    sendEmailAction.run(
                            "joshuatimothy.abad@champ.aero",
                            "Action Executed",
                            String.format("Rule Name: %s ", rule.getRuleName())
                    );
                } else if (rule.getActionName().equals(Action.UPDATE_ALLOC)) {
                    updateAllocAction.run("KK", (String) event.getField("id").value());
                }
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
