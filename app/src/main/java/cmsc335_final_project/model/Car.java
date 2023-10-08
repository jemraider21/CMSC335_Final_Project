package cmsc335_final_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Car {
    private int currentPosition;
    private int speed;
    private Intersection destinationIntersection;

    public void updatePosition() {
        currentPosition += speed;
    }

    public boolean hasReachedDestination() {
        return currentPosition == destinationIntersection.getX();
    }
}
