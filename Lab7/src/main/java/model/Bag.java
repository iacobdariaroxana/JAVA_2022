package model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Bag {
    private final Map<Tile, Integer> letters = new HashMap<>();
    int[] frequency = {9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
    int[] points = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    public Bag() {
        int k = 0;
        for (char c = 'a'; c < 'z'; c++) {
            Tile tile = new Tile(c, points[k]);
            letters.put(tile, frequency[k]);
            k++;
        }
    }

    private void updateLetters(Tile tileToUpdate){
        letters.put(tileToUpdate, letters.get(tileToUpdate) -1);
        if(letters.get(tileToUpdate) == 0){
            letters.remove(tileToUpdate);
        }
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (letters.isEmpty()) {
                break;
            }
            int index = ThreadLocalRandom.current().nextInt(letters.size());
            List<Tile> keysAsArray = new ArrayList<>(letters.keySet());
            extracted.add(keysAsArray.get(index));
            updateLetters(keysAsArray.get(index));
        }
        return extracted;
    }

    public Map<Tile,Integer> getLetters() {
        return letters;
    }
}
