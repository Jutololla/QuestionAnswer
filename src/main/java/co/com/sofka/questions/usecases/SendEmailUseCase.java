package co.com.sofka.questions.usecases;

import co.com.sofka.questions.email.EmailController;
import co.com.sofka.questions.model.EmailDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
@Validated
public class SendEmailUseCase {

    public Mono<String> apply(EmailDTO emailDTO){
        EmailController email = new EmailController();
        email.sendMail(emailDTO.getToEmail(),emailDTO.getToName(),emailDTO.getQuestion(),emailDTO.getQuestionLink());
        return Mono.just("mail sent");
    }

}
