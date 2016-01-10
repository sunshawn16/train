
import java.util.*;
import java.io.*;

public class Dijkstra {
    private static ArrayList<Town> towns = new ArrayList<Town>();

    public static void main(String[] args) {
        Town source;
        source = initMap();
        if (source == null) return;
        int sourceDex = addTown(source);
        MapBuilder.mapBuilder(towns.get(sourceDex));
        getRoute(sourceDex);
    }

    private static Town initMap() {
        Scanner in;
        Town source;
        try {
            in = new Scanner(new FileReader("src/main/resource/input.txt"));
            source = new Town("" + in.nextInt());
            in.nextLine();
            while (in.hasNextLine()) {
                String[] vals = in.nextLine().split("\\s+|\\t+");
                Town townA = new Town("" + vals[0]);
                Town townB = new Town("" + vals[1]);
                double weight = Integer.parseInt(vals[2]);
                int aDex = addTown(townA);
                int bDex = addTown(townB);
                towns.get(aDex).addEdge(towns.get(bDex), weight);
            }
        } catch (Exception e) {
            return null;
        }
        return source;
    }

    public static int addTown(Town toAdd) {
        for (int a = 0; a < towns.size(); a++) {
            if (towns.get(a).equals(toAdd))
                return a;
        }
        towns.add(toAdd);
        return towns.size() - 1;
    }


    private static void getRoute(int sourceDex) {
        for (Town town : towns) {
            if (town.vertDistance == Double.POSITIVE_INFINITY)
                System.out.println("Town " + town + " cannot be reached by node " + towns.get(sourceDex) + "!");
            else {
                System.out.println("From " + towns.get(sourceDex) + " to " + town.name + ":");
                System.out.print("The shortest path is: ");
                List<Town> path = getShortestPathTo(town);
                for (int pathTown = 0; pathTown < path.size() - 1; pathTown++)
                    System.out.print(path.get(pathTown) + " -> ");
                System.out.println(path.get(path.size() - 1) + ";");
                System.out.printf("The distance is: %.0f\n", town.vertDistance);
            }
        }
    }
    public static List<Town> getShortestPathTo(Town target) {
        List<Town> path = new ArrayList<Town>();
        for (Town town = target; town != null; town = town.previous)
            path.add(town);
        Collections.reverse(path);
        return path;
    }
}