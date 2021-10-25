package co.com.sofka.questions.usecases;

import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAnswerUseCaseTest {

    @SpyBean
    private DeleteAnswerUseCase deleteAnswerUseCase;

    @MockBean
    private AnswerRepository answerRepository;

    @Test
    void deleteQuestionSuccessTest(){
        answerRepository = mock(AnswerRepository.class);
        deleteAnswerUseCase = new DeleteAnswerUseCase(answerRepository);
        Mockito.when( answerRepository.deleteById("Q-111")).thenReturn(Mono.empty());

        var result = deleteAnswerUseCase.apply("Q-111").block();
        assertNull(result);
    }

}