package core;

import processor.Processor;
import task.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Simulator {
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String RED = "\u001B[31m";
    private static final String ORANGE = "\u001B[38;5;214m";
    private static final String GREEN = "\u001B[32m";
    private static final String WHITE = "\u001B[37m";

    private final int totalCycles;
    private final List<Task> allTasks;
    private final List<Task> taskQueue = new ArrayList<>();
    private final List<Processor> processors;
    private final TaskAssigner assigner;
    private final Clock clock = new Clock();

    public Simulator(int numProcessors, int totalCycles, List<Task> tasks) {
        this.totalCycles = totalCycles;
        this.allTasks = tasks;
        this.processors = new ArrayList<>();
        this.assigner = new TaskAssigner(new PriorityBasedScheduler());

        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor(i + 1));
        }
    }

    public void run() {
        printProcessorHeader();

        while (clock.getCycle() <= totalCycles) {
            int currentCycle = clock.getCycle();

            List<Task> newTasks = allTasks.stream()
                    .filter(t -> t.getCreationTime() == currentCycle)
                    .collect(Collectors.toList());
            taskQueue.addAll(newTasks);

            assigner.assignTasks(processors, taskQueue);

            List<Task> completedTasks = new ArrayList<>();
            for (Processor p : processors) {
                Task completed = p.process();
                if (completed != null) {
                    completedTasks.add(completed);
                }
            }

            printCycleReport(currentCycle, newTasks, completedTasks);
            clock.tick();
        }

        printProcessorFooter();
    }

    private void printProcessorHeader() {
        System.out.print("\n    ");
        for (Processor p : processors) {
            System.out.printf(BLUE + "P%-3d " + RESET, p.getId());
        }
        System.out.println("\n   ---------------------");
    }

    private void printProcessorFooter() {
        System.out.print("\n    ");
        for (Processor p : processors) {
            System.out.printf(BLUE + "P%-3d " + RESET, p.getId());
        }
        System.out.println();
    }

    private void printCycleReport(int cycle, List<Task> newTasks, List<Task> completedTasks) {
        System.out.printf("\n%sC%d%s  | ", RED, cycle, RESET);
        for (Processor p : processors) {
            Task task = p.getCurrentTask();
            if (task != null) {
                System.out.printf(GREEN + "%-3s " + RESET, task);
            } else {
                System.out.print("_   ");
            }
        }
        System.out.print("| ");
        if (!newTasks.isEmpty()) {
            System.out.printf(ORANGE + "Tasks: %s Created!" + RESET, newTasks);
        }
        if (!completedTasks.isEmpty()) {
            System.out.printf(" | " + WHITE + "Completed: %s" + RESET, completedTasks);
        }
        System.out.println();
    }
}
