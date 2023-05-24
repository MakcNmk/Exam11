package ru.makcnm.exam11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import ru.makcnm.exam11.controller.MainController;
import ru.makcnm.exam11.pojo.Question;
import ru.makcnm.exam11.util.HelpWindowUtil;
import ru.makcnm.exam11.util.QuestionUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unchecked")
public class Exam11 extends Application {
    public static Stage stage;
    public static Scene mainScene;
    public static int questionNumber = -1;
    public static ArrayList<Question> questionList = null;

    @Override
    public void start(Stage stage) throws IOException {
        Exam11.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Exam11.class.getResource("fxml/main.fxml"));
        MainController mainController = new MainController();
        fxmlLoader.setController(mainController);
        Scene scene = new Scene(fxmlLoader.load());
        mainScene = scene;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnShown(event -> onOpenWindowEvent(scene, mainController));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void onOpenWindowEvent(Scene scene, MainController mainController) {
        try {
            File lessonDir = new File(Constants.LESSON_DIR);

            if (!lessonDir.exists()) {
                HelpWindowUtil.openInfoWindow(scene, "Ошибка", "Не найдена папка с уроками");
                return;
            }

            String[] lessonNames = lessonDir.list();

            if (lessonNames == null || lessonNames.length == 0) {
                HelpWindowUtil.openInfoWindow(scene, "Ошибка", "Папка с уроками пуста");
                return;
            }

            ChoiceBox choiceLesson = mainController.getChoiceLesson();
            ChoiceBox choiceAction = mainController.getChoiceAction();
            ChoiceBox choiceNumber = mainController.getChoiceNumber();

            choiceLesson.getItems().addAll(lessonNames);
            choiceAction.getItems().addAll(Constants.CHOICE_ACTIONS.toArray());

            choiceLesson.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String lessonName = String.valueOf(newValue);
                String actionName = String.valueOf(choiceAction.getValue());
                if (actionName.equals("Выполнить вариант/задание")) return;
                ArrayList<String> numbers = QuestionUtil.getNumbers(lessonName, actionName);
                if (numbers == null) return;
                choiceNumber.getItems().clear();
                choiceNumber.getItems().addAll(numbers.toArray());
            });

            choiceAction.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String lessonName = String.valueOf(choiceLesson.getValue());
                String actionName = String.valueOf(newValue);
                if (lessonName.equals("Выберите предмет")) return;
                ArrayList<String> numbers = QuestionUtil.getNumbers(lessonName, actionName);
                if (numbers == null) return;
                choiceNumber.getItems().clear();
                Collections.sort(numbers);
                choiceNumber.getItems().addAll(numbers.toArray());
            });
        } catch (Exception e) {
            HelpWindowUtil.openInfoWindow(scene, "Ошибка", "Непредвиденная ошибка");
            e.printStackTrace();
        }
    }
}