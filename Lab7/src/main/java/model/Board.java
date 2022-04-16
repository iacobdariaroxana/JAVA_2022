package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Player, List<String>> words = new HashMap<>();

    public synchronized void addWord(Player player, String word) {
        if (words.containsKey(player)) {
            words.get(player).add(word);
//            System.out.println(player.getName() + ": " + word);
        } else {
            words.put(player, new ArrayList<>());
            words.get(player).add(word);
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "words=" + words +
                '}';
    }
}
