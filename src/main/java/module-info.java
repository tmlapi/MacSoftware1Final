module software1.finalsoftwarev2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens software1.finalsoftwarev2 to javafx.fxml;
    opens Model to javafx.base;
    exports software1.finalsoftwarev2;
}