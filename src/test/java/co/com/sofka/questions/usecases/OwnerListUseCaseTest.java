package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OwnerListUseCaseTest {
    QuestionRepository questionRepository;
    MapperUtils mapperUtils;
    OwnerListUseCase ownerListUseCase;

    @BeforeEach
    public void setup(){
        mapperUtils = new MapperUtils();
        questionRepository = mock(QuestionRepository.class);
        ownerListUseCase = new OwnerListUseCase(mapperUtils,questionRepository);
    }

    @Test
    void validParametersTest(){
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("id","userId","type","software","category"));
        questionList.add(new Question("id2","userId","type","software","category"));
        questionList.add(new Question("id3","userId3","type","software","category"));
        questionList.add(new Question("id3","userId2","type","software","category"));

        List<QuestionDTO> questionListReturn = new ArrayList<>();
        questionListReturn.add(new QuestionDTO("id","userId","type","software","category"));
        questionListReturn.add(new QuestionDTO("id2","userId","type","software","category"));

        Mockito.when(questionRepository.findByUserId("userId")).thenReturn(Flux.fromIterable(questionList));

        StepVerifier.create(ownerListUseCase.apply("userId"))
                .expectNextMatches(element->{
                    assert element.equals(questionListReturn);
                    return true;
                });
    }

}