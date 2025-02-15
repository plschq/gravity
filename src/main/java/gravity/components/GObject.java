package gravity.components;


import gravity.App;
import gravity.Properties;
import gravity.dataclasses.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Objects;


public class GObject {

    public XY pos;
    public double mass;
    public Vector velocity;
    public Timeline timeline;
    public Circle node = new Circle();


    public GObject(XY pos, double mass, Vector velocity) {
        this.pos = pos;
        this.mass = mass; // kg
        this.velocity = velocity; // Earths per second

        this.reload();
    }
    
    
    synchronized public void reload() {
        this.node.setRadius(App.getRadiusByMass(this.mass));
        this.node.setFill(Properties.OBJECT_COLOR);
        this.node.setCenterX(this.pos.x);
        this.node.setCenterY(this.pos.y);

        this.timeline = new Timeline(new KeyFrame(Duration.millis(Properties.FPS), e ->
                Platform.runLater(this::update)));
        this.timeline.setCycleCount(-1);
        this.timeline.play();
    }

    synchronized public void update() {
        this.pos.x += Math.sin(Math.toRadians(this.velocity.angle)) * this.velocity.value / Properties.EARTH;
        this.pos.y += -Math.cos(Math.toRadians(this.velocity.angle)) * this.velocity.value / Properties.EARTH;
        this.node.setCenterX(this.pos.x);
        this.node.setCenterY(this.pos.y);

        // Crashes
        for (GObject object : App.objects) {
            if (!Objects.equals(object, this) && !App.crashedObjects.contains(object)) {
                if (App.getDistanceBetween(this.pos, object.pos) <
                        this.node.getRadius() + object.node.getRadius()) {GObject.crash(this, object); break;}
            }
        }

        for (GObject object : App.objects) {
            if (!Objects.equals(object, this) && !App.crashedObjects.contains(object)) {
                double distance = App.getDistanceBetween(this.pos, object.pos) * Properties.EARTH;
                if (distance == 0) {break;}
                
                // Vector F = new Vector(Properties.G * this.mass / Math.pow(distance, 2),
                //                        App.getAngleBetween(this.pos, object.pos));
                Vector force = new Vector(Properties.G * this.mass * object.mass / Math.pow(distance, 2),
                        App.getAngleBetween(this.pos, object.pos));
                Vector acceleration = new Vector(force.value / object.mass, force.angle);
                
                object.velocity = Vector.add(object.velocity, acceleration);
            }
        }
    }

    synchronized public void delete() {
        this.timeline.stop();
        Group g = new Group();
        g.getChildren().add(this.node);
        g.getChildren().remove(this.node);
        App.objects.remove(this);
    }

    synchronized public static void crash(GObject object1, GObject object2) {
        if (App.crashedObjects.contains(object1) || App.crashedObjects.contains(object2)) {return;}

        App.crashedObjects.add(object1); object1.delete();
        App.crashedObjects.add(object2); object2.delete();

        XY pos; if (object1.mass > object2.mass) {pos = object1.pos;
        } else if (object2.mass > object1.mass) {pos = object2.pos;
        } else {pos = new XY(
                object1.pos.x + (Math.abs(object1.pos.x - object2.pos.x) / 2),
                object1.pos.y + (Math.abs(object1.pos.y - object2.pos.y) / 2));}
        // App.addObject(new GObject(pos, object1.mass + object2.mass, new Vector(0, 0)));
        // XY pos = new XY(Vector.add(object1.velocity, object2.velocity));
        Vector v = Vector.subtract(object1.velocity, object2.velocity);
        App.addObject(new GObject(pos, object1.mass + object2.mass, new Vector(v.value / (object1.mass + object2.mass), v.angle)));
    }

}
