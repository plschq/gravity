package gravity.controllers;


import gravity.App;

import gravity.Properties;
import gravity.components.GObject;
import gravity.dataclasses.Vector;
import gravity.dataclasses.XY;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;


public class ObjectsSceneController {
    
    private static XY lastMousePressed;
    private static XY lastCameraTranslate;
    
    
    public static void init() {
        App.objectsScene.addEventHandler(ScrollEvent.SCROLL, e -> {
            App.objectsScene.getCamera().setTranslateZ(App.objectsScene.getCamera().getTranslateZ() + e.getDeltaY());
        });
        App.objectsScene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!e.getButton().equals(MouseButton.SECONDARY)) {return;}
            lastMousePressed = new XY(e.getSceneX(), e.getSceneY());
            lastCameraTranslate = new XY(
                    App.objectsScene.getCamera().getTranslateX(),
                    App.objectsScene.getCamera().getTranslateY());
        });
        App.objectsScene.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (!e.getButton().equals(MouseButton.SECONDARY)) {return;}
            App.objectsScene.getCamera().setTranslateX(lastCameraTranslate.x + lastMousePressed.x - e.getSceneX());
            App.objectsScene.getCamera().setTranslateY(lastCameraTranslate.y + lastMousePressed.y - e.getSceneY());
        });
    }
    
}
