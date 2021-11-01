package co.com.sofka.questions.usecases;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.mapper.MapperUtils;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
class DeleteVoteUseCaseTest {
//    @Autowired
//    AnswerRepository answerRepository;
//    UpdateAnswerUseCase updateAnswerUseCase;
//    MapperUtils mapperUtils;
//    DeleteVoteUseCase deleteVoteUseCase;
//
//    @BeforeEach
//    public void setup() {
//        answerRepository=mock(AnswerRepository.class);
//        deleteVoteUseCase = new DeleteVoteUseCase(answerRepository, updateAnswerUseCase
//                , mapperUtils);
//
//    }
//        @Test
//        void validArgumentsTest(){
//
//        AnswerDTO answerDTO = new AnswerDTO("id", "userId", "questionId", "answer",
//                3);
//            answerDTO.getPlusVotes().addAll(Arrays.asList("UserId", "1", "2"));
//            answerDTO.getSubtractVotes().addAll(Arrays.asList("3", "4"));
//
//            List<Answer> answerList = new ArrayList<>();
//            answerList.add(0, new Answer("id", "userId", "questionId", "answer", 3));
//            answerList.get(0).getPlusVotes().addAll(Arrays.asList("UserId", "1", "2"));
//            answerList.get(0).getSubtractVotes().addAll(Arrays.asList("3", "4"));
//            answerList.add(1, new Answer("id2", "userId", "questionId", "answer", 3));
//            answerList.get(1).getPlusVotes().addAll(Arrays.asList("5"));
//            answerList.get(1).getSubtractVotes().addAll(Arrays.asList("6", "7"));
//            answerList.add(2, new Answer("id3", "userId", "questionId", "answer", 3));
//            answerList.get(2).getPlusVotes().addAll(Arrays.asList("8", "9"));
//            answerList.get(2).getSubtractVotes().addAll(Arrays.asList("ok", "noOk"));
//
//            Answer answerReturn = answerList.get(0);
//
//        when(answerRepository.findByQuestionId("questionId")).thenReturn(Flux.just(answerReturn).map(mapperUtils.mapperToAnswerDTO()));
////        when(updateAnswerUseCase.apply(any(AnswerDTO.class))).thenReturn(notNull());
//
//        StepVerifier.create(deleteVoteUseCase.apply(answerDTO))
//                .verifyComplete();


//    }



    }

