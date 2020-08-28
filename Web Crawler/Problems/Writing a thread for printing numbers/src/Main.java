class NumbersThread extends Thread {

    private final int from;
    private final int to;

    public NumbersThread(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; ++i) {
            System.out.println(i);
        }
    }

}
