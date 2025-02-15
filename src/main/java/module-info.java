module gravity {
    
    requires javafx.controls;
    requires javafx.fxml;
    
    
    opens gravity to javafx.fxml;
    exports gravity;
    
}
