/*
    Hundred more such boring classes OR ...
    magic class for everything everybody is waiting for
*/
class Box<T> {

    private T dessert;

    public void put(T dessert) {
        this.dessert = dessert;
    }

    public T get() {
        return this.dessert;
    }

}

// Don't change classes below
class Cake { }

class Pie { }

class Tart { }