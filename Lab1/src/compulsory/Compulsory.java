package compulsory;

/**
 * Main class of the application
 * @author Roxana
 */
public class Compulsory {
    static int sumOfDigits(int n)
    {
        int sum = 0;
        while(n != 0)
        {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        // Display on the screen the message "Hello World!"
        System.out.println("Hello World");

        // Define an array of strings languages
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        // Generate a random integer n
        int n = (int) (Math.random() * 1_000_000);
        //System.out.println("Random:" + n);

        // multiply n by 3
        n = Math.multiplyExact(n, 3);

        // add the binary number 10101 to the result
        int binaryNumber = Integer.parseInt("10101",2);
        n = n + binaryNumber;

        // add the hexadecimal number FF to the result;
        int hexadecimalNumber = Integer.parseInt("FF",16);
        n = n + hexadecimalNumber;

        // multiply the result by 6
        n = Math.multiplyExact(n, 6);

        // Compute the sum of the digits in the result obtained in the previous step. This is the new result. While the new result has more than one digit, continue to sum the digits of the result.
        int result = n;
        while(result > 9)
        {
            result = Compulsory.sumOfDigits(result);
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
