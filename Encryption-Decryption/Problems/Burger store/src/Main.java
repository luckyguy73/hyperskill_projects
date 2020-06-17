import java.util.Arrays;

class TestDrive {

    public static void main(String[] args) {
        String[] types = {"Chinese", "American", "Russian"};
        BurgerFactory factory = new BurgerStore();
        Arrays.stream(types).forEach(factory::orderBurger);
    }

}

abstract class BurgerFactory {

    abstract Burger createBurger(String type);

    @SuppressWarnings("UnusedReturnValue")
    Burger orderBurger(String type) {
        Burger burger = createBurger(type);
        if (burger == null) {
            System.out.println("Sorry, we are not able to create this kind of burger\n");
            return null;
        }
        System.out.println("Making a " + burger.getName());
        burger.putBun();
        burger.putCutlet();
        burger.putSauce();
        System.out.println("Done a " + burger.getName() + "\n");
        return burger;
    }

}

class BurgerStore extends BurgerFactory {

    @Override
    Burger createBurger(String type) {
        String name = type + " Burger";
        switch (type.toLowerCase()) {
            case "chinese": return new ChineseBurger(name);
            case "american": return new AmericanBurger(name);
            case "russian": return new RussianBurger(name);
            default: return null;
        }
    }

}

class Burger {

    private final String name;

    Burger(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void putBun() {
        System.out.println("Putting bun");
    }

    void putCutlet() {
        System.out.println("Putting cutlet");
    }

    void putSauce() {
        System.out.println("Putting sauce");
    }

}

class ChineseBurger extends Burger {

    public ChineseBurger(String name) {
        super(name);
    }

}

class AmericanBurger extends Burger {

    public AmericanBurger(String name) {
        super(name);
    }

}

class RussianBurger extends Burger {

    public RussianBurger(String name) {
        super(name);
    }

}