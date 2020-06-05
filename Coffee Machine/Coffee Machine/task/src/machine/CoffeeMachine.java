package machine;

import java.util.*;

public class CoffeeMachine {

    enum State { MAIN_MENU, BUYING, FILLING, EXIT }

    enum Coffee {
        ESPRESSO(250, 0, 16, 4), LATTE(350, 75, 20, 7), CAPPUCCINO(200, 100, 12, 6);

        private final int water;
        private final int milk;
        private final int beans;
        private final int cost;

        Coffee(int water, int milk, int beans, int cost) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cost = cost;
        }

        public int getWater() { return water; }
        public int getMilk() { return milk; }
        public int getBeans() { return beans; }
        public int getCost() { return cost; }
    }

    private int water = 400, milk = 540, beans = 120, cups = 9, money = 550, fillSteps = 0;
    private State state;
    private final String[] fillArray = {"", "Write how many disposable cups of coffee do you want to add:",
        "Write how many grams of coffee beans do you want to add:", "Write how many ml of milk do you want to add:",
        "\nWrite how many ml of water do you want to add:"};

    public CoffeeMachine() { setDefaultState(); }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CoffeeMachine machine = new CoffeeMachine();
        while (machine.state != State.EXIT) machine.processInput(scan.next());
    }

    private void processInput(String input) {
        switch (state) {
            case MAIN_MENU: processAction(input); break;
            case BUYING: makeDrink(input); break;
            case FILLING: fill(input); break;
            default: setDefaultState();
        }
    }

    private void processAction(String input) {
        switch (input) {
            case "buy": buy(); break;
            case "fill": fillInitialize(); break;
            case "take": take(); break;
            case "remaining": displayRemainingResources(); break;
            case "exit": shutdownCoffeeMachine(); break;
            default: setDefaultState();
        }
    }

    private void buy() {
        state = State.BUYING;
        System.out.println("\nWhat do you want to buy?");
        System.out.println("1 - espresso");
        System.out.println("2 - latte");
        System.out.println("3 - cappuccino");
        System.out.println("4 - back to main menu:");
    }

    private void makeDrink(String type) {
        Coffee coffee = Coffee.ESPRESSO;
        switch (type) {
            case "1": break;
            case "2": coffee = Coffee.LATTE; break;
            case "3": coffee = Coffee.CAPPUCCINO; break;
            default: setDefaultState(); return;
        }
        int wn = coffee.getWater(), mn = coffee.getMilk(), bn = coffee.getBeans();
        if (water >= wn && milk >= mn && beans >= bn && cups > 0) {
            water -= wn;
            milk -= mn;
            beans -= bn;
            money += coffee.getCost();
            cups--;
            System.out.println("\nI have enough ingredients, making you a coffee!");
        } else displayShortage(coffee);
        setDefaultState();
    }

    private void displayShortage(Coffee coffee) {
        System.out.printf("\nSorry, we don't have enough ingredients to make your %s.\n", coffee.name().toLowerCase());
        System.out.println("Please choose a different drink or try again later, thank you.");
    }

    private void fillInitialize() {
        fillSteps = 4;
        state = State.FILLING;
        System.out.println(fillArray[fillSteps]);
    }

    private void fill(String input) {
        switch (fillSteps) {
            case 1: cups += Integer.parseInt(input); break;
            case 2: beans += Integer.parseInt(input); break;
            case 3: milk += Integer.parseInt(input); break;
            case 4: water += Integer.parseInt(input);
        }
        if (--fillSteps > 0) System.out.println(fillArray[fillSteps]);
        else setDefaultState();
    }

    private void take() {
        System.out.printf("\nI gave you $%d\n", money);
        money = 0;
        setDefaultState();
    }

    private void displayRemainingResources() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of beans\n", beans);
        System.out.printf("%d of cups\n", cups);
        System.out.printf("%d of money\n", money);
        setDefaultState();
    }

    private void setDefaultState() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        state = State.MAIN_MENU;
    }

    private void shutdownCoffeeMachine() {
        System.out.println("Goodbye!");
        state = State.EXIT;
    }

}
