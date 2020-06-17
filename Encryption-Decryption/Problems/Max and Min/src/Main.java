import java.util.*;

class Finder {

    private final FindingStrategy strategy;

    public Finder(FindingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * It performs the search algorithm according to the given strategy
     */
    public int find(int[] numbers) {
        return this.strategy.getResult(numbers);
    }

}

interface FindingStrategy {

    /**
     * Returns search result
     */
    int getResult(int[] numbers);

}

class MaxFindingStrategy implements FindingStrategy {

    @Override
    public int getResult(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            max = Math.max(max, num);
        }
        return max;
    }

}

class MinFindingStrategy implements FindingStrategy {

    @Override
    public int getResult(int[] numbers) {
        int min = Integer.MAX_VALUE;
        for (int num : numbers) {
            min = Math.min(min, num);
        }
        return min;
    }

}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String[] elements = scanner.nextLine().split("\\s+");
        int[] numbers;
        if (elements[0].equals("EMPTY")) {
            numbers = new int[0];
        } else {
            numbers = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                numbers[i] = Integer.parseInt(elements[i]);
            }
        }
        final String type = scanner.nextLine();
        Finder finder = null;
        switch (type) {
            case "MIN":
                finder = new Finder(new MinFindingStrategy());
                break;
            case "MAX":
                finder = new Finder(new MaxFindingStrategy());
                break;
            default:
                break;
        }
        if (finder == null) {
            throw new RuntimeException("Unknown strategy type passed. Please, write to the author of the problem.");
        }
        System.out.println(finder.find(numbers));
    }

}