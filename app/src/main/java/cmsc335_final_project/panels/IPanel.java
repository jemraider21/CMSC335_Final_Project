package cmsc335_final_project.panels;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public interface IPanel {
    public void initPanel(BorderPane node);

    public void startUpdate();
}
