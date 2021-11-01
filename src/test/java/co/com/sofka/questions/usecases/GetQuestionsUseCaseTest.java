package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetQuestionsUseCaseTest {

    QuestionRepository questionRepository;
    AnswerRepository answerRepository;
    GetQuestionsUseCase getUseCase;
    MapperUtils mapperUtils;

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
        List<Answer> answer = new ArrayList<>();
        answer.add(new Answer("id","userId","questionId","answer",3));
        answer.add(new Answer("id2","userId","questionId","answer",3));
        answer.add(new Answer("id3","userId","questionId","answer",3));
        List<AnswerDTO> answerDTO = new ArrayList<>();
        answerDTO.add(new AnswerDTO("id","userId","questionId","answer",3));
        answerDTO.add(new AnswerDTO("id2","userId","questionId","answer",3));
        answerDTO.add(new AnswerDTO("id3","userId","questionId","answer",3));

        when(questionRepository.findById(anyString())).thenReturn(Mono.just(question));
        when(answerRepository.findAllByQuestionId("asdfg")).thenReturn(Flux.fromIterable(answer));

        StepVerifier.create(getUseCase.apply("asdfg"))
                .expectNextMatches(questionDTO -> {
                    assert questionDTO.getUserId().equals("xxxx-xxxx");
                    assert questionDTO.getCategory().equals("software");
                    assert questionDTO.getQuestion().equals("¿Que es java?");
                    assert questionDTO.getType().equals("tech");
                    assert questionDTO.getId().equals("asdfg");
                    assert questionDTO.getAnswers().equals(answerDTO);
                    return true;
                });
    }

}