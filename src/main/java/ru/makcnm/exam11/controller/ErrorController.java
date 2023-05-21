package ru.makcnm.exam11.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

@SuppressWarnings("unused")
public class ErrorController {
    @FXML
    private Text infoText;
    @FXML
    private Text titleText;

    public Text getInfoText() {
        return infoText;
    }

    public Text getTitleText() {
        return titleText;
    }
}
