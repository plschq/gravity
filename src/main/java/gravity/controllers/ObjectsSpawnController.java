package gravity.controllers;


import gravity.App;
import gravity.Properties;
import gravity.components.GObject;
import gravity.dataclasses.*;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class ObjectsSpawnController {
    
    private static XY lastMousePressed;
    
    
    public static void init() {
        App.objectsScene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
            lastMousePressed = new XY(e.getSceneX(), e.getSceneY());
        });
        App.objectsScene.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
            if (!e.getButton().equals(MouseButton.PRIMARY)) {return;}
            XY pos = new XY(lastMousePressed.x + App.objectsScene.getCamera().getTranslateX(),
                    lastMousePressed.y + App.objectsScene.getCamera().getTranslateY());
            
            double mass = Properties.EARTH_MASS;
            double angle = App.getAngleBetween(lastMousePressed, new XY(e.getSceneX(), e.getSceneY()));
            double distance = App.getDistanceBetween(lastMousePressed, new XY(e.getSceneX(), e.getSceneY()));
            
            Vector velocity = new Vector(distance * Properties.EARTH * Properties.FPS * 0.001, angle);
            App.addObject(new GObject(pos, mass, velocity));
        });
    }
    
}
