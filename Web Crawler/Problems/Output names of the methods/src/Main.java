class CreateInstance {

    public static SuperClass create() {
        return new SuperClass() {
            @Override
            public void method2() {
                System.out.println("method2");
            }

            @Override
            public void method3() {
                System.out.println("method3");
            }
        };
    }

}
