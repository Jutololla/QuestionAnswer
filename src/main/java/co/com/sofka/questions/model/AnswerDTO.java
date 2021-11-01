package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class AnswerDTO {

    private String id;
    @NotBlank(message = "Debe existir el userId para este objeto")
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    private String answer;

    private Integer position;
    private ArrayList<String> plusVotes;
    private ArrayList<String> subtractVotes;

    public AnswerDTO() {
        this.plusVotes = new ArrayList<>();
        this.subtractVotes = new ArrayList<>();
    }

    public AnswerDTO(String id, String userId, String questionId, String answer, Integer position) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.position = position;
        this.plusVotes = new ArrayList<>();
        this.subtractVotes = new ArrayList<>();
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

    public void addUpVote(String userId) {
        this.plusVotes.add(userId);
    }

    public void addDownVote(String userId) {
        this.subtractVotes.add(userId);
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return Objects.equals(userId, answerDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
