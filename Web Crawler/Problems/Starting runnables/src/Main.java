class Starter {

    public static void startRunnables(Runnable[] runnables) {
        java.util.stream.Stream.of(runnables).parallel().forEach(r -> new Thread(r).start());
    }

}
