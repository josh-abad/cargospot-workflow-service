package aero.champ.cargospot.workflow.domain.rule;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ruleName;

    private String eventName;

    @OneToMany(mappedBy = "condition")
    private List<Condition> conditions;

    public Long getId() {
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
}
