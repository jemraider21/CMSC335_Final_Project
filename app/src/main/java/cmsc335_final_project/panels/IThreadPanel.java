package cmsc335_final_project.panels;

import java.util.concurrent.ExecutorService;

import javafx.scene.layout.VBox;

public interface IThreadPanel extends IPanel {
    public void initPanel(VBox centerVBox, ExecutorService executorService);

    public void createNewFuture();

    public void stopUpdate();
}
