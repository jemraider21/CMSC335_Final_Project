package cmsc335_final_project.panels.impls;

import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

import cmsc335_final_project.enums.FutureAction;
import cmsc335_final_project.enums.TrafficLightStatus;
import cmsc335_final_project.model.Car;
import cmsc335_final_project.panels.IThreadPanel;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CarPanel extends FuturePanel implements IThreadPanel {
    private HBox panel;

    @Setter
    private TrafficLightPanel trafficLightPanel;

    public void initPanel(VBox centerBox, ExecutorService executorService) {
        panel = new HBox(50);
        this.executorService = executorService;

        // For demonstration purposes, let's add three cars to the panel:
        IntStream.range(0, 3).forEachOrdered(n -> addCar(new Car()));
        centerBox.getChildren().add(panel);
    }

    public void addCar(Car car) {
        panel.getChildren().add(car);
    }

    public void startUpdate() {
        createNewFuture();
        setCurrentTimelineState(FutureAction.ACTIVE);
    }

    public void createNewFuture() {
        future = executorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Platform.runLater(this::startCar);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Car thread has been slept");
            }
        });
        currentTimelineState = FutureAction.ACTIVE;
    }

    private void startCar() {
        for (Node carNode : panel.getChildren()) {
            if (carNode instanceof Car) {
                Car car = (Car) carNode;
                TrafficLightStatus trafficLightStatus = trafficLightPanel.getTrafficLightStatus();
                System.out.println("Traffic light is: " + trafficLightStatus.toString());

                if (trafficLightStatus.isSafeToProceed()) {
                    car.updatePosition(1);
                } else {
                    car.stop();
                }
            }
        }
    }

    @Override
    public void stopUpdate() {
        future.cancel(true);
        setCurrentTimelineState(FutureAction.STOP);
    }
}
