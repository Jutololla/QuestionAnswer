package co.com.sofka.questions.usecases;

import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlusAnswerVoteUseCaseTest {
    AnswerRepository answerRepository;
    MapperUtils mapperUtils;
    GetAnswerUseCase getAnswerUseCase;
    DeleteVoteUseCase deleteVoteUseCase;
    PlusAnswerVoteUseCase plusAnswerVoteUseCase;
    UpdateAnswerUseCase updateAnswerUseCase;

    @BeforeEach
    public void setup() {
        answerRepository = mock(AnswerRepository.class);
        mapperUtils = new MapperUtils();
        getAnswerUseCase = new GetAnswerUseCase(answerRepository, mapperUtils);
        updateAnswerUseCase = new UpdateAnswerUseCase(mapperUtils, answerRepository, getAnswerUseCase);
        deleteVoteUseCase = new DeleteVoteUseCase(answerRepository, updateAnswerUseCase, mapperUtils);
        plusAnswerVoteUseCase = new PlusAnswerVoteUseCase(answerRepository, mapperUtils, getAnswerUseCase,
                deleteVoteUseCase);
    }

    @Test
    public void voteUp() {
//        var answer = new AnswerDTO("qqqq", "uuuu", "answer1","xxxx",3);
//        var question =  new QuestionDTO();
//        question.setId("qqqq");
//
//        when(answerRepository.findById("xxxx")).thenReturn(Mono.just(answer).map(mapperUtils.mapperToAnswer()));
//        when(answerRepository.save(Mockito.any())).thenReturn(Mono.just(answer).map(mapperUtils.mapperToAnswer()));
//        when(questionRepository.findById("qqqq")).thenReturn(Mono.just(question).map(mapperUtils.mapperToQuestion(question.getId())));
//        when(answerRepository.findAllByQuestionId("qqqq")).thenReturn(Flux.just(answer).map(mapperUtils.mapperToAnswer()));
//        var answerVotedUp = new VoteUpUseCase(getAnswerUseCase,updateAnswerUseCase, calculateAnswerPositionsUseCase, mapperUtils);
//
//        StepVerifier.create(answerVotedUp.apply("xxxx", "uuuu"))
//                .verifyComplete();
    }

}




