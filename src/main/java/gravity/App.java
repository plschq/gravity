package gravity;


import gravity.components.GObject;
import gravity.controllers.ObjectsSceneController;
import gravity.controllers.ObjectsSpawnController;

import gravity.dataclasses.Vector;
import gravity.dataclasses.XY;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;


public final class App {
    
    public static final Group objectsSceneRoot = new Group();
    public static final Scene objectsScene = new Scene(App.objectsSceneRoot, 500, 500, true, SceneAntialiasing.BALANCED);
    
    public static final Scene currentScene = App.objectsScene;
    
    public static final ArrayList<GObject> objects = new ArrayList<>();
    public static final ArrayList<GObject> crashedObjects = new ArrayList<>();
    
    
    public static void init() {
        App.objectsScene.setCursor(Cursor.DEFAULT);
        App.objectsScene.setFill(Properties.OBJECTS_SCENE_BG_COLOR);
        App.objectsScene.setCamera(new PerspectiveCamera(false));
        App.objectsScene.getCamera().setTranslateX(0);
        App.objectsScene.getCamera().setTranslateY(0);
        App.objectsScene.getCamera().setTranslateZ(0);
    
        ObjectsSpawnController.init();
        ObjectsSceneController.init();
    
        // App.addObject(new GObject(new XY(250, 250), 1.98892 * Math.pow(10, 30), new Vector(0, 0))); // Spawn real Sun
        // App.addObject(new GObject(new XY(250, 250 - 586.3), Properties.EARTH_MASS, new Vector(0.00233, 90))); // Spawn real Earth
    }
    
    public static void addObject(GObject object) {
        App.objects.add(object);
        App.objectsSceneRoot.getChildren().add(object.node);
    }
    
    public static double getRadiusByMass(double mass) {
        return Properties.UNIT * Math.sqrt(mass / Properties.EARTH_MASS);
    }
    
    public static double getAngleBetween(XY point1, XY point2) {
        double angle = 0;
        if (point1.x < point2.x && point1.y >= point2.y) {
            angle = Math.atan((point2.x - point1.x) / (point1.y - point2.y)) / (Math.PI / 180) + 180;
        } else if (point1.x < point2.x) {
            angle = Math.atan((point1.x - point2.x) / (point2.y - point1.y)) / (Math.PI / 180);
        } else if (point1.y < point2.y) {
            angle = Math.atan((point2.x - point1.x) / (point1.y - point2.y)) / (Math.PI / 180);
        } else if (point1.y >= point2.y) {
            angle = Math.atan((point1.x - point2.x) / (point2.y - point1.y)) / (Math.PI / 180) + 180;
        } return (Double.isNaN(angle) || Double.isInfinite(angle)) ? 0 : App.simplifyAngle(angle);
    }
    
    public static double getDistanceBetween(XY point1, XY point2) {
        return Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
    }
    
    public static double simplifyAngle(double angle) {
        return (angle % 360 + 360) % 360;
    }
    
}
