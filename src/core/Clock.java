package core;

public class Clock {
    private int cycle;

    public Clock() {
        this.cycle = 1;
    }

    public synchronized void tick() {
        cycle++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized int getCycle() {
        return cycle;
    }
}
