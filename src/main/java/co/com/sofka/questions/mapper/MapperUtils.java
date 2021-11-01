package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.repositories.AnswerRepository;
import co.com.sofka.questions.usecases.UpdateAnswerUseCase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    public Function<AnswerDTO, Answer> mapperToAnswer() {
        return updateAnswer -> {
            Answer answer = new Answer();
            BeanUtils.copyProperties(updateAnswer, answer);
            return answer;
        };
    }

    public Function<QuestionDTO, Question> mapperToQuestion() {
        return updateQuestion -> {
            Question question = new Question();
            BeanUtils.copyProperties(updateQuestion, question);
            return question;
        };
    }

    public Function<Question, QuestionDTO> mapEntityToQuestion() {
        return entity -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(entity, questionDTO);
            return questionDTO;
        };
    }

    public Function<Answer, AnswerDTO> mapEntityToAnswer() {
        return entity -> {
            AnswerDTO answerDTO = new AnswerDTO();
            BeanUtils.copyProperties(entity, answerDTO);
            return answerDTO;
        };
    }

    public Function<Answer, Answer> setPosition() {
        return answer -> {
            Answer answerToReturn = new Answer();
            BeanUtils.copyProperties(answer, answerToReturn);
            answerToReturn.setPosition(answerToReturn.getPlusVotes().size() - answerToReturn.getSubtractVotes().size());
            return answerToReturn;
        };
    }


}


