package ru.makcnm.exam11.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import ru.makcnm.exam11.Exam11;
import ru.makcnm.exam11.pojo.Question;
import ru.makcnm.exam11.pojo.ResultRow;
import ru.makcnm.exam11.util.QuestionUtil;

@SuppressWarnings("unused")
public class ResultController {
    @FXML
    private TableView<ResultRow> resultTable;
    @FXML
    private Button goHomeButton;
    @FXML
    private Button endButton;

    @FXML
    public void goHomeButtonClickEvent(MouseEvent event) {
        Exam11.stage.setScene(Exam11.mainScene);
    }

    @FXML
    public void endButtonClickEvent(MouseEvent event) {
        for (Question question : Exam11.questionList) question.setInputted("");
        Scene firstQuestion = QuestionUtil.getQuestionScene(Exam11.questionList.get(Exam11.questionNumber));
        Exam11.stage.setScene(firstQuestion);
    }

    public TableView getResultTable() {
        return resultTable;
    }
}
