class Main {

    public static void main(String[] args) {
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();
        java.util.Scanner s = new java.util.Scanner(System.in);
        int n = s.nextInt();
        java.util.stream.IntStream.generate(s::nextInt).limit(n).forEach(stack::push);
        stack.forEach(System.out::println);
    }

}
