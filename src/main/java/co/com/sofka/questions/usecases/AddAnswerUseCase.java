package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.usecases.usecasesinterfaces.SaveAnswer;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

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
