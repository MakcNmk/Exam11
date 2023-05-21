package ru.makcnm.exam11.controller;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import ru.makcnm.exam11.Exam11;
import ru.makcnm.exam11.util.QuestionUtil;

@SuppressWarnings("unused")
public class MainController {
    @FXML
    private ChoiceBox choiceLesson;
    @FXML
    private ChoiceBox choiceAction;
    @FXML
    private ChoiceBox choiceNumber;
    @FXML
    private Button startButton;

    @FXML
    public void startButtonClickEvent(MouseEvent event) {
        String lessonName = String.valueOf(choiceLesson.getValue());
        String actionName = String.valueOf(choiceAction.getValue());
        String numberString = String.valueOf(choiceNumber.getValue());

        if (numberString.equals("Номер")) return;

        int number = Integer.parseInt(numberString);
        Exam11.questionList = QuestionUtil.getQuestions(lessonName, actionName, number);

        if (Exam11.questionList == null) return;

        Exam11.questionNumber = 0;
        Scene firstQuestion = QuestionUtil.getQuestionScene(Exam11.questionList.get(Exam11.questionNumber));

        if (firstQuestion == null) return;

        Exam11.stage.setScene(firstQuestion);
    }

    public ChoiceBox getChoiceLesson() {
        return choiceLesson;
    }

    public ChoiceBox getChoiceAction() {
        return choiceAction;
    }

    public ChoiceBox getChoiceNumber() {
        return choiceNumber;
    }
}