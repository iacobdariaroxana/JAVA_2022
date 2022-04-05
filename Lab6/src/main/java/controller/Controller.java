package controller;

import model.Node;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements Serializable {
    private List<Node> nodes;
    private Map<Node, Color> clickedNodes = new HashMap<>();
    private Map<Node, List<Node>> adjacency = new HashMap<>();
    private Node clickedNode;
    private Node previousNode;
    private int turn;
    private Color color;
    private boolean flagStart;

    public Controller() {
        setNodes(new ArrayList<>());
        setFlagStart(true);
        turn = 0;
    }

    public void createLink(int x1, int y1, int x2, int y2) {
        Node firstNode = new Node(x1, y1);
        Node secondNode = new Node(x2, y2);
        if (!getNodes().contains(firstNode)) {
            getNodes().add(firstNode);
            firstNode.addNeighbor(secondNode);
        } else {
            getNodes().get(getNodes().indexOf(firstNode)).addNeighbor(secondNode);
        }
        if (!getNodes().contains(secondNode)) {
            getNodes().add(secondNode);
            secondNode.addNeighbor(firstNode);
        } else {
            getNodes().get(getNodes().indexOf(secondNode)).addNeighbor(firstNode);
        }

        if (getAdjacency().containsKey(firstNode)) {
            getAdjacency().get(firstNode).add(secondNode);
        } else {
            getAdjacency().put(firstNode, new ArrayList<>());
            getAdjacency().get(firstNode).add(secondNode);
        }
        if (getAdjacency().containsKey(secondNode)) {
            getAdjacency().get(secondNode).add(firstNode);
        } else {
            getAdjacency().put(secondNode, new ArrayList<>());
            getAdjacency().get(secondNode).add(firstNode);
        }
    }

    private boolean isInsideCircle(int x, int y, int centerX, int centerY) {
        return ((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)) <= 100;
    }

    private Node findNode(int x, int y) {
        for (Node node : getNodes()) {
            if (isInsideCircle(x, y, node.getX(), node.getY())) {
                clickedNode = node;
                color = (turn == 0 ? new Color(228, 88, 88) : new Color(98, 70, 234));
                return node;
            }
        }
        return null;
    }

    private void updateNeighbors(Node nodeToRemove) {
        for(Node node: adjacency.keySet()){
            adjacency.get(node).remove(nodeToRemove);
        }
    }

    private boolean startGame(int x, int y) {
        if ((previousNode = findNode(x, y)) != null) {
            clickedNodes.put(previousNode, color);
            updateNeighbors(previousNode);
            flagStart = false;
            return true;
        }
        return false;
    }

    public int validate(int x, int y) {
        if (flagStart) {
            if (startGame(x, y)) {
                turn = Math.abs(1 - turn);
                flagStart = false;
                return 1;
            } else {
                return 0;
            }
        }
        if ((clickedNode = findNode(x, y)) != null) {
            if (getAdjacency().get(previousNode).contains(clickedNode)) {
                if (adjacency.get(clickedNode).isEmpty()) {
                    System.out.println("GAME OVER");
                    return turn + 3;
                }
                updateNeighbors(clickedNode);
                previousNode = clickedNode;
                clickedNodes.put(clickedNode, color);
                turn = Math.abs(1 - turn);
                return 1;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    public Color getColor() {
        return color;
    }

    public Node getClickedNode() {
        return clickedNode;
    }

    public void deleteNodes() {
        nodes.clear();
        adjacency.clear();
        clickedNodes.clear();
    }

    public void showNeighborsOfNode(Node node) {
        node.getNeighbors().forEach(System.out::println);
    }

    public void setFlagStart(boolean flagStart) {
        this.flagStart = flagStart;
        this.turn = 0;
    }

    public void test() {
        for (Node node : getNodes()) {
            System.out.println("Supreme " + node);
            showNeighborsOfNode(node);
        }
    }

    @Override
    public String toString() {
        return "Controller{" +
                "nodes=" + getNodes() +
                '}' + "\nNumber of nodes: " + getNodes().size();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public Map<Node, Color> getClickedNodes() {
        return clickedNodes;
    }

    public void setClickedNodes(Map<Node, Color> clickedNodes) {
        this.clickedNodes = clickedNodes;
    }

    public Map<Node, List<Node>> getAdjacency() {
        return adjacency;
    }

    public void setAdjacency(Map<Node, List<Node>> adjacency) {
        this.adjacency = adjacency;
    }
}
