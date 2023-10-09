package cmsc335_final_project.model;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

public class Car extends VBox {
    private Rectangle rectangle;
    private Label speedLable, positionLabel;

    @Getter
    private double speed; // in m/s

    @Getter
    private double position; // in meters

    public Car() {
        rectangle = new Rectangle(30, 15, Color.BLUE); // Simple car representation

        speedLable = new Label("Speed: 0");
        positionLabel = new Label("Position: 0");

        this.getChildren().addAll(rectangle, speedLable, positionLabel);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        speedLable.setText(String.format("Speed: %s m/s", String.valueOf(speed)));
    }

    public void setPosition(double position) {
        this.position = position;
        positionLabel.setText(String.format("Position: %s m", String.valueOf(position)));
    }

    public void updatePosition(double timeInSeconds) {
        this.position += this.speed * timeInSeconds;
        positionLabel.setText(String.format("Position: %s m", String.valueOf(position)));
    }
}
