package cmsc335_final_project.enums;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrafficLightStatus {
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW),
    RED(Color.RED);

    Color color;

    public boolean isSafeToProceed() {
        return this.equals(GREEN) || this.equals(YELLOW);
    }
}