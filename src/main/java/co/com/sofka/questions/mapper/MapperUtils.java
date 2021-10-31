package co.com.sofka.questions.mapper;

import co.com.sofka.questions.collections.Answer;
import co.com.sofka.questions.collections.Question;
import co.com.sofka.questions.model.AnswerDTO;
import co.com.sofka.questions.model.QuestionDTO;
import co.com.sofka.questions.reposioties.AnswerRepository;
import co.com.sofka.questions.usecases.UpdateAnswerUseCase;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {

    @Autowired
    AnswerRepository answerRepository;
    UpdateAnswerUseCase updateAnswerUseCase;

    public Function<AnswerDTO, Answer> mapperToAnswer() {
//        return updateAnswer -> {
//            var answer = new Answer();
//            answer.setPosition(updateAnswer.getPosition());
//            answer.setQuestionId(updateAnswer.getQuestionId());
//            answer.setUserId(updateAnswer.getUserId());
//            answer.setAnswer(updateAnswer.getAnswer());
//            answer.setId(updateAnswer.getId());
//            return answer;
//        };
        return updateAnswer -> {
            Answer answer = new Answer();
            BeanUtils.copyProperties(updateAnswer,answer);
            return answer;
    };
    }

    public Function<QuestionDTO, Question> mapperToQuestion(String id) {
        return updateQuestion -> {
            Question question = new Question();
            BeanUtils.copyProperties(updateQuestion,question);

//            question.setId(id);
//            question.setUserId(updateQuestion.getUserId());
//            question.setCategory(updateQuestion.getCategory());
//            question.setQuestion(updateQuestion.getQuestion());
//            question.setUserId(updateQuestion.getUserId());
//            question.setType(updateQuestion.getType());
            return question;
        };
    }

    public Function<Question, QuestionDTO> mapEntityToQuestion() {
        return entity -> {
            QuestionDTO questionDTO = new QuestionDTO();
//                entity.getId(),
//                entity.getUserId(),
//                entity.getQuestion(),
//                entity.getType(),
//                entity.getCategory()
            BeanUtils.copyProperties(entity, questionDTO);
            return questionDTO;
        };
            }

    public Function<Answer, AnswerDTO> mapEntityToAnswer() {
        return entity -> {
            AnswerDTO answerDTO = new AnswerDTO();
//                    entity.getId(),
//                    entity.getUserId(),
//                    entity.getQuestionId(),
//                    entity.getAnswer(),
//                    entity.getPosition()
            BeanUtils.copyProperties(entity, answerDTO);
            return answerDTO;
        };}

        public void deletePreviousVote(AnswerDTO answerDTO){
            answerRepository.findAllByQuestionId(answerDTO.getQuestionId())
                    .flatMap(originalAnswer -> {
                        originalAnswer.removeUpVote(answerDTO.getUserId());
                        originalAnswer.removeDownVote(answerDTO.getUserId());
                        return updateAnswerUseCase.apply(mapEntityToAnswer().apply(originalAnswer));
                    });
        }
    }


