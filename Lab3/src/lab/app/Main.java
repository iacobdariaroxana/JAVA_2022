package lab.app;

import lab.model.*;

public class Main {
    public static void main(String[] args) {
        Network network = new Network();
        Computer c1 = new Computer("A", "00-00-00-00-00-00","v1", "127.0.0.1", 16);
        network.addNode(c1);
        Computer c2 = new Computer("B","00-00-00-00-00-07", "v6", "127.0.0.6", 8);
        network.addNode(c2);

        Router r1 = new Router("A", "00-00-00-00-00-02","v2", "127.0.0.2");
        network.addNode(r1);
        Router r2 = new Router("B", "00-00-00-00-00-05", "v5", "127.0.0.5");
        network.addNode(r2);

        Switch s1 = new Switch("A","00-00-00-00-00-04", "v3");
        network.addNode(s1);
        Switch s2 = new Switch("B","00-00-00-00-00-01", "v4");
        network.addNode(s2);
        System.out.println(network);

        c1.setCost(r1, 10);
        c1.setCost(s1, 50);
        r1.setCost(s1, 20);
        r1.setCost(s2, 20);
        r1.setCost(r2, 10);
        s1.setCost(s2, 20);
        s2.setCost(r2, 30);
        s2.setCost(c2, 10);
        r2.setCost(c2, 20);
    }
}
