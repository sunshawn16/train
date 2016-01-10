
import java.util.*;
import java.io.*;

public class Dijkstra {
    private static ArrayList<Town> towns = new ArrayList<Town>();

    public static void mapBuilder(Town source) {
        source.vertDistance = 0.0;
        PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
        townQueue.add(source);

        while (!townQueue.isEmpty()) {
            Town town = townQueue.poll();
            for (Edge edge : town.adj) {
                Town destinationTown = edge.destination;
                double distToU = town.vertDistance + edge.distance;
                if (distToU < destinationTown.vertDistance) {
                    townQueue.remove(destinationTown);
                    destinationTown.vertDistance = distToU;
                    destinationTown.previous = town;
                    townQueue.add(destinationTown);
                }
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

    public static int addTown(Town toAdd) {
        for (int a = 0; a < towns.size(); a++) {
            if (towns.get(a).equals(toAdd))
                return a;
        }
        towns.add(toAdd);
        return towns.size() - 1;
    }

    public static void main(String[] args) {
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
            return;
        }
        int sourceDex = addTown(source);
        mapBuilder(towns.get(sourceDex));

        for (Town town : towns) {
            if (town.vertDistance == Double.POSITIVE_INFINITY)
                System.out.println("Node " + town + " cannot be reached by node " + towns.get(sourceDex) + "!");
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
}