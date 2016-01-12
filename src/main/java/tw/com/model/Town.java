package tw.com.model;

import java.util.*;

import static java.lang.Double.*;

public class Town implements Comparable<Town>
{
    private final String name;
    private ArrayList<Edge> adj = new ArrayList<Edge>();
    private double vertDistance = POSITIVE_INFINITY;
    private Town previous;

    public String getName() {
        return name;
    }

    public ArrayList<Edge> getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Edge> adj) {
        this.adj = adj;
    }

    public double getVertDistance() {
        return vertDistance;
    }

    public void setVertDistance(double vertDistance) {
        this.vertDistance = vertDistance;
    }

    public Town getPrevious() {
        return previous;
    }

    public void setPrevious(Town previous) {
        this.previous = previous;
    }

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
        return compare(vertDistance, other.vertDistance);
    }

    public boolean equals (Town other)
    {
        return other.name.equals(name);
    }

    public void addEdge(Town target, double distance)
    {
        adj.add(new Edge(target, distance));
    }


}