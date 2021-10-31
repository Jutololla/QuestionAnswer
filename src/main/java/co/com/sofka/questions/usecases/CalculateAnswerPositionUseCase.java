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

//    Flux<Integer> getPositions(String questionId)
//        {
//            return  getQuestionsUseCase.apply(questionId).map(QuestionDTO::getAnswers)
//                    .map(answerDTOS -> answerDTOS.stream().map(answerDTO ->
//                            answerDTO.getPlusVotes().size() - answerDTO.getSubtractVotes().size()
//                    ))
//                    .map( scores ->
//                    {
//                        var originalScores = scores.collect(Collectors.toList());
//                        var scoresSorted = originalScores.stream().sorted(Comparator.reverseOrder())
//                                .collect(Collectors.toList());
//                        return originalScores.stream().map(scoresSorted::indexOf);
//                    }).flatMapMany(Flux::fromStream);
//        }

@Override
    public Mono<Void> apply(AnswerDTO answerDTO) {

//        answerRepository.findAllByQuestionId(answerDTO.getQuestionId())
//                .map(answer -> {
//                    answer.removeDownVote(answerDTO.getUserId());
//                    answer.removeUpVote(answerDTO.getUserId());
//                    return updateAnswerUseCase.apply(mapperUtils.mapEntityToAnswer().apply(answer));
//                });


//        getQuestionsUseCase.apply(questionId).map(questionDTO -> {
//            mapperUtils.mapperToQuestion(questionId).apply(questionDTO))
//                    .map(answer -> {
//                                answer.removeUpVote(userId);
//                                answer.removeDownVote(userId);
//                                return answer;
//                            }
//                    ));


//            return getAnswerUseCase.apply(answerDTO.getId())
//                    .map(answerDTO2 -> {
//                        answerDTO2.setPosition(answerDTO2.getPlusVotes().size()
//                                - answerDTO2.getSubtractVotes().size());
//                        return updateAnswerUseCase.apply(answerDTO2);
//                    }).then();

    return getAnswerUseCase.apply(answerDTO.getId())
            .flatMap((originalAnswerDTO) ->{
                answerDTO.setPosition(answerDTO.getPlusVotes().size()
                        - answerDTO.getSubtractVotes().size());
                return answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO));})
            .map(mapperUtils.mapEntityToAnswer()).then();

//            return getQuestionsUseCase.apply(questionId).map(QuestionDTO::getAnswers)
//                    .flatMapMany(Flux::fromIterable)
//                    .zipWith(getPositions(questionId))
//                    .map(dtoIntegerTuple ->
//                    {
//                        var answerDTO = dtoIntegerTuple.getT1();
//                        var position = dtoIntegerTuple.getT2();
//                        answerDTO.setPosition(position);
//                        return answerDTO;
//                    }).map(answerDTO ->
//                    {
//                        updateAnswerUseCase.apply(answerDTO).subscribe();
//                        return answerDTO;
//                    }).then();

        }


}







