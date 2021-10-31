package co.com.sofka.questions.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vote {
    @Id
    private String id;
    private String questionId;
    private String answerId;
    private String userId;
    private Integer value;

    public Vote(String questionId, String answerId, String userId, Integer value) {
        this.questionId = questionId;
        this.userId = userId;
        this.value = value;
        this.answerId=answerId;
    }

    public Vote() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
