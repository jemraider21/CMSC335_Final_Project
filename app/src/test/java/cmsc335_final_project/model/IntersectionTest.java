package cmsc335_final_project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntersectionTest {
    @Test
    void testConstructor() {
        Assertions.assertDoesNotThrow(() -> new Intersection(2, 4));
    }

    @Test
    void testGetters() {
        int x1 = 2;
        int y1 = 3;
        Intersection intersection1 = new Intersection(x1, y1);
        Assertions.assertEquals(x1, intersection1.getX());
        Assertions.assertEquals(y1, intersection1.getY());

        int x2 = 4;
        int y2 = 5;
        Intersection intersection2 = new Intersection(x2, y2);
        Assertions.assertEquals(x2, intersection2.getX());
        Assertions.assertEquals(y2, intersection2.getY());
    }

    @Test
    void testSetters() {
        int x1 = 2;
        int y1 = 4;

        Intersection intersection = new Intersection(x1, y1);
        Assertions.assertEquals(x1, intersection.getX());
        Assertions.assertEquals(y1, intersection.getY());

        int x2 = 3;
        int y2 = 5;
        intersection.setX(x2);
        intersection.setY(y2);
        Assertions.assertEquals(x2, intersection.getX());
        Assertions.assertEquals(y2, intersection.getY());

        int x3 = 6;
        int y3 = 7;
        intersection.setBothXAndY(x3, y3);
        Assertions.assertEquals(x3, intersection.getX());
        Assertions.assertEquals(y3, intersection.getY());
    }
}
