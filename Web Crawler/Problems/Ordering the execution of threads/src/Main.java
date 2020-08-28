import java.util.List;

class Invoker {

    public static void invokeMethods(Thread t1, Thread t2, Thread t3) throws InterruptedException {
        for (var t : List.of(t3, t2, t1)) {
            startThread(t);
        }
    }

    private static void startThread(Thread t) throws InterruptedException {
        t.start();
        t.join();
    }

}
