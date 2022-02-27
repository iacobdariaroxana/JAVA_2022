package homework;

import java.util.ArrayList;
/*
Implement an efficient algorithm that determines, if possible, a subset of distinct words W1,W2,...,Wk (from the ones that you have generated) such that k ≥ 3 and Wi and Wi+1 are neighbors, for all i in [1..k], where Wk+1=W1.
Can you find the largest possible k?
R:  The largest possible k cannot be found through an efficient algorithm because that will lead to finding a hamiltonian cycle in a graph, a problem which is proven that is NP-complete.
    A subset of words with the properties presented above reduces to the problem of finding a cycle in a graph( every word corresponds to a vertex, for example words[0] corresponds to vertex 0).
    For finding a cycle in a graph I will use a DFS traversal.
    The complexity of the algorithm is O(n+m) where n is the number of words(vertices) and m is the number of edges( given by the presence of a common letter between two words ).
*/

/**
 * Main class of the application
 * @author Daria-Roxana
 */
class Bonus {
    private ArrayList<Integer>[] adjacencyList;
    private String[] words;
    private int startingNode;
    private int finalNode;
    private int[] parents;
    public Bonus(String[] words, boolean[][] adjacency) {
        parents = new int[words.length]; // an array which keeps the parent node of every node
        this.words = words;
        // create the adjacency list for each vertex(word)
        adjacencyList = new ArrayList[words.length];
        for( int i = 0; i < words.length; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for( int i = 0; i < words.length; i++) {
            for (int j = 0 ; j < words.length; j++) {
                if(adjacency[i][j]){
                    adjacencyList[i].add(j);
                }
            }
        }
    }
    public boolean dfs(int node, boolean[] visited, int parent) {
        // mark the node as visited
        visited[node] = true;
        // traverse the neighbors list
        for (Integer neighbor : adjacencyList[node]) {
            if (!visited[neighbor]) {
                // if the node is not visited, continue dfs traversal
                parents[neighbor] = node; // update the parents array
                if (dfs(neighbor, visited, node)) {
                    return true; // a cycle was found
                }
            } else if (neighbor != parent) {
                // if the neighbor is different from parent then it means that a backward edge was found
                startingNode = neighbor; // the starting node of the cycle
                finalNode = node; // the ending node of the cycle
                return true; // a cycle was found
            }
        }
        return false; // no cycle found
    }
    public boolean findCycle() {
        boolean[] visited = new boolean[words.length];
        for(int i = 0; i < words.length; i++) {
            visited[i] = false;
        }
        for(int i = 0; i < words.length; i++) {
            // if the node is not visited, start dfs from that node
            if(!visited[i] && dfs(i, visited, -1)) {
                    return true;
            }
        }
        return false;
    }
    public void displaySubset() {
        System.out.println("A subset of words is:");
        int temporary = finalNode;
        while(temporary != startingNode)
        {
            System.out.print(words[temporary]+", ");
            temporary = parents[temporary];
        }
        System.out.println(words[startingNode]);
    }
}
