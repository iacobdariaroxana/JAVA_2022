package lab.alg;

import java.util.Arrays;

public class Prim {
    private int[][] adjacencyCost;
    private int numberOfNodes;
    private int[][] edgeNodes;

    public Prim(int[][] adjacency) {
        this.adjacencyCost = adjacency;
        numberOfNodes = adjacencyCost.length;
        edgeNodes = new int[numberOfNodes-1][2];
    }

    public int findMinimumKeyNode(int[] key, Boolean[] mst) {
        int min = Integer.MAX_VALUE, minNode = -1;
        for (int i = 0; i < numberOfNodes; i++) {
            if (!mst[i] && key[i] < min) {
                min = key[i];
                minNode = i;
            }
        }
        return minNode;
    }

    public int[][] findMinimumSpanningTree() {
        int[] parent = new int[numberOfNodes];
        int[] key = new int[numberOfNodes];
        Boolean mst[] = new Boolean[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            key[i] = Integer.MAX_VALUE;
            mst[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;
        for (int i = 0; i < numberOfNodes - 1; i++) {
            int node = findMinimumKeyNode(key, mst);
            mst[node] = true;
            for (int j = 0; j < numberOfNodes; j++) {
                if (adjacencyCost[node][j] != 0 && !mst[j] && adjacencyCost[node][j] < key[j]) {
                    parent[j] = node;
                    key[j] = adjacencyCost[node][j];
                }
            }
        }
        return getMST(parent);
    }

    public int[][] getMST(int[] parent) {
        int count = 0;
        for (int i = 1; i < numberOfNodes; i++) {
            edgeNodes[count][0] = parent[i];
            edgeNodes[count++][1] = i;
        }
        return edgeNodes;
    }

    public int getMSTCost(){
        int cost = 0;
        for(int[] row:edgeNodes){
            cost+= adjacencyCost[row[0]][row[1]];
        }
        return cost;
    }
    public void showMatrix() {
        for (int[] row : adjacencyCost)
            System.out.println(Arrays.toString(row));
    }
}
