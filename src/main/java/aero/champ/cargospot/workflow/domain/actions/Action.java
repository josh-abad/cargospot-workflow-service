package aero.champ.cargospot.workflow.domain.actions;

public interface Action {

    public static final String SEND_EMAIL = "Send Email";

    public static final String UPDATE_ALLOC = "Update Allocation";

    void run(String... args);
}
