package ru.makcnm.exam11.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.makcnm.exam11.Exam11;
import ru.makcnm.exam11.controller.ErrorController;

import java.io.IOException;

public class HelpWindowUtil {
    public static void openInfoWindow(Scene rootScene, String titleText, String infoText) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Exam11.class.getResource("fxml/info.fxml"));
            ErrorController errorController = new ErrorController();
            fxmlLoader.setController(errorController);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initOwner(rootScene.getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.requestFocus();
            errorController.getTitleText().setText(titleText);
            errorController.getInfoText().setText(infoText);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
