package co.com.sofka.questions.usecases;

import co.com.sofka.questions.email.EmailSender;
import co.com.sofka.questions.model.EmailDTO;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class SendEmailUseCase {

    private final Mailer mailer=MailerBuilder
            .withSMTPServer("smtp.office365.com", 587, "testingservice151526@outlook.com", "Testing15152626")
            .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .buildMailer();

    public Mono<String> apply(EmailDTO emailDTO){
        EmailSender email = new EmailSender(mailer);
        email.sendMail(emailDTO.getToEmail(),emailDTO.getToName(),emailDTO.getQuestion(),emailDTO.getQuestionLink());
        return Mono.just("mail sent");
    }

}
