package processor;

import task.Task;

public class Processor {
    private final int id;
    private Task currentTask;

    public Processor(int id) {
        this.id = id;
        this.currentTask = null;
    }

    public int getId() { return id; }
    public boolean isIdle() { return currentTask == null; }

    public void assignTask(Task task) {
        this.currentTask = task;
    }

    public Task process() {
        if (currentTask == null) return null;

        currentTask.execute();
        if (currentTask.isCompleted()) {
            Task completedTask = currentTask;
            currentTask = null;
            return completedTask;
        }
        return null;
    }

    @Override
    public String toString() {
        return isIdle() ? "_" : currentTask.toString();
    }

    public Task getCurrentTask() {
        return currentTask;
    }
}
