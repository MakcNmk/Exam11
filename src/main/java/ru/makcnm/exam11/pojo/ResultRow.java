package ru.makcnm.exam11.pojo;

@SuppressWarnings("unused")
public class ResultRow {
    private String questionNum = null;
    private String answer = null;
    private String inputted = null;

    public ResultRow() {
    }
    public ResultRow(String questionNum, String answer, String inputted) {
        this.questionNum = questionNum;
        this.answer = answer;
        this.inputted = inputted;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getInputted() {
        return inputted;
    }

    public void setInputted(String inputted) {
        this.inputted = inputted;
    }
}
