package task;

import java.util.List;
import java.util.stream.Collectors;

public class PriorityBasedScheduler implements TaskScheduler {
    @Override
    public Task selectNextTask(List<Task> taskQueue) {
        if (taskQueue.isEmpty()) return null;

        List<Task> highPriority = taskQueue.stream()
                .filter(t -> t.getPriority() == 1)
                .collect(Collectors.toList());

        List<Task> candidates = !highPriority.isEmpty() ? highPriority : taskQueue;
        if (candidates.isEmpty()) return null;

        return candidates.stream()
                .max((t1, t2) -> Integer.compare(t1.getExecutionTime(), t2.getExecutionTime()))
                .orElse(null);
    }
}
