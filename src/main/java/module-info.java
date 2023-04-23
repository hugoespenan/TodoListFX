module com.example.todolistjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jakarta.mail;


    opens com.example.todolistjavafx to javafx.fxml;
    exports com.example.todolistjavafx;
}