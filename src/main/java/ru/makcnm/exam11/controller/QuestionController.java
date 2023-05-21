package ru.makcnm.exam11.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import ru.makcnm.exam11.Exam11;
import ru.makcnm.exam11.util.QuestionUtil;
import ru.makcnm.exam11.util.ResultUtil;

@SuppressWarnings("unused")
public class QuestionController {
    @FXML
    private Button nextButton;
    @FXML
    private Button endButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField answerField;

    @FXML
    public void nextButtonClickEvent(MouseEvent event) {
        Exam11.questionList.get(Exam11.questionNumber).setInputted(answerField.getText());
        int newQuestionNumber = Exam11.questionNumber + 1;
        if (newQuestionNumber >= Exam11.questionList.size()) return;
        Exam11.questionNumber = newQuestionNumber;
        Exam11.stage.setScene(QuestionUtil.getQuestionScene(Exam11.questionList.get(Exam11.questionNumber)));
    }

    @FXML
    public void endButtonClickEvent(MouseEvent event) {
        Exam11.questionList.get(Exam11.questionNumber).setInputted(answerField.getText());
        Exam11.stage.setScene(ResultUtil.getResultScene(Exam11.questionList));
    }

    @FXML
    public void backButtonClickEvent(MouseEvent event) {
        Exam11.questionList.get(Exam11.questionNumber).setInputted(answerField.getText());
        int newQuestionNumber = Exam11.questionNumber - 1;
        if (newQuestionNumber < 0) return;
        Exam11.questionNumber = newQuestionNumber;
        Exam11.stage.setScene(QuestionUtil.getQuestionScene(Exam11.questionList.get(Exam11.questionNumber)));
    }

    public TextField getAnswerField() {
        return answerField;
    }
}
