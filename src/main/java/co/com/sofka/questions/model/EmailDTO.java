package co.com.sofka.questions.model;

public class EmailDTO {
    private String toEmail;
    private String toName;
    private String question;
    private String questionLink;

    public EmailDTO(String toEmail, String toName, String question, String questionLink) {
        this.toEmail = toEmail;
        this.toName = toName;
        this.question = question;
        this.questionLink = questionLink;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionLink() {
        return questionLink;
    }

    public void setQuestionLink(String questionLink) {
        this.questionLink = questionLink;
    }
}
