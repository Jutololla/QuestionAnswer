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

public class EmailSender {
    Mailer mailer;

    public EmailSender(Mailer mailer) {
        this.mailer = mailer;
    }

    public String sendMail(String toEmail, String toName, String question, String questionPath) {
        String text = readFileToString("email.html");
        text = text.replace("QUESTION", question);
        String questionLink = "https://questionanswer-e95e5.web.app".concat(questionPath);
//        String questionLink = "http://localhost:3000".concat(questionPath);
        text = text.replace("LINKTOWEBPAGE", questionLink);


        Email email = EmailBuilder.startingBlank()
                .from("Testor Aurelio", "testingservice151526@outlook.com")
                .to(toName, toEmail)
                .withSubject("Answer received")
                .withHTMLText(text)
                .buildEmail();


        mailer.sendMail(email);
        return "OK";
    }

    private static String readFileToString(String path) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);

        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            return "";
        }
    }

}
