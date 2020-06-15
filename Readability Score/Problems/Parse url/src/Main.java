import java.util.*;

class Main {

    public static void main(String[] args) {
        String[] params = new Scanner(System.in).nextLine().split("\\?")[1].split("&");
        Map<String, String> map = new LinkedHashMap<>();
        for (String param : params) {
            String[] item = param.split("=");
            map.put(item[0], item.length < 2 ? "not found" : item[1]);
        }
        map.forEach((k, v) -> System.out.printf("%s : %s\n", k, v));
        if (map.containsKey("pass")) {
            System.out.printf("password : %s", map.get("pass"));
        }
    }

}
