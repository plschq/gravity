package gravity;


import javafx.stage.Stage;


public final class Launcher {
    
    public static void start(Stage stage) {
        Window.init(stage);
        App.init();
        Window.show();
    } public static void stop() {
        Window.getStage().close();
    }
    
    public static void main(String[] args) {
        Window.main(args);
    }
    
}
