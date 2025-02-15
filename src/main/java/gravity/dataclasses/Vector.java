package gravity.dataclasses;


import gravity.App;


public class Vector {
    
    public double value;
    public double angle;
    
    
    public Vector(double value, double angle) {
        this.value = value;
        this.angle = angle;
    }
    
    
    public static Vector add(Vector vector1, Vector vector2) {
        XY point1 = new XY(0, 0);
        XY point2 = new XY(
                Math.sin(Math.toRadians(vector1.angle)) * vector1.value,
                Math.cos(Math.toRadians(vector1.angle)) * vector1.value
        ); XY point3 = new XY(
                point2.x + Math.sin(Math.toRadians(vector2.angle)) * vector2.value,
                point2.y + Math.cos(Math.toRadians(vector2.angle)) * vector2.value
        ); return new Vector(App.getDistanceBetween(point1, point3),
                App.simplifyAngle(-App.getAngleBetween(point1, point3)));
    }
    
    public static Vector subtract(Vector vector1, Vector vector2) {
        vector2.angle += 180;
        return Vector.add(vector1, vector2);
    }
    
}
