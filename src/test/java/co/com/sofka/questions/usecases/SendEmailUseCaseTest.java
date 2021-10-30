package co.com.sofka.questions.usecases;

import co.com.sofka.questions.email.EmailController;
import co.com.sofka.questions.model.EmailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SendEmailUseCaseTest {
    EmailController email;
    SendEmailUseCase sendEmailUseCase;

    @BeforeEach
    public void setup(){


        sendEmailUseCase = new SendEmailUseCase();

    }

    @Test
    void sendMailTest() throws IOException {
        EmailDTO emailDTO = new EmailDTO("testingservice151526@outlook.com",
                "Peblito", "Ejemplo pregunta", "facebook.com");

        when(email.sendMail(any(),any(),any(),any())).thenReturn("OK test");


        StepVerifier.create(sendEmailUseCase.apply(emailDTO)).expectNextMatches(
                string->{
                    assert string.equals("mail sent");
                    return true;
                }).verifyComplete();



    }

}