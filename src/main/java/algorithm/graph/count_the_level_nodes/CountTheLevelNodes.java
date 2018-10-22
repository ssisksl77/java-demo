package algorithm.graph.count_the_level_nodes;

public class CountTheLevelNodes {
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1);  // level1
        g.addEdge(0, 2);  // level1
        g.addEdge(1, 3);  // level2
        g.addEdge(2, 4);  // level2
        g.addEdge(2, 5);  // level2

        int targegLevel = 2;
        System.out.println(g.bfsCountLevel(0, 2));

    }
}



