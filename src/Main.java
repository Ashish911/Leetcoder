import java.util.HashMap;
import java.util.Map;

public class Main {

    public static Map<Integer, String> problemMap = new HashMap<>() {{

        put(200, "solutions.graphs.Number_Of_Islands");
        put(695, "solutions.graphs.Max_Area_Of_Island");
        put(133, "solutions.graphs.Clone_Graph");
        put(994, "solutions.graphs.Rotting_Oranges");
        put(417, "solutions.graphs.Pacific_Atlantic_Water_Flow");
        put(130, "solutions.graphs.Surrounded_Regions");
        put(207, "solutions.graphs.Course_Schedule");
        put(210, "solutions.graphs.Course_Schedule_Two");
    }};

    public static void main(String[] args) {
        String className = problemMap.get(210);

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
