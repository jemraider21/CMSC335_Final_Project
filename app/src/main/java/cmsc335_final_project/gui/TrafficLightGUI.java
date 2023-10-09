package cmsc335_final_project.gui;

import java.util.Timer;
import java.util.TimerTask;

import cmsc335_final_project.models.TrafficLightColor;
import cmsc335_final_project.models.TrafficLightSimulator;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrafficLightGUI extends StackPane {

    private TrafficLightSimulator tl;

    public TrafficLightGUI(TrafficLightSimulator tl) {
        this.tl = tl;

        // Create a Circle for each traffic light color.
        Circle redCircle = createCircle(50, javafx.scene.paint.Color.RED);
        Circle yellowCircle = createCircle(50, Color.YELLOW);
        Circle greenCircle = createCircle(50, Color.GREEN);

        // Add the Circles to the StackPane.
        getChildren().addAll(redCircle, yellowCircle, greenCircle);

        // Start a timer to update the traffic light colors.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTrafficLightColors();
            }
        }, 0, 1000);
    }

    private void updateTrafficLightColors() {
        // Get the current traffic light color.
        TrafficLightColor color = tl.getColor();

        // Set the fill color of the appropriate Circle.
        switch (color) {
            case RED:
                getCircleFromParent(0).setFill(Color.RED);
                break;
            case YELLOW:
                getCircleFromParent(1).setFill(Color.YELLOW);
                break;
            case GREEN:
                getCircleFromParent(2).setFill(Color.GREEN);
                break;
        }
    }

    private Circle createCircle(double radius, Color color) {
        Circle circle = new Circle(radius);
        circle.setFill(color);
        return circle;
    }

    private Circle getCircleFromParent(int index) {
        return (Circle) getChildren().get(index);
    }
}
