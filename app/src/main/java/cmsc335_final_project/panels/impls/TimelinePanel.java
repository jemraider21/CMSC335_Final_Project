package cmsc335_final_project.panels.impls;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javafx.animation.Timeline;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public abstract class TimelinePanel {
    // protected Timeline timeline;
    protected TimelineAction currentTimelineState;
    protected ExecutorService executorService;
    protected Future<?> future;

    public void setTimelineState(TimelineAction action) {
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

    // public boolean isTimelineNotNull() {
    // return timeline != null;
    // }

    public boolean isActive() {
        return currentTimelineState.equals(TimelineAction.ACTIVE);
    }

    public boolean isNotActive() {
        return currentTimelineState.equals(TimelineAction.PAUSE) ||
                currentTimelineState.equals(TimelineAction.STOP);
    }

    public boolean isFutureNotNull() {
        return future != null;
    }

    public enum TimelineAction {
        ACTIVE, PAUSE, STOP
    }
}
