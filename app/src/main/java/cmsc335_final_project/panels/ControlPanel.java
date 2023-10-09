package cmsc335_final_project.panels;

import cmsc335_final_project.panels.TimelinePanel.TimelineAction;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ControlPanel {
    private HBox panel;
    private Button startButton, pauseButton, continueButton, stopButton;
    private TrafficLightPanel trafficLightPanel;
    private CarPanel carPanel;

    public void initControlPanel(BorderPane root, CarPanel carPanel, TrafficLightPanel trafficLightPanel) {
        this.carPanel = carPanel;
        this.trafficLightPanel = trafficLightPanel;

        panel = new HBox(10);

        startButton = createButton("Start", false, Action.START);
        pauseButton = createButton("Pause", false, Action.PAUSE);
        continueButton = createButton("Continue", true, Action.CONTINUE);
        stopButton = createButton("Stop", false, Action.STOP);
        panel.getChildren().addAll(startButton, pauseButton, continueButton, stopButton);

        root.setBottom(panel);
    }

    private Button createButton(String buttonName, boolean isDisabled, Action action) {
        Button button = new Button(buttonName);
        button.setDisable(isDisabled);
        switch (action) {
            case START:
                button.setOnAction(e -> startSimulation());
                break;
            case PAUSE:
                button.setOnAction(e -> pauseSimulation());
                break;
            case CONTINUE:
                button.setOnAction(e -> continueSimulation());
                break;
            case STOP:
            default:
                button.setOnAction(e -> stopSimulation());
        }
        return button;
    }

    private void startSimulation() {

        // Start car movement and traffic lights
        if (!carPanel.isTimelineNotNull() || carPanel.isNotActive()) {
            carPanel.createNewTimeline();
            carPanel.startCarMovementUpdate();
        }

        if (!trafficLightPanel.isTimelineNotNull() || trafficLightPanel.isNotActive()) {
            trafficLightPanel.createNewTimeline();
            trafficLightPanel.startTrafficLightCycle();
        }

        // Set appropriate button states, e.g., enable pause and stop
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        continueButton.setDisable(true);
        stopButton.setDisable(false);
    }

    private void pauseSimulation() {
        TimelineAction action = TimelineAction.PAUSE;
        // Pause car movement and traffic lights
        if (carPanel.isTimelineNotNull() && carPanel.isActive()) {
            carPanel.setTimelineState(action);
        }

        if (trafficLightPanel.isTimelineNotNull() && trafficLightPanel.isActive()) {
            trafficLightPanel.setTimelineState(action);
        }

        // Set appropriate button states, e.g., enable continue
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        continueButton.setDisable(false);
        stopButton.setDisable(false);
    }

    private void continueSimulation() {
        TimelineAction action = TimelineAction.ACTIVE;

        // Continue car movement and traffic lights from where they paused
        if (carPanel.isTimelineNotNull() && carPanel.isNotActive()) {
            carPanel.setTimelineState(action);
        }

        if (trafficLightPanel.isTimelineNotNull() && trafficLightPanel.isNotActive()) {
            trafficLightPanel.setTimelineState(action);
        }

        // Set appropriate button states, e.g., enable pause and stop
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        continueButton.setDisable(true);
        stopButton.setDisable(false);
    }

    private void stopSimulation() {
        TimelineAction action = TimelineAction.STOP;

        // Stop everything and reset to initial states
        if (carPanel.isTimelineNotNull() && carPanel.isActive()) {
            carPanel.setTimelineState(action);
        }

        if (trafficLightPanel.isTimelineNotNull() && trafficLightPanel.isActive()) {
            trafficLightPanel.setTimelineState(action);
        }

        // Set appropriate button states, e.g., enable start
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        continueButton.setDisable(true);
        stopButton.setDisable(true);
    }

    private enum Action {
        START,
        PAUSE,
        CONTINUE,
        STOP
    }
}
