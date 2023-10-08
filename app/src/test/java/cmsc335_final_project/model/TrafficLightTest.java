package cmsc335_final_project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrafficLightTest {
    @Test
    void testConstructor() {
        Assertions.assertDoesNotThrow(() -> new TrafficLight(TrafficLightState.GREEN));
    }

    @Test
    void testGetters() {
        TrafficLightState state = TrafficLightState.RED;
        TrafficLight trafficLight = new TrafficLight(state);

        Assertions.assertEquals(state, trafficLight.getState());
        Assertions.assertEquals(state.getDuration(), trafficLight.getScheduledTimeToChangeState());
    }

    @Test
    void testSetters() {
        TrafficLightState ogState = TrafficLightState.GREEN;
        TrafficLight trafficLight = new TrafficLight(ogState);
        Assertions.assertEquals(ogState, trafficLight.getState());
        Assertions.assertEquals(ogState.getDuration(), trafficLight.getScheduledTimeToChangeState());

        TrafficLightState newState = TrafficLightState.YELLOW;
        trafficLight.setState(newState);

        Assertions.assertEquals(newState, trafficLight.getState());
        Assertions.assertEquals(newState.getDuration(), trafficLight.getScheduledTimeToChangeState());
    }

    @Test
    void testUpdateState() {
        TrafficLightState expectedResultState = TrafficLightState.YELLOW;

        TrafficLightState state = TrafficLightState.GREEN;
        TrafficLight trafficLight = new TrafficLight(state);
        while (!trafficLight.updateState()) {
            Assertions.assertEquals(state, trafficLight.getState());
        }

        Assertions.assertEquals(expectedResultState, trafficLight.getState());
        Assertions.assertEquals(expectedResultState.getDuration(), trafficLight.getScheduledTimeToChangeState());
    }

    @Test
    void testIsSafeForCarsToPass() {
        TrafficLight greenLight = new TrafficLight(TrafficLightState.GREEN);
        Assertions.assertTrue(greenLight.isSafeForCarsToPass());

        TrafficLight yellowLight = new TrafficLight(TrafficLightState.YELLOW);
        Assertions.assertFalse(yellowLight.isSafeForCarsToPass());

        TrafficLight redLight = new TrafficLight(TrafficLightState.RED);
        Assertions.assertFalse(redLight.isSafeForCarsToPass());
    }
}
