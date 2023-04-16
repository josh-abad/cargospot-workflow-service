package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.rule.Rule;
import aero.champ.cargospot.workflow.domain.event.AbstractEvent;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RuleService {

    public List<Rule> getRules() {
        return Collections.emptyList();
    }

    public void executeRulesFor(AbstractEvent event) {
        getRules().stream()
                .filter(rule -> rule.forEvent(event))
                .forEach(Rule::execute);
    }
}
