package task;

import processor.Processor;
import java.util.List;

public class TaskAssigner {
    private final TaskScheduler scheduler;

    public TaskAssigner(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void assignTasks(List<Processor> processors, List<Task> taskQueue) {
        for (Processor p : processors) {
            if (p.isIdle() && !taskQueue.isEmpty()) {
                Task task = scheduler.selectNextTask(taskQueue);
                if (task != null) {
                    p.assignTask(task);
                    taskQueue.remove(task);
                }
            }
        }
    }
}
