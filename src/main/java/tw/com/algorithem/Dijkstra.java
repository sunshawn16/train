package tw.com.algorithem;

import tw.com.Utils.MapBuilder;
import tw.com.model.Town;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.POSITIVE_INFINITY;
import static java.util.Collections.reverse;

public class Dijkstra {

    public static void main(String[] args) throws IOException {
        List<Town> towns = new ArrayList<>();
        Dijkstra dijkstra = new Dijkstra();
        Town source = MapBuilder.initMap(towns);
        dijkstra.getRoute(source,towns);
    }

    public void getRoute(Town source, List<Town> towns) {
        for (Town town : towns) {
            diaplayEachTown(source, town);
        }
    }

    private void diaplayEachTown(Town source, Town town) {
        if (town.getVertDistance() == POSITIVE_INFINITY)
            System.out.println("Town " + town + " cannot be reached by node " + source.getName() + "!");
        else {
            System.out.println("From " + source.getName() + " to " + town.getName() + ":");
            System.out.print("The shortest path is: ");
            List<Town> path = getShortestPathTo(town);
            for (int pathTown = 0; pathTown < path.size() - 1; pathTown++)
                System.out.print(path.get(pathTown) + " -> ");
            System.out.println(path.get(path.size() - 1) + ";");
            System.out.printf("The distance is: %.0f\n", town.getVertDistance());
        }
    }

    public List<Town> getShortestPathTo(Town target) {
        List<Town> path = new ArrayList<Town>();
        for (Town town = target; town != null; town = town.getPrevious())
            path.add(town);
        reverse(path);
        return path;
    }
}