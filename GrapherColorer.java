import java.util.*;

/**
 *
 */
public class GrapherColorer {

    private int degree = 0;
    private static int nextColor = 0;

    public void colorGraph(List<GraphNode> graph) {
        if (graph == null || graph.isEmpty()) {
            return;
        }
        for (GraphNode n : graph) {
            if (n.neighbors.size() >= degree) {
                degree = n.neighbors.size() + 1;
            }
        }

        Queue<GraphNode> l = new LinkedList<>();

        for (GraphNode n : graph) {
            if (n.color == -1) {
                n.color = 0;
                l.add(n);
                while (!l.isEmpty()) {
                    GraphNode cu = l.remove();
                    for (GraphNode ne : cu.neighbors) {
                        if (ne.color == -1) {
                            ne.color = getNextColor(cu.color);
                            l.add(ne);
                        }
                    }
                }
            }
        }
    }

    private int getNextColor(int neighborColor) {
        if (nextColor + 1 > degree) {
            nextColor = 0;
        } else {
            nextColor += 1;
        }
        if (nextColor == neighborColor) {
            return getNextColor(neighborColor);
        } else {
            return nextColor;
        }
    }


    private static class GraphNode {
        public String label;
        public Set<GraphNode> neighbors = new HashSet<>();
        public int color = -1;
    }

    public static void main(String[] args) {
        GraphNode a = new GraphNode();
        GraphNode b = new GraphNode();
        GraphNode c = new GraphNode();
        GraphNode d = new GraphNode();
        GraphNode e = new GraphNode();
        GraphNode f = new GraphNode();
        a.label = "a";
        b.label = "b";
        c.label = "c";
        d.label = "d";
        e.label = "e";
        f.label = "f";
        a.neighbors.addAll(Arrays.asList(b, c, d));
        b.neighbors.addAll(Arrays.asList(a, e));
        c.neighbors.addAll(Arrays.asList(a, e));
        d.neighbors.addAll(Arrays.asList(a));
        List<GraphNode> graph = Arrays.asList(a, b, c, d, e, f);
        GrapherColorer grapherColorer = new GrapherColorer();
        grapherColorer.colorGraph(graph);
        for (GraphNode n : graph) {
            System.out.println(String.format("Color at node %s: %s", n.label, n.color));
        }
    }
}
