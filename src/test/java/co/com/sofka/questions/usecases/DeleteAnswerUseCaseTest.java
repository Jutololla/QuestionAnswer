package co.com.sofka.questions.usecases;

import co.com.sofka.questions.repositories.AnswerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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