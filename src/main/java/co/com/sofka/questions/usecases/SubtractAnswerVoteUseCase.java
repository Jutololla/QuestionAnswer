package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class SubtractAnswerVoteUseCase {
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final VoteRepository voteRepository;
    private final GetAnswerUseCase getAnswerUseCase;
    private final UpdateAnswerUseCase updateAnswerUseCase;
    private final CalculateAnswerPositionUseCase calculateAnswerPositionUseCase;


    public SubtractAnswerVoteUseCase(AnswerRepository answerRepository, MapperUtils mapperUtils, VoteRepository voteRepository, GetAnswerUseCase getAnswerUseCase, UpdateAnswerUseCase updateAnswerUseCase, CalculateAnswerPositionUseCase calculateAnswerPositionUseCase) {
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
        this.voteRepository = voteRepository;
        this.getAnswerUseCase = getAnswerUseCase;
        this.updateAnswerUseCase = updateAnswerUseCase;
        this.calculateAnswerPositionUseCase = calculateAnswerPositionUseCase;
    }

    public Mono<Void> apply(AnswerDTO answerDTO) {
//        AtomicReference<Integer> value = null;
//
//        answerRepository.findById(answerDTO.getId()).map((answer ->{
//            System.out.println("answer = " + answer);
////            value.set(answer.getPosition() - answerDTO.getPosition());
//            assert false;
//            value.set(answer.getPosition() - answerDTO.getPosition());
//            return null;
//        } ));


//        Vote vote = new Vote(answerDTO.getQuestionId(),answerDTO.getId(), answerDTO.getUserId(), answerDTO.getPosition());
//
//        voteRepository.findByQuestionIdAndUserId(answerDTO.getQuestionId(),answerDTO.getUserId())
//                        .flatMap(element->{
//                            if(Objects.isNull(element)){
//                                voteRepository.save(vote);
//                            }
//                            else{
//                                vote.setId(element.getId());
//                                voteRepository.save(vote);
//                                answerRepository.findByUserIdAndQuestionId(answerDTO.getUserId(),answerDTO.getQuestionId())
//                                        .filter((answer -> answer.contains(vote.getId())))
//                                        .flatMap((answer -> {
//                                            answer.setPosition(answer.getPosition()-value.get());
//                                            return saveAnswer(mapperUtils.mapEntityToAnswer().apply(answer));
//                                        }));
//                            }
//                            return saveAnswer(answerDTO);
//                        });
//        return Mono.just("OK");
//    }
//
//    private Mono<String> saveAnswer(AnswerDTO answerDTO){
//    Objects.requireNonNull(answerDTO.getId(), "Id of the answer is required");
//        return answerRepository
//                .save(mapperUtils.mapperToAnswer().apply(answerDTO))
//            .map(Answer::getId);
//        }


//    return getAnswerUseCase.apply(answerDTO.getId())
//            .map(i->mapperUtils.mapperToAnswer().apply(i))
//            .map(answer -> {
//                answer.addDownVote(answerDTO.getUserId());
//                answer.removeUpVote(answerDTO.getUserId());
//                return answer;
//            })
//            .map(i->mapperUtils.mapEntityToAnswer().apply(i))
//            .flatMap(updateAnswerUseCase)
////            .map(AnswerDTO::getQuestionId)
//            .flatMap(calculateAnswerPositionUseCase);
        answerRepository.findAllByQuestionId(answerDTO.getQuestionId())
                .map(answer -> {
                    answer.removeDownVote(answerDTO.getUserId());
                    answer.removeUpVote(answerDTO.getUserId());
                    return updateAnswerUseCase.apply(mapperUtils.mapEntityToAnswer().apply(answer));
                });

        getAnswerUseCase.apply(answerDTO.getId())
                .map(answerDTO1 -> {
                    answerDTO1.addDownVote(answerDTO1.getUserId());
                    return updateAnswerUseCase.apply(answerDTO1);
                }).then();

        

        return Mono.empty();
    }

}
