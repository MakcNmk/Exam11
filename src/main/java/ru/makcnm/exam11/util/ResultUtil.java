package ru.makcnm.exam11.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.makcnm.exam11.Exam11;
import ru.makcnm.exam11.controller.ResultController;
import ru.makcnm.exam11.pojo.Question;
import ru.makcnm.exam11.pojo.ResultRow;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ResultUtil {
    public static Scene getResultScene(ArrayList<Question> questionList) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Exam11.class.getResource("fxml/result.fxml"));
            ResultController resultController = new ResultController();
            fxmlLoader.setController(resultController);
            Scene resultScene = new Scene(fxmlLoader.load());
            TableView resultTable = resultController.getResultTable();

            // Генерация таблицы

            TableColumn<ResultRow, String> columnQuestionNum = new TableColumn<>("Задание");
            columnQuestionNum.setStyle(
                    "-fx-background-color: #EAEEEF; -fx-border-color: gray;"
            );
            columnQuestionNum.setPrefWidth(148);
            columnQuestionNum.setMaxWidth(148);
            columnQuestionNum.setCellValueFactory(new PropertyValueFactory<>("questionNum"));

            TableColumn<ResultRow, String> columnAnswer = new TableColumn<>("Ваш ответ");
            columnAnswer.setStyle(
                    "-fx-background-color: #EAEEEF; -fx-border-color: gray;"
            );
            columnAnswer.setPrefWidth(148);
            columnAnswer.setMaxWidth(148);
            columnAnswer.setCellValueFactory(new PropertyValueFactory<>("answer"));

            TableColumn<ResultRow, String> columnInputted = new TableColumn<>("Ответ");
            columnInputted.setStyle(
                    "-fx-background-color: #EAEEEF; -fx-border-color: gray;"
            );
            columnInputted.setPrefWidth(148);
            columnInputted.setMaxWidth(148);
            columnInputted.setCellValueFactory(new PropertyValueFactory<>("inputted"));

            resultTable.getColumns().addAll(List.of(columnQuestionNum, columnAnswer, columnInputted));

            for (Question question : questionList)
                resultTable.getItems().addAll(new ResultRow(
                        String.valueOf(questionList.indexOf(question) + 1),
                        question.getInputted(),
                        question.getAnswer()
                ));

            return resultScene;
        } catch (Exception e) {
            HelpWindowUtil.openInfoWindow(Exam11.stage.getScene(), "Ошибка", "Непредвиденная ошибка");
            e.printStackTrace();
            return null;
        }
    }
}
