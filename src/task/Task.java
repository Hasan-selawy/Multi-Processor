package task;

public class Task {
    private final int id;
    private final int creationTime;
    private final int executionTime;
    private final int priority;
    private int remainingTime;

    public Task(int id, int creationTime, int executionTime, int priority) {
        this.id = id;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.remainingTime = executionTime;
    }

    public int getId() { return id; }
    public int getCreationTime() { return creationTime; }
    public int getExecutionTime() { return executionTime; }
    public int getPriority() { return priority; }
    public int getRemainingTime() { return remainingTime; }

    public void execute() {
        remainingTime--;
    }

    public boolean isCompleted() {
        return remainingTime == 0;
    }

    @Override
    public String toString() {
        return "T" + id;
    }
}
