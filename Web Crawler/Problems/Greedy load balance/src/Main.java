import java.util.*;

class Main {

    private static final Queue<Task> ONE = new ArrayDeque<>();
    private static final Queue<Task> TWO = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        while (n-- > 0) {
            assignTask(new Task(s.nextInt(), s.nextInt()));
        }
        printQueues();
        s.close();
    }

    private static void assignTask(Task task) {
        int sumOne = ONE.stream().mapToInt(Task::getLoad).sum();
        int sumTwo = TWO.stream().mapToInt(Task::getLoad).sum();

        if (sumOne <= sumTwo) {
            ONE.offer(task);
        } else {
            TWO.offer(task);
        }
    }

    private static void printQueues() {
        ONE.forEach(f -> System.out.printf("%s ", f.getId()));
        System.out.println();
        TWO.forEach(f -> System.out.printf("%s ", f.getId()));
    }

}

class Task {

    private final int id;
    private final int load;

    public Task(int id, int load) {
        this.id = id;
        this.load = load;
    }

    public int getId() {
        return id;
    }

    public int getLoad() {
        return load;
    }

}
