import java.util.List;
import java.util.stream.Collectors;
import java.util.function.UnaryOperator;
import static java.util.stream.Collectors.toList;

class Operator {

    public static UnaryOperator<List<String>> unaryOperator = u -> u.stream().distinct().collect(toList());

}
