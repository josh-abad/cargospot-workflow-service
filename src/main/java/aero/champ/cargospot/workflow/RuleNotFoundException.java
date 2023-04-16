package aero.champ.cargospot.workflow;

public class RuleNotFoundException extends RuntimeException {

    public RuleNotFoundException(Long id) {
        super("Could not find rule with ID " + id);
    }
}
