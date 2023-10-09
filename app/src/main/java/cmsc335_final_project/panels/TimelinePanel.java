package cmsc335_final_project.panels;

import javafx.animation.Timeline;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public abstract class TimelinePanel {
    protected Timeline timeline;
    protected TimelineAction currentTimelineState;

    public void setTimelineState(TimelineAction action) {
        switch (action) {
            case ACTIVE:
                timeline.play();
                break;
            case PAUSE:
                timeline.pause();
            case STOP:
            default:
                timeline.stop();
        }
        currentTimelineState = action;
    }

    public boolean isTimelineNotNull() {
        return timeline != null;
    }

    public boolean isActive() {
        return currentTimelineState.equals(TimelineAction.ACTIVE);
    }

    public boolean isNotActive() {
        return currentTimelineState.equals(TimelineAction.PAUSE) || currentTimelineState.equals(TimelineAction.STOP);
    }

    public enum TimelineAction {
        ACTIVE, PAUSE, STOP
    }
}
