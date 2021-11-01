package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class UpdateAnswerUseCaseTest {
    AnswerRepository answerRepository;
    MapperUtils mapperUtils;
    GetAnswerUseCase getAnswerUseCase;
    UpdateAnswerUseCase updateAnswerUseCase;

    @BeforeEach
    public void setup(){
        mapperUtils=new MapperUtils();
        answerRepository=mock(AnswerRepository.class);
        getAnswerUseCase=mock(GetAnswerUseCase.class);
        updateAnswerUseCase = new UpdateAnswerUseCase(mapperUtils,answerRepository,getAnswerUseCase);
    }

    @Test
    void validParametersTest(){
        AnswerDTO answerDTO = new AnswerDTO("id","userId","questionId","answer",3);
        Answer answer = new Answer("id","userId","questionId","answer",3);
        AnswerDTO answerDTOReturn = new AnswerDTO("id","userId","questionId","answer",3);



        Mockito.when(getAnswerUseCase.apply("id")).thenReturn(Mono.just(answerDTO));
        Mockito.when(answerRepository.save(mapperUtils.mapperToAnswer().apply(answerDTO))).thenReturn(Mono.just(answer));

        StepVerifier.create(updateAnswerUseCase.apply(answerDTO))
                .expectNextMatches(answerDTO1 -> {
                    assert answerDTO1.equals(answerDTOReturn);
                    return true;
                });

    }

}