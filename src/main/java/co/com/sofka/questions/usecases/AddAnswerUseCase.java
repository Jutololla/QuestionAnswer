package co.com.sofka.questions.usecases;

import co.com.sofka.questions.email.EmailController;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

@Service
@Validated
public class AddAnswerUseCase implements SaveAnswer {
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final GetQuestionsUseCase getQuestionsUseCase;

    public AddAnswerUseCase(MapperUtils mapperUtils, GetQuestionsUseCase getQuestionsUseCase, AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
        this.getQuestionsUseCase = getQuestionsUseCase;
        this.mapperUtils = mapperUtils;
    }

    public Mono<QuestionDTO> apply(AnswerDTO answerDTO){

//        EmailController email = new EmailController();
//        try {
//            email.sendMail("julianangulop@gmail.com","Julian","Pregunta1","WWW.google.com");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Objects.requireNonNull(answerDTO.getQuestionId(), "Id of the answer is required");
        return getQuestionsUseCase.apply(answerDTO.getQuestionId()).flatMap(question ->
                answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO))
                        .map(answer -> {
                            question.getAnswers().add(answerDTO);
                            return question;
                        })
        );
    }

}
