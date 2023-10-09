package cmsc335_final_project.panels.impls;

import java.util.concurrent.ExecutorService;

import cmsc335_final_project.enums.FutureAction;
import cmsc335_final_project.enums.TrafficLightStatus;
import cmsc335_final_project.panels.IThreadPanel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TrafficLightPanel extends FuturePanel implements IThreadPanel {
    private Circle redLight;
    private Circle yellowLight;
    private Circle greenLight; // Traffic light circles

    @Getter
    private TrafficLightStatus trafficLightStatus;

    public void initPanel(VBox centerBox, ExecutorService executorService) {
        this.executorService = executorService;

        // Initialize the traffic light circles with default 'off' color
        redLight = new Circle(20, Color.GRAY);
        yellowLight = new Circle(20, Color.GRAY);
        greenLight = new Circle(20, Color.GRAY);

        // Align traffic lights vertically
        VBox trafficLightBox = new VBox(10, redLight, yellowLight, greenLight);
        trafficLightBox.setAlignment(Pos.CENTER);

        // Place the traffic light box in the center of the main layout
        centerBox.getChildren().add(trafficLightBox);
    }

    public void startUpdate() {
        createNewFuture();
        setCurrentTimelineState(FutureAction.ACTIVE);
    }

    @Override
    public void stopUpdate() {
        future.cancel(true);
        setCurrentTimelineState(FutureAction.STOP);
    }

    public void createNewFuture() {
        this.trafficLightStatus = TrafficLightStatus.RED;
        future = executorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {

                    // Green Light Logic
                    Platform.runLater(() -> turnOnLight(greenLight, TrafficLightStatus.GREEN));
                    Thread.sleep(10000); // 10 seconds for green light

                    // Yellow Light Logic
                    Platform.runLater(() -> turnOnLight(yellowLight, TrafficLightStatus.YELLOW));
                    Thread.sleep(2000); // 2 seconds for yellow light

                    // Red Light Logic
                    Platform.runLater(() -> turnOnLight(redLight, TrafficLightStatus.RED));
                    Thread.sleep(8000); // 8 seconds for red light

                }
            } catch (InterruptedException e) {
                // Handle interruption - This is normal when stopping the simulation.
                System.out.println("Traffic Light thread has been slept");
            }
        });
        currentTimelineState = FutureAction.PAUSE;
    }

    public void turnOffAllLights() {
        redLight.setFill(Color.GRAY);
        yellowLight.setFill(Color.GRAY);
        greenLight.setFill(Color.GRAY);
    }

    public void turnOnLight(Circle circleToChange, TrafficLightStatus trafficLightStatus) {
        turnOffAllLights();
        this.trafficLightStatus = trafficLightStatus;
        circleToChange.setFill(trafficLightStatus.getColor());
    }

}
