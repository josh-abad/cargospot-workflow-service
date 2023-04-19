package aero.champ.cargospot.workflow.domain.rule;

import aero.champ.cargospot.workflow.domain.actions.ActionForm;
import aero.champ.cargospot.workflow.domain.condition.Condition;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Rule {

    @Id
    private String id;

    private String ruleName;

    private String eventName;

    private List<ActionForm> actions;

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

    public List<ActionForm> getActions() {
        return new ArrayList<>(actions);
    }
}
