package tw.com.Utils;

import tw.com.model.Edge;
import tw.com.model.Town;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;

import static java.nio.file.Files.readAllLines;

public class MapBuilder {
    public static Town initMap(List<Town> towns) throws IOException {
        List<String> lines;
        MapBuilder mapBuilder = new MapBuilder();
        lines = readAllLines(Paths.get("src/main/resource/input.txt"));
        for (String line : lines) {
            String[] vals = line.split("\\s+");
            Town townA = new Town(vals[0]);
            Town townB = new Town(vals[1]);
            double weight = Integer.parseInt(vals[2]);
            Town aDex = mapBuilder.addTownToMap(townA, towns);
            Town bDex = mapBuilder.addTownToMap(townB, towns);
            aDex.addEdge(bDex, weight);
        }
        Town source = towns.get(0);
        mapBuilder.townBuilder(source);
        return source;
    }

    public void townBuilder(Town source) {
        source.setVertDistance(0.0);
        PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
        townQueue.add(source);
        while (!townQueue.isEmpty()) {
            Town startTown = townQueue.poll();
            distanceAndPreBuilder(townQueue, startTown);
        }
    }

    private void distanceAndPreBuilder(PriorityQueue<Town> townQueue, Town startTown) {
        for (Edge edge : startTown.getAdj()) {
            Town destinationTown = edge.getDestination();
            double distToStart = startTown.getVertDistance() + edge.getDistance();
            if (distToStart < destinationTown.getVertDistance()) {
                townQueue.remove(destinationTown);
                destinationTown.setVertDistance(distToStart);
                destinationTown.setPrevious(startTown);
                townQueue.add(destinationTown);
            }
        }
    }

    public Town addTownToMap(Town toAdd, List<Town> towns) {
//        return towns.stream()
//                .filter(town->town.equals(toAdd))
//                .
        for (Town town : towns) {
            if (town.equals(toAdd))
                return town;
        }
        towns.add(toAdd);
        return towns.get(towns.size() - 1);
    }
}
