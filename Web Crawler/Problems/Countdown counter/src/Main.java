class CountDownCounter {

    int count;

    public CountDownCounter(int initial) {
        this.count = initial;
    }

    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

}
