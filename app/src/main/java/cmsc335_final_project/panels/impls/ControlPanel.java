package cmsc335_final_project.panels.impls;

import cmsc335_final_project.enums.ControlPanelAction;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ControlPanel {
    private HBox panel;
    private Button startButton;
    private Button stopButton;
    private TrafficLightPanel trafficLightPanel;
    private CarPanel carPanel;

    public void initPanel(BorderPane root, CarPanel carPanel, TrafficLightPanel trafficLightPanel) {
        this.carPanel = carPanel;
        this.trafficLightPanel = trafficLightPanel;

        panel = new HBox(10);

        startButton = createButton("Start", false, ControlPanelAction.START);
        stopButton = createButton("Stop", false, ControlPanelAction.STOP);
        panel.getChildren().addAll(startButton, stopButton);

        root.setBottom(panel);
    }

    private Button createButton(String buttonName, boolean isDisabled, ControlPanelAction action) {
        Button button = new Button(buttonName);
        button.setDisable(isDisabled);
        switch (action) {
            case START:
                button.setOnAction(e -> startSimulation());
                break;
            case STOP:
            default:
                button.setOnAction(e -> stopSimulation());
        }
        return button;
    }

    private void startSimulation() {

        // Start car movement and traffic lights
        if (!carPanel.isFutureNotNull() || carPanel.isNotActive()) {
            carPanel.startUpdate();
        }

        if (!trafficLightPanel.isFutureNotNull() || trafficLightPanel.isNotActive()) {
            trafficLightPanel.startUpdate();
        }

        startButton.setDisable(true);
        stopButton.setDisable(false);
    }

    private void stopSimulation() {
        // Stop everything and reset to initial states
        if (carPanel.isFutureNotNull() && carPanel.isActive()) {
            carPanel.stopUpdate();
        }

        if (trafficLightPanel.isFutureNotNull() && trafficLightPanel.isActive()) {
            trafficLightPanel.stopUpdate();
        }

        startButton.setDisable(false);
        stopButton.setDisable(true);
    }
}
