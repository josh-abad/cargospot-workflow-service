package aero.champ.cargospot.workflow;

public class RuleNotFoundException extends RuntimeException {

    public RuleNotFoundException(String id) {
        super("Could not find rule with ID " + id);
    }
}
