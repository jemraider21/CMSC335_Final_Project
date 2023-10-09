package cmsc335_final_project.panels.impls;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import cmsc335_final_project.enums.FutureAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public abstract class FuturePanel {
    protected FutureAction currentTimelineState;
    protected ExecutorService executorService;
    protected Future<?> future;

    public void setTimelineState(FutureAction action) {
        switch (action) {
            case ACTIVE:
                future.cancel(false);
                break;
            case PAUSE:
                break;
            case STOP:
            default:
                future.cancel(true);
        }
        currentTimelineState = action;
    }

    public boolean isActive() {
        return currentTimelineState.equals(FutureAction.ACTIVE);
    }

    public boolean isNotActive() {
        return currentTimelineState.equals(FutureAction.PAUSE) ||
                currentTimelineState.equals(FutureAction.STOP);
    }

    public boolean isFutureNotNull() {
        return future != null;
    }
}
