package cmsc335_final_project.panels;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TrafficLightPanel extends TimelinePanel {
    private Circle redLight, yellowLight, greenLight; // Traffic light circles

    public void initTrafficLightPanel(BorderPane root) {
        // Initialize the traffic light circles with default 'off' color
        redLight = new Circle(20, Color.GRAY);
        yellowLight = new Circle(20, Color.GRAY);
        greenLight = new Circle(20, Color.GRAY);

        // Align traffic lights vertically
        VBox trafficLightBox = new VBox(10, redLight, yellowLight, greenLight);
        trafficLightBox.setAlignment(Pos.CENTER);

        // Place the traffic light box in the center of the main layout
        root.setCenter(trafficLightBox);
    }

    public void startTrafficLightCycle() {
        setTimeline(createNewTimeline());

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        currentTimelineState = TimelineAction.ACTIVE;
    }

    public Timeline createNewTimeline() {
        return new Timeline(
                new KeyFrame(Duration.seconds(0), event -> turnOnRedLight()),
                new KeyFrame(Duration.seconds(10), event -> turnOnYellowLight()),
                new KeyFrame(Duration.seconds(12), event -> turnOnGreenLight()),
                new KeyFrame(Duration.seconds(20), event -> turnOnRedLight()));
    }

    public void turnOffAllLights() {
        redLight.setFill(Color.GRAY);
        yellowLight.setFill(Color.GRAY);
        greenLight.setFill(Color.GRAY);
    }

    public void turnOnRedLight() {
        turnOffAllLights();
        redLight.setFill(Color.RED);
    }

    public void turnOnYellowLight() {
        turnOffAllLights();
        yellowLight.setFill(Color.YELLOW);
    }

    public void turnOnGreenLight() {
        turnOffAllLights();
        greenLight.setFill(Color.GREEN);
    }

}
