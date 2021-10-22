package co.com.sofka.questions.routers;

import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.EmailDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QuestionRouter {

    @Bean
    public RouterFunction<ServerResponse> getAll(ListUseCase listUseCase) {
        return route(GET("/getAll"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(), QuestionDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getOwnerAll(OwnerListUseCase ownerListUseCase) {
        return route(
                GET("/getOwnerAll/{userId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                ownerListUseCase.apply(request.pathVariable("userId")),
                                QuestionDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateQuestion(UpdateQuestionUseCase updateQuestionUseCase) {
        return route(
                PUT("/updatequestion").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(QuestionDTO.class)
                        .flatMap(questionDTO -> updateQuestionUseCase.apply(questionDTO)
                                        .flatMap(result->ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(result))
        ));
    }

    @Bean
    public RouterFunction<ServerResponse> updateAnswer(UpdateAnswerUseCase updateAnswerUseCase) {
        return route(
                PUT("/updateanswer").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(answerDTO -> updateAnswerUseCase.apply(answerDTO)
                                .flatMap(result->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        ));
    }

    @Bean
    public RouterFunction<ServerResponse> create(CreateQuestionUseCase createQuestionUseCase) {
        Function<QuestionDTO, Mono<ServerResponse>> executor = questionDTO -> createQuestionUseCase.apply(questionDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));
        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(QuestionDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> get(GetQuestionsUseCase getQuestionsUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getQuestionsUseCase.apply(
                                        request.pathVariable("id")),
                                QuestionDTO.class
                        ))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> addAnswer(AddAnswerUseCase addAnswerUseCase) {
        return route(POST("/add").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(AnswerDTO.class)
                        .flatMap(addAnswerDTO -> addAnswerUseCase.apply(addAnswerDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> sendEmail(SendEmailUseCase sendEmailUseCase){
        return route(
                POST("/sendemail/").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(EmailDTO.class)
                        .flatMap(sendEmailUseCase::apply)
                        .flatMap(result->ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> delete(DeleteQuestionUseCase deleteQuestionUseCase) {
        return route(
                DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteQuestionUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteAnswer(DeleteAnswerUseCase deleteAnswerUseCase) {
        return route(
                DELETE("/deleteanswer/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher((deleteAnswerUseCase.apply(request.pathVariable("id"))), Void.class))
        );
    }
}
