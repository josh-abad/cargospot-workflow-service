package aero.champ.cargospot.workflow.service;

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

    private final EmailService emailService;

    public RuleService(RuleRepository ruleRepository, ConditionService conditionService, EmailService emailService) {
        this.ruleRepository = ruleRepository;
        this.conditionService = conditionService;
        this.emailService = emailService;
    }

    public void executeRulesFor(AbstractEvent event) {
        List<Rule> rules = ruleRepository.findByEventName(event.eventName());
        for (Rule rule : rules) {
            if (conditionService.evaluateAll(rule.getConditions(), event)) {
                emailService.sendEmail(
                        "joshuatimothy.abad@champ.aero",
                        "Action Executed",
                        String.format("Rule Name: %s ", rule.getRuleName())
                );
                // TODO: execute actions
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
