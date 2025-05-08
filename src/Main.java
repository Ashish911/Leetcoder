import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<Integer, String> problemMap = new HashMap<>() {{

        put(200, "solutions.graphs.Number_Of_Islands");
        put(695, "solutions.graphs.Max_Area_Of_Island");
        put(133, "solutions.graphs.Clone_Graph");
    }};

    public static void main(String[] args) {
        String className = problemMap.get(133);

        if (className == null) {
            System.out.println("Problem not found.");
            return;
        }

        try {
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            clazz.getMethod("run").invoke(instance);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
