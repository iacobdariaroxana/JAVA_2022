package lab.app;

import lab.alg.FloydWarshall;
import lab.model.*;

public class Main {
    public static void main(String[] args) {

        Network network = new Network();
        Computer c1 = new Computer("A", "00-00-00-00-00-00", "v1", "127.0.0.1", 16);
        Computer c2 = new Computer("B", "00-00-00-00-00-07", "v6", "127.0.0.6", 8);
        Router r1 = new Router("A", "00-00-00-00-00-02", "v2", "127.0.0.2");
        Router r2 = new Router("B", "00-00-00-00-00-05", "v5", "127.0.0.5");
        Switch s1 = new Switch("A", "00-00-00-00-00-04", "v3");
        Switch s2 = new Switch("B", "00-00-00-00-00-01", "v4");

        network.addNode(c1);
        network.addNode(r1);
        network.addNode(s1);
        network.addNode(s2);
        network.addNode(r2);
        network.addNode(c2);

        c1.setCost(r1, 10);
        c1.setCost(s1, 50);
        r1.setCost(s1, 20);
        r1.setCost(s2, 20);
        r1.setCost(r2, 10);
        s1.setCost(s2, 20);
        s2.setCost(r2, 30);
        s2.setCost(c2, 10);
        r2.setCost(c2, 20);

        System.out.println(network);
        network.setIdentifiableNodes();
        network.displayIdentifiableNodes();

        FloydWarshall fw = new FloydWarshall(network.getNodes());
        fw.findAllShortestTimes();
        fw.getAllShortestTimes(network.getIdentifiableNodes());

    }
}
