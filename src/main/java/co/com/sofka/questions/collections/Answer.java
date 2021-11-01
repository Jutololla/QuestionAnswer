package co.com.sofka.questions.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Answer {
    @Id
    private String id;
    private String userId;
    private String questionId;
    private String answer;
    private Integer position;
    private ArrayList<String> plusVotes;
    private ArrayList<String> subtractVotes;

    public Answer() {
        this.plusVotes = new ArrayList<>();
        this.subtractVotes = new ArrayList<>();
    }

    public Answer(String id, String userId, String questionId, String answer, Integer position) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.position = position;
        this.plusVotes = new ArrayList<>();
        this.subtractVotes = new ArrayList<>();
    }


    public void addUpVote(String userId) {
        this.plusVotes.add(userId);
    }

    public void addDownVote(String userId) {
        this.subtractVotes.add(userId);
    }

    public void removeUpVote(String userId) {
        boolean opt;
        do {
            opt = this.plusVotes.remove(userId);
        } while (opt);
    }

    public void removeDownVote(String userId) {
        boolean opt;
        do {
            opt = this.subtractVotes.remove(userId);
        } while (opt);
    }

  public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getPlusVotes() {
        return plusVotes;
    }

    public void setPlusVotes(ArrayList<String> plusVotes) {
        this.plusVotes = plusVotes;
    }

    public ArrayList<String> getSubtractVotes() {
        return subtractVotes;
    }

    public void setSubtractVotes(ArrayList<String> subtractVotes) {
        this.subtractVotes = subtractVotes;
    }

}
