package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class UpdateQuestionUseCaseTest {
    QuestionRepository questionRepository;
    UpdateQuestionUseCase updateUseCase;

    @BeforeEach
    public void setup(){
        MapperUtils mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        updateUseCase=new UpdateQuestionUseCase(mapperUtils,questionRepository);
    }

    @Test
    void validArgumentsTest(){
        var questionDTO = new QuestionDTO("asdf","User", "pregunta", "Type", "Category");
        var questionUpdated = new Question("asdf","UserAll", "pregunta", "Type", "Category");

        Mockito.when(questionRepository.save(any())).thenReturn(Mono.just(questionUpdated));
        StepVerifier.create(updateUseCase.apply(questionDTO))
                .expectNextMatches(element -> {
                    assert element.equals("asdf");
                    return true;
                })
                .verifyComplete();
    }

}