package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetAnswerUseCaseTest {
    AnswerRepository answerRepository;
    MapperUtils mapperUtils;
    GetAnswerUseCase getAnswerUseCase;

    @BeforeEach
    public void setup(){
        mapperUtils = new MapperUtils();
        answerRepository = mock(AnswerRepository.class);
        getAnswerUseCase = new GetAnswerUseCase(answerRepository,mapperUtils);
    }

    @Test
    void validArgumentsTest(){
        Answer answer = new Answer("id","userId","questionId","answerString",1);
        AnswerDTO answerDTO = mapperUtils.mapperToAnswerDTO().apply(answer);

        when(answerRepository.findById("id")).thenReturn(Mono.just(answer));

        StepVerifier.create(getAnswerUseCase.apply("id"))
                .expectNextMatches(element -> {
                    assert element.equals((answerDTO));
                    return true;
                })
                .verifyComplete();
    }

}