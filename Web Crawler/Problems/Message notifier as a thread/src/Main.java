class MessageNotifier extends Thread {

    private final String msg;
    private final int repeats;

    public MessageNotifier(String msg, int repeats) {
        this.msg = msg;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeats; ++i) {
            System.out.println(msg);
        }
    }

}
