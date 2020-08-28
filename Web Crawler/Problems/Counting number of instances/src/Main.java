class ClassCountingInstances {

    private static long numberOfInstances;

    public ClassCountingInstances() {
        increment();
    }

    private static synchronized void increment() {
        numberOfInstances++;
    }

    public static synchronized long getNumberOfInstances() {
        return numberOfInstances;
    }

}
