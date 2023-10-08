package cmsc335_final_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrafficLightState {
    GREEN(15),
    YELLOW(5),
    RED(10);

    private int duration;

    public TrafficLightState getNextState() {
        switch (this) {
            case RED:
                return GREEN;
            case YELLOW:
                return RED;
            case GREEN:
                return YELLOW;
            default:
                throw new IllegalArgumentException("Invalid TrafficLightState: " + this);
        }
    }
}
