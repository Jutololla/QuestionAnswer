package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.VoteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class PlusAnswerVoteUseCase {
    private final AnswerRepository answerRepository;
    private final MapperUtils mapperUtils;
    private final VoteRepository voteRepository;
    private final GetAnswerUseCase getAnswerUseCase;
    private final UpdateAnswerUseCase updateAnswerUseCase;
    private final CalculateAnswerPositionUseCase calculateAnswerPositionUseCase;
    private final DeleteVoteUseCase deleteVoteUseCase;


    public PlusAnswerVoteUseCase(AnswerRepository answerRepository, MapperUtils mapperUtils, VoteRepository voteRepository, GetAnswerUseCase getAnswerUseCase, UpdateAnswerUseCase updateAnswerUseCase, CalculateAnswerPositionUseCase calculateAnswerPositionUseCase, DeleteVoteUseCase deleteVoteUseCase) {
        this.answerRepository = answerRepository;
        this.mapperUtils = mapperUtils;
        this.voteRepository = voteRepository;
        this.getAnswerUseCase = getAnswerUseCase;
        this.updateAnswerUseCase = updateAnswerUseCase;
        this.calculateAnswerPositionUseCase = calculateAnswerPositionUseCase;
        this.deleteVoteUseCase = deleteVoteUseCase;
    }

    public Mono<Void> apply(AnswerDTO answerDTO) {

        return deleteVoteUseCase.apply(answerDTO).then(getAnswerUseCase.apply(answerDTO.getId())
                .flatMap((originalAnswerDTO) ->{
                    BeanUtils.copyProperties(originalAnswerDTO,answerDTO);
                    answerDTO.addUpVote(answerDTO.getUserId());
                    return answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO));})
                .map(mapperUtils.mapEntityToAnswer()).then());


    }

    }


