public class Main {

    public static void main(String[] args) {
        new Thread(new RunnableWorker(), "worker-firefighter").start();
        new Thread(new RunnableWorker(), "worker-policeman").start();
        new Thread(new RunnableWorker(), "worker-doctor").start();
    }

}

class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();
        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        }
    }

}
