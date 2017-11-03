import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {
	
	public int compare(Vertex x, Vertex y) {
        if (x.getTotalCost() < y.getTotalCost()) {
            return -1;
        }
        if (x.getTotalCost() > y.getTotalCost()) {
            return 1;
        }
        return 0;
    }
}
