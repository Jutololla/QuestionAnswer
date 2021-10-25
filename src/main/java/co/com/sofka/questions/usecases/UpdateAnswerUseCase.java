package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateAnswerUseCase {
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;

    public UpdateAnswerUseCase(AnswerRepository answerRepository, MapperUtils mapperUtils) {
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
    }

    public Mono<String> apply(AnswerDTO answerDTO){
        Objects.requireNonNull(answerDTO.getId(), "Id of the answer is required");
        return answerRepository
                .save(mapperUtils.mapperToAnswer().apply(answerDTO))
                .map(Answer::getId);
    }
}
