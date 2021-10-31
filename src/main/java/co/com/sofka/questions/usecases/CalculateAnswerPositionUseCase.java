package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class CalculateAnswerPositionUseCase implements Function<AnswerDTO, Mono<Void>> {
    private final UpdateAnswerUseCase updateAnswerUseCase;
    private final GetAnswerUseCase getAnswerUseCase;
    private final MapperUtils mapperUtils;
    private final AnswerRepository answerRepository;

    public CalculateAnswerPositionUseCase(UpdateAnswerUseCase updateAnswerUseCase, GetAnswerUseCase getAnswerUseCase, MapperUtils mapperUtils, AnswerRepository answerRepository) {
        this.updateAnswerUseCase = updateAnswerUseCase;
        this.getAnswerUseCase = getAnswerUseCase;
        this.mapperUtils = mapperUtils;
        this.answerRepository = answerRepository;
    }


@Override
    public Mono<Void> apply(AnswerDTO answerDTO) {



    return getAnswerUseCase.apply(answerDTO.getId())
            .flatMap((originalAnswerDTO) ->{
                answerDTO.setPosition(answerDTO.getPlusVotes().size()
                        - answerDTO.getSubtractVotes().size());
                return answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO));})
            .map(mapperUtils.mapEntityToAnswer()).then();

        }


}







