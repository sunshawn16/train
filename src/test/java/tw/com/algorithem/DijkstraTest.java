package tw.com.algorithem;

import org.junit.Test;
import tw.com.model.Town;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DijkstraTest {
    @Test
    public void testGetShortestRoute() throws Exception {
        Town townA = new Town("A");
        Town townB = new Town("B");
        Town townC = new Town("C");
        townA.addEdge(townB,5.0);
        townB.addEdge(townC, 4.0);
        townB.setPrevious(townA);
        townC.setPrevious(townB);
        Dijkstra dijkstra = new Dijkstra();
        List<Town> path = dijkstra.getShortestPathTo(townC);

        assertThat(path.size(),is(3));
    }
}