package cmsc335_final_project.panels;

import cmsc335_final_project.model.Car;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CarPanel {
    private HBox panel;
    private Timeline carMovementTimeline;

    public void initCarsPanel(BorderPane root) {
        panel = new HBox(50);

        // For demonstration purposes, let's add three cars to the panel:
        for (int i = 0; i < 3; i++) {
            addCar(new Car());
        }

        root.setBottom(panel);
    }

    public void addCar(Car car) {
        panel.getChildren().add(car);
    }

    public void startCarMovementUpdate() {
        carMovementTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    // Every second, update the position of each car based on its speed
                    for (Node carNode : panel.getChildren()) {
                        Car car = (Car) carNode;
                        car.updatePosition(2); // updating for 1 second
                    }
                }));
        carMovementTimeline.setCycleCount(Timeline.INDEFINITE);
        carMovementTimeline.play();
    }
}
