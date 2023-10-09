package cmsc335_final_project.panels.impls;

import java.text.SimpleDateFormat;
import java.util.Date;

import cmsc335_final_project.panels.IPanel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import static javafx.animation.Timeline.INDEFINITE;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TimePanel implements IPanel {
    private Label timeLabel; // To display the current time

    public void initPanel(BorderPane root) {
        // Create the label to show time
        timeLabel = new Label();
        updateTimeLabel();

        // Add time label to the top of the main layout
        root.setTop(timeLabel);
    }

    public void updateTimeLabel() {
        // Use SimpleDateFormat to format the current time
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timeLabel.setText(sdf.format(new Date()));
    }

    public void startUpdate() {
        // Create a timeline to update the time label every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTimeLabel()));
        timeline.setCycleCount(INDEFINITE);
        timeline.play();
    }
}
