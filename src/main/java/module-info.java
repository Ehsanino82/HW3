module com.example.hw3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;

    opens Sample to javafx.fxml;
    exports Sample;
    exports Sample.Controller;
    opens Sample.Controller to javafx.fxml;
    exports Sample.View;
    opens Sample.View to javafx.fxml;
    opens Sample.Model;
    exports Sample.Model to com.google.Gson;
    exports Sample.View.Component to com.google.Gson;
    opens Sample.View.Component;

}