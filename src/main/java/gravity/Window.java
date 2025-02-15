package gravity;


import javafx.application.Application;
import javafx.stage.Stage;


public final class Window extends Application {
    
    private static Stage stage;
    
    
    public static Stage getStage() {return Window.stage;}
    
    public static void init(Stage stage) {
        Window.stage = stage;
        Window.stage.setTitle("Gravity 2 | JavaFX application");
        Window.stage.setScene(App.currentScene);
        Window.stage.setFullScreen(false);
        Window.stage.setAlwaysOnTop(false);
        Window.stage.setResizable(true);
        Window.stage.setMinWidth(500);
        Window.stage.setMinHeight(300);
    }
    
    public static void show() {Window.stage.show();}
    
    @Override public void start(Stage stage) {Launcher.start(stage);}
    @Override public void stop() {Launcher.stop();}
    
    public static void main(String[] args) {Window.launch(args);}
    
}
