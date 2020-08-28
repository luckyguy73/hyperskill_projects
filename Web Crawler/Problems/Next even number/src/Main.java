import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = u -> u % 2 == 0 ? u + 2 : ++u;

}
