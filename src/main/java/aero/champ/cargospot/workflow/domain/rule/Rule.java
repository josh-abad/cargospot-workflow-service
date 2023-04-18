package aero.champ.cargospot.workflow.domain.rule;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    @Id
    private String id;

    private String ruleName;

    private String eventName;

    private String actionName;

    private List<Condition> conditions;

    public String getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public String getEventName() {
        return eventName;
    }

    public List<Condition> getConditions() {
        return new ArrayList<>(conditions);
    }

    public String getActionName() {
        return actionName;
    }
}
