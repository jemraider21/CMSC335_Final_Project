package cmsc335_final_project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {
    @Test
    void testConstructor() {
        Assertions.assertDoesNotThrow(() -> new Car(1, 2, new Intersection(0, 0)));

        Intersection testIntersection = new Intersection(2, 3);
        Assertions.assertDoesNotThrow(() -> new Car(5, 4, testIntersection));
    }

    @Test
    void testGetters() {
        int currentPosition = 3;
        int speed = 7;
        Intersection intersection = new Intersection(6, 7);
        Car testCar = new Car(currentPosition, speed, intersection);

        Assertions.assertEquals(currentPosition, testCar.getCurrentPosition());
        Assertions.assertEquals(speed, testCar.getSpeed());
        Assertions.assertEquals(intersection, testCar.getDestinationIntersection());
    }

    @Test
    void testSetter() {
        int ogCurrentPosition = 9;
        int ogSpeed = 8;
        Intersection ogIntersection = new Intersection(7, 6);

        Car car = new Car(ogCurrentPosition, ogSpeed, ogIntersection);
        Assertions.assertEquals(ogCurrentPosition, car.getCurrentPosition());
        Assertions.assertEquals(ogSpeed, car.getSpeed());
        Assertions.assertEquals(ogIntersection, car.getDestinationIntersection());

        int newCurrentPosition = 5;
        int newSpeed = 4;
        Intersection newIntersection = new Intersection(3, 2);

        car.setCurrentPosition(newCurrentPosition);
        car.setSpeed(newSpeed);
        car.setDestinationIntersection(newIntersection);

        Assertions.assertEquals(newCurrentPosition, car.getCurrentPosition());
        Assertions.assertEquals(newSpeed, car.getSpeed());
        Assertions.assertEquals(newIntersection, car.getDestinationIntersection());
    }

    @Test
    void testUpdatePosition() {
        int currentPosition = createRandomInt();
        int speed = createRandomInt();
        Intersection intersection = new Intersection(createRandomInt(), createRandomInt());
        Car car = new Car(currentPosition, speed, intersection);

        int expectedUpdatedPotition = currentPosition + speed;
        car.updatePosition();
        Assertions.assertEquals(expectedUpdatedPotition, car.getCurrentPosition());
    }

    @Test
    void testHasReachedDestination(){
        int currentPosition = 21;
        int speed = 15;
        Intersection intersection = new Intersection(92, 84);
        Car car = new Car(currentPosition, speed, intersection);
        Assertions.assertFalse(car.hasReachedDestination());

        car.setCurrentPosition(92);
        Assertions.assertTrue(car.hasReachedDestination());
    }

    private int createRandomInt() {
        return (int) Math.random();
    }
}
