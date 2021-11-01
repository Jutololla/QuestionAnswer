package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetAnswerUseCase implements Function<String, Mono<AnswerDTO>> {
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;

    public GetAnswerUseCase(AnswerRepository answerRepository, MapperUtils mapperUtils) {
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<AnswerDTO> apply(String answerId){
        return answerRepository.findById(answerId)
                .map(mapperUtils.mapperToAnswerDTO());
    }
}
