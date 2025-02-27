
import core.Simulator;
import task.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processors: ");
        int numProcessors = scanner.nextInt();

        System.out.print("Enter the total number of cycles: ");
        int totalCycles = scanner.nextInt();

        String taskFile = "C:/users/hasan/OneDrive/Desktop/tasks.txt";
        List<Task> tasks = readTasks(taskFile);

        new Simulator(numProcessors, totalCycles, tasks).run();

        scanner.close();
    }

    private static List<Task> readTasks(String filename) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int numTasks = Integer.parseInt(br.readLine());
            for (int i = 0; i < numTasks; i++) {
                String[] parts = br.readLine().split(" ");
                int creationTime = Integer.parseInt(parts[0]);
                int executionTime = Integer.parseInt(parts[1]);
                int priority = Integer.parseInt(parts[2]);
                tasks.add(new Task(i + 1, creationTime, executionTime, priority));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
