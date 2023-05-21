package ru.makcnm.exam11.pojo;

import java.io.File;

public class Question {
    private final File questionFile;
    private final String answer;
    private String inputted;

    public Question(File questionFile, String answer, String inputted) {
        this.questionFile = questionFile;
        this.answer = answer;
        this.inputted = inputted;
    }

    public File getQuestionFile() {
        return questionFile;
    }

    public String getAnswer() {
        return answer;
    }

    public String getInputted() {
        return inputted;
    }

    public void setInputted(String inputted) {
        this.inputted = inputted;
    }
}
