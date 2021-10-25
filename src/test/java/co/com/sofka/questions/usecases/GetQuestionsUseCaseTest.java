package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetQuestionsUseCaseTest {

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    GetQuestionsUseCase getUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        answerRepository = mock(AnswerRepository.class);
        questionRepository=mock(QuestionRepository.class);
        getUseCase = new GetQuestionsUseCase(mapperUtils,questionRepository,answerRepository);
    }

    @Test
    void validParametersTest(){
        var question =  new Question();
        question.setUserId("xxxx-xxxx");
        question.setType("tech");
        question.setCategory("software");
        question.setQuestion("¿Que es java?");
        question.setId("asdfg");
        when(questionRepository.findById(anyString())).thenReturn(Mono.just(question));

        StepVerifier.create(getUseCase.apply("asdfg"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getType().equals("tech");
                    assert questionDTO.getId().equals("asdfg");
                    return true;
                });
    }

}