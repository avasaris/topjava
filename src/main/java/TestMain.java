import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestMain {
    private final static Map<Integer, Map<Integer, String>> repository = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Map<Integer, String> inner1 = new HashMap<>();
        inner1.put(1, "One");

        repository.put(10, inner1);

        repository.computeIfAbsent(10, (id) -> {
            System.out.println("===" + id);
            // System.out.println("==="+old);
//            Map<Integer, String> inner2 = new ConcurrentHashMap<>();
//            inner2.put(2, "Two");
//            return inner2;
            return new HashMap<>();
        }).put(2, "Two");

        System.out.println(repository);
    }
}
