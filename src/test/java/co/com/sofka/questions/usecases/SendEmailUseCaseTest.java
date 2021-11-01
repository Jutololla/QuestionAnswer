package co.com.sofka.questions.usecases;

import co.com.sofka.questions.email.EmailSender;
import co.com.sofka.questions.model.EmailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import reactor.test.StepVerifier;


import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SendEmailUseCaseTest {
    private EmailSender email;
    private SendEmailUseCase sendEmailUseCase;

    @BeforeEach
    public void setup(){
        sendEmailUseCase = new SendEmailUseCase();
    }

    @Test
    void sendMailTest() throws IOException {
        EmailDTO emailDTO = new EmailDTO("testingservice151526@outlook.com",
                "Peblito", "Ejemplo pregunta", "facebook.com");

        StepVerifier.create(sendEmailUseCase.apply(emailDTO)).expectNextMatches(
                string->{
                    assert string.equals("mail sent");
                    return true;
                }).verifyComplete();



    }

}