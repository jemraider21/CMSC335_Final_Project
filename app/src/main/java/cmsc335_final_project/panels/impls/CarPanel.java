package cmsc335_final_project.panels.impls;

import java.util.concurrent.ExecutorService;

import cmsc335_final_project.model.Car;
import cmsc335_final_project.panels.IThreadPanel;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CarPanel extends TimelinePanel implements IThreadPanel {
    private HBox panel;

    @Override
    public void initPanel(BorderPane root) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initPanel'");
    }

    public void initPanel(VBox centerBox, ExecutorService executorService) {
        panel = new HBox(50);
        this.executorService = executorService;

        // For demonstration purposes, let's add three cars to the panel:
        for (int i = 0; i < 3; i++) {
            addCar(new Car());
        }

        centerBox.getChildren().add(panel);
    }

    public void addCar(Car car) {
        panel.getChildren().add(car);
    }

    public void startUpdate() {
        createNewFuture();
        setCurrentTimelineState(TimelineAction.ACTIVE);
    }

    public void createNewFuture() {
        future = executorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Platform.runLater(() -> {
                        for (Node carNode : panel.getChildren()) {
                            if (carNode instanceof Car) {
                                Car car = (Car) carNode;
                                car.updatePosition(1);
                            }
                        }
                    });
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Car thread has been slept");
            }
        });
        currentTimelineState = TimelineAction.ACTIVE;
    }

    @Override
    public void stopUpdate() {
        future.cancel(true);
        setCurrentTimelineState(TimelineAction.STOP);
    }
}
