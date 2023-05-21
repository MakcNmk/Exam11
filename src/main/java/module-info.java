module com.example.exam {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.makcnm.exam11 to javafx.fxml;
    exports ru.makcnm.exam11;
    exports ru.makcnm.exam11.controller;
    exports ru.makcnm.exam11.pojo;
    opens ru.makcnm.exam11.controller to javafx.fxml;
}