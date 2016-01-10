import java.util.PriorityQueue;

public class MapBuilder {
    public static void mapBuilder(Town source) {
        source.vertDistance = 0.0;
        PriorityQueue<Town> townQueue = new PriorityQueue<Town>();
        townQueue.add(source);

        while (!townQueue.isEmpty()) {
            Town startTown = townQueue.poll();
            for (Edge edge : startTown.adj) {
                Town destinationTown = edge.destination;
                double distToStart = startTown.vertDistance + edge.distance;
                if (distToStart < destinationTown.vertDistance) {
                    townQueue.remove(destinationTown);
                    destinationTown.vertDistance = distToStart;
                    destinationTown.previous = startTown;
                    townQueue.add(destinationTown);
                }
            }
        }
    }
}
