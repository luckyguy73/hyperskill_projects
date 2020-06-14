import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class Operator {

    public static UnaryOperator<List<String>> unaryOperator = strings -> {
        Set<String> set = new HashSet<>(strings);
        return List.of(String.join(" ", set));
    };

}
