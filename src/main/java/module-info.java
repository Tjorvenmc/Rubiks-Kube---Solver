module com.rubiks {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens com.rubiks to javafx.fxml;
    exports com.rubiks;
}
