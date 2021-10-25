package co.com.sofka.questions.usecases.usecasesinterfaces;

import co.com.sofka.questions.email.EmailController;
import co.com.sofka.questions.model.QuestionDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveQuestion {
    Mono<String> apply(@Valid QuestionDTO questionDTO);


}