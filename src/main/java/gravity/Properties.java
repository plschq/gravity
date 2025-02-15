package gravity;


import javafx.scene.paint.Color;


public final class Properties {
    
    public static final double G = 0.00001; // 6.6743 * Math.pow(10, -11);
    public static final double PI = 3.1415926535897932384626433832795;
    public static final double UNIT = 20;
    public static final double FPS = 10;
    public static final double SPEED = 1;
    public static final double EARTH = 6378100 * 2 / Properties.UNIT;
    public static final double EARTH_MASS = 5.792 * Math.pow(10, 24);
    
    public static final Color OBJECTS_SCENE_BG_COLOR = Color.web("#111");
    public static final Color OBJECT_COLOR = Color.web("#fff");
    
}
