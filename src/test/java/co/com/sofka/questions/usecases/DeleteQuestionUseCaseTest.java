package co.com.sofka.questions.usecases;

import co.com.sofka.questions.reposioties.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteQuestionUseCaseTest {
    @SpyBean
    private DeleteQuestionUseCase deleteUseCase;

    @MockBean
    private QuestionRepository questionRepository;

    @Test
    void deleteQuestionSucessTest(){

        Mockito.when( questionRepository.deleteById("Q-111")).thenReturn(Mono.empty());

        var result = deleteUseCase.apply("Q-111").block();
        assertNull(result);
    }
}