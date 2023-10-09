package cmsc335_final_project.panels.impls;

import java.util.concurrent.ExecutorService;

import cmsc335_final_project.panels.IThreadPanel;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TrafficLightPanel extends TimelinePanel implements IThreadPanel {
    private Circle redLight, yellowLight, greenLight; // Traffic light circles

    @Override
    public void initPanel(BorderPane root) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initPanel'");
    }

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
        setCurrentTimelineState(TimelineAction.ACTIVE);
    }

    @Override
    public void stopUpdate() {
        future.cancel(true);
        setCurrentTimelineState(TimelineAction.STOP);
    }

    public void createNewFuture() {
        future = executorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {

                    // Red Light Logic
                    Platform.runLater(() -> turnOnRedLight());
                    Thread.sleep(10000); // 10 seconds for red light

                    // Yellow Light Logic
                    Platform.runLater(() -> turnOnYellowLight());
                    Thread.sleep(2000); // 2 seconds for yellow light

                    // Green Light Logic
                    Platform.runLater(() -> turnOnGreenLight());
                    Thread.sleep(8000); // 8 seconds for green light

                }
            } catch (InterruptedException e) {
                // Handle interruption - This is normal when stopping the simulation.
                System.out.println("Traffic Light thread has been slept");
            }
        });
        currentTimelineState = TimelineAction.PAUSE;
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
