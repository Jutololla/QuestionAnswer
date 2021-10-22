package co.com.sofka.questions.email;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class EmailController {

    public void sendMail(String toEmail, String toName, String question) throws IOException {
        String text =readFileToString("email.html");
        text=text.replace("QUESTION",question);

        Email email = EmailBuilder.startingBlank()
                .from("Testor Aurelio", "testingservice151526@outlook.com")
                .to(toName, toEmail)
                .withSubject("Answer received")
                .withHTMLText(text)

//                .withPlainText("You've received an answer to your question "+question)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.office365.com", 587, "testingservice151526@outlook.com", "Testing15152626")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .buildMailer();

        mailer.sendMail(email);
    }

    public static String readFileToString(String path) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);

        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            return "";
        }
    }

}