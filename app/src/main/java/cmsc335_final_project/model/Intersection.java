package cmsc335_final_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Intersection {
    private int x;
    private int y;

    public void setBothXAndY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
