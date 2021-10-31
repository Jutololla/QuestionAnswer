package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlusAnswerVoteUseCaseTest {
//    AnswerRepository answerRepository;
//    PlusAnswerVoteUseCase plusAnswerVoteUseCase;
//    VoteRepository voteRepository;
//
//    @BeforeEach
//    public void setup(){
//        MapperUtils mapperUtils = new MapperUtils();
//        answerRepository = mock(AnswerRepository.class);
//        plusAnswerVoteUseCase = new PlusAnswerVoteUseCase(answerRepository,mapperUtils, voteRepository, getAnswerUseCase, updateAnswerUseCase, calculateAnswerPositionUseCase);
//
//    }
//
//    @Test
//    void validArgumentsTest(){
//        Answer answer = new Answer("asdf","qwer","pregunta id",
//                "respuesta ejemplo",1);
//        AnswerDTO answerDTO = new AnswerDTO("asdf","qwer","pregunta id",
//                "respuesta ejemplo",1);
//        when(answerRepository.save(any())).thenReturn(Mono.just(answer));
//
//        StepVerifier.create(plusAnswerVoteUseCase.apply(answerDTO)).expectNextMatches(
//                element->{
//                    assert element.equals("asdf");
//                    return true;
//                }
//        ).verifyComplete();
//    }

}