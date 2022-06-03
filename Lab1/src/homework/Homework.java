package homework;
import java.util.Random;

/**
 * Main class of the application
 * @author Daria-Roxana
 */
public class Homework {
    private Random rand = new Random(); // creates a new random number generator.
    public String createRandomWord(int len, char[] alphabet) {
        StringBuilder word = new StringBuilder(); // it's mutable
        for (int i = 0; i < len; i++) {
            int k = rand.nextInt(alphabet.length); // returns a pseudorandom, uniformly distributed(the probability of each occurring is the same) int value between 0 (inclusive) and the specified value (exclusive), drawn from this random number generator's sequence
            word.append(alphabet[k]);
        }
        return word.toString(); // returns a string containing the characters in this sequence in the same order as this sequence.
    }
    public String[] generateWords(int n, int p, char[] alphabet, Homework h) {
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = h.createRandomWord(p, alphabet);
        }
        return words;
    }
    public void displayWords(String[] words) {
        System.out.println("The generated array of strings contains the following words: ");
        for (String word : words) {
            System.out.println(word + " ");
        }
    }
    public boolean[][] generateAdjacency(int n, String[] words) {
        boolean[][] adjacency = new boolean[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                adjacency[i][j] = adjacency[j][i] = false; //initialize with false, later update if it's true
                for (int k = 0; k < words[i].length(); k++) {
                    String charToString = Character.toString(words[i].charAt(k)); // returns a String object representing this Character's value.
                    if (words[j].contains(charToString)) {
                        adjacency[i][j] = adjacency[j][i] = true;
                        break; // found a common letter, move to next word
                    }
                }
            }
        }
        return adjacency;
    }
    public String[][] generateNeighbors(int n, String[] words, boolean[][] adjacency) {
        String[][] neighbors = new String[n][];
        for(int i = 0; i < n; i++) {
            int numberOfNeighbors = 0;
            for( int j = 0; j < n; j++) {
                if(adjacency[i][j])
                    numberOfNeighbors++;
            }
            neighbors[i] = new String[numberOfNeighbors];
            int counter = 0;
            for( int j = 0; j < n; j++) {
                if(adjacency[i][j])
                    neighbors[i][counter++] = words[j];
            }
        }
        return neighbors;
    }
    public void displayNeighbors(String[] words, String[][] neighbors) {
        for (int i = 0; i < words.length; i++) {
            if (neighbors[i].length != 0) {
                System.out.print("Neighbors of word " + words[i] + " are: ");
                for (int j = 0; j < neighbors[i].length; j++) {
                    System.out.print(neighbors[i][j] + " ");
                }
                System.out.println();
            } else
                System.out.println("Word: " + words[i] + " has no neighbors!");
        }
    }
    public static void main(String[] args) {
        // Let n, p be two integers and C1,...,Cm a set of letters (the alphabet), all given as a command line arguments. Validate the arguments!
        if (args.length < 3) {
            System.err.println("Not enough arguments!You must provide two numbers and a number of characters(at least one)");
            System.exit(-1);
        }
        // for validating the input I chose to use a regex
        StringBuilder arguments = new StringBuilder();
        for (String argument : args) {
            arguments.append(argument);
            arguments.append(" ");
        }
        String argString = arguments.toString();
        if (!argString.matches("[1-9][0-9]*\s[1-9][0-9]*\s([a-zA-Z]\s)+")) {
            System.err.println("Invalid input! Usage: natural number, natural number, one or more characters!");
            System.exit(-1);
        }
        Homework h = new Homework(); // create an object of the class
        int n = Integer.parseInt(args[0]); // number of strings(words) in array
        System.out.println("Number of words in array is: " + n);
        int p = Integer.parseInt(args[1]); // each word contains exactly p characters from the given alphabet
        System.out.println("Length of a word is: " + p);
        int m = args.length - 2; //the number of letters in the alphabet(subtract 2 for the first two integers)
        char[] alphabet = new char[m];
        for (int i = 0; i < m; i++) {
            alphabet[i] = args[i + 2].charAt(0);
        }
        /* Create an array of n strings (called words), each word containing exactly p characters from the given alphabet.
            Display on the screen the generated array.*/
        String[] words = h.generateWords(n, p, alphabet, h);
        h.displayWords(words);
        /* Two words are neighbors if they have a common letter.
            Create a boolean n x n matrix, representing the adjacency relation of the words.
            Create a data structure (using arrays) that stores the neighbors of each word. Display this data structure on the screen.*/
        boolean[][] adjacency = h.generateAdjacency(n, words);// if the words are neighbors, value will be true otherwise false
        String[][] neighbors = h.generateNeighbors(n, words, adjacency); // neighbors[i] stores the neighbors for word[i]
        h.displayNeighbors(words, neighbors);
        /* For larger n display the running time of the application in nanoseconds (DO NOT display the data structure!).
            Try n > 30_000. You might want to adjust the JVM Heap Space using the VM options -Xms4G -Xmx4G.*/
        long startTime = System.nanoTime();
        String[] largerWords = h.generateWords(30000, p, alphabet, h);
        boolean[][] largerAdjacency = h.generateAdjacency(30000, largerWords);
        String[][] largerNeighbors = h.generateNeighbors(30000, largerWords, largerAdjacency);
        long endTime = System.nanoTime();
        System.out.println("Running time in nanoseconds for larger number of words is: " + (endTime - startTime));

        // create an object of class Bonus and parse the necessary resources(the adjacency matrix, words array and the number of words)
        Bonus b = new Bonus(words, adjacency);
        if (!b.findCycle()) {
            System.out.println("There is no subset of words with this property!");
        } else {
            b.displaySubset();
        }
    }
}
