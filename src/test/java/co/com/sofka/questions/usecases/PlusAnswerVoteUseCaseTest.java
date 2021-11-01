package co.com.sofka.questions.usecases;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@SpringBootTest
class PlusAnswerVoteUseCaseTest {
//    AnswerRepository answerRepository;
//    PlusAnswerVoteUseCase plusAnswerVoteUseCase;
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