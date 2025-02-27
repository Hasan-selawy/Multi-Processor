package task;

import java.util.List;

public interface TaskScheduler {
    Task selectNextTask(List<Task> taskQueue);
}
