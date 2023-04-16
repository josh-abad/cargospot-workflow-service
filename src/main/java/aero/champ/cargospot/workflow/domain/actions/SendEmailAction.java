package aero.champ.cargospot.workflow.domain.actions;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SendEmailAction implements Action {

    private final String message;

    private final String subject;

    private final String recipient;

    public SendEmailAction(String message, String subject, String recipient) {
        this.message = message;
        this.subject = subject;
        this.recipient = recipient;
    }

    @Override
    public void run() {
        new JavaMailSenderImpl();
    }
}
