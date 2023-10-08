package cmsc335_final_project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrafficLightStateTest {
    @Test
    void testGetDuration() {
        Assertions.assertEquals(15, TrafficLightState.GREEN.getDuration());
        Assertions.assertEquals(5, TrafficLightState.YELLOW.getDuration());
        Assertions.assertEquals(10, TrafficLightState.RED.getDuration());
    }

    @Test
    void testGetNextState() {
        Assertions.assertEquals(TrafficLightState.YELLOW, TrafficLightState.GREEN.getNextState());
        Assertions.assertEquals(TrafficLightState.RED, TrafficLightState.YELLOW.getNextState());
        Assertions.assertEquals(TrafficLightState.GREEN, TrafficLightState.RED.getNextState());
    }
}
