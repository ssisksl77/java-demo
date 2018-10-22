package algorithm.graph.count_the_level_nodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    int V;
    List<Integer>[] adj;

    public Graph(int v) {
        V = v;
        this.adj = new List[v];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    List<Integer> getAdj(int n) {
        return this.adj[n];
    }

    int bfsCountLevel(int start, int targetLevel) {
        boolean[] visited = new boolean[V];  // default false;
        int[] levelArray = new int[V];  // default 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        levelArray[start] = 0;

        int curNode;
        while(!q.isEmpty()) {
            curNode = q.poll();
            for (int i = 0; i < adj[curNode].size(); i++) {
                if (!visited[adj[curNode].get(i)]) {
                    visited[adj[curNode].get(i)] = true;
                    levelArray[adj[curNode].get(i)] = levelArray[curNode] + 1;
                    // System.out.println("visit " + adj[curNode].get(i));
                    q.add(adj[curNode].get(i));
                }
            }
        }
        int resCnt = 0;
        for (int i = 0; i < levelArray.length; i++) {
            if (levelArray[i] == targetLevel) {
                resCnt++;
            }
            // System.out.println(levelArray[i]);
        }
        return resCnt;
    }
}
