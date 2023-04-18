package aero.champ.cargospot.workflow.domain.actions;

import aero.champ.cargospot.workflow.service.EmailService;
import org.springframework.stereotype.Component;

@Component
@RegisteredAction(name = "Send Email", arguments = {"Message", "Subject", "Recipient"})
public class SendEmailAction implements Action {

    private final EmailService emailService;

    public SendEmailAction(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        String message = args[0];
        String subject = args[1];
        String recipient = args[2];
        emailService.sendEmail(recipient, subject, message);
    }
}
