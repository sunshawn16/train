
import java.util.*;

public class Town implements Comparable<Town>
{
    public final String name;
    public ArrayList<Edge> adj = new ArrayList<Edge>();
    public double vertDistance = Double.POSITIVE_INFINITY;
    public Town previous;

    public Town(String argName)
    { 
        name = argName;
    }

    public String toString()
    {
        return name;
    }

    public int compareTo(Town other)
    {
        return Double.compare(vertDistance, other.vertDistance);
    }

    public boolean equals (Town other)
    {
        if(other.name.equals(name))
            return true;
        return false;
    }

    public void addEdge(Town target, double distance)
    {
        adj.add(new Edge(target, distance));
    }

}