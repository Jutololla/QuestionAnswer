package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class DeleteVoteUseCase implements Function<String, Mono<Void>> {

    AnswerRepository answerRepository;
    UpdateAnswerUseCase updateAnswerUseCase;
    MapperUtils mapperUtils;

    public DeleteVoteUseCase(AnswerRepository answerRepository, UpdateAnswerUseCase updateAnswerUseCase, MapperUtils mapperUtils) {
        this.answerRepository = answerRepository;
        this.updateAnswerUseCase = updateAnswerUseCase;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<Void> apply(String userId){
        return answerRepository.findByQuestionId(userId)
                .flatMap(originalAnswer -> {
                    originalAnswer.removeUpVote(userId);
                    originalAnswer.removeDownVote(userId);
                    return updateAnswerUseCase.apply(mapperUtils.mapEntityToAnswer().apply(originalAnswer));
                }).then();
    }

}
