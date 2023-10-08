package cmsc335_final_project.model;

import lombok.Getter;

@Getter
public class TrafficLight {
    private TrafficLightState state;
    private int scheduledTimeToChangeState;

    public TrafficLight(TrafficLightState state) {
        this.state = state;
        this.scheduledTimeToChangeState = state.getDuration();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
        this.scheduledTimeToChangeState = state.getDuration();
    }

    public boolean updateState() {
        if (scheduledTimeToChangeState == 0) {
            state = state.getNextState();
            scheduledTimeToChangeState = state.getDuration();
            return true;
        } else {
            scheduledTimeToChangeState -= 1;
            return false;
        }
    }

    public boolean isSafeForCarsToPass() {
        return state == TrafficLightState.GREEN;
    }
}
