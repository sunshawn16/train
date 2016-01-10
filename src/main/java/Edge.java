
public class Edge
{
    public Town destination;
    public double distance;

    public Edge(Town target, double distance)
    { 
        this.destination = target;
        this.distance = distance;
    }
}