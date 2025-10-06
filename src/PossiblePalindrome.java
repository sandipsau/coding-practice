import java.util.Arrays;
import java.util.Scanner;

public class PossiblePalindrome {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        // tactcoa
        int[] charCount = new int[128];
        for (char c : s.toCharArray()) {
            charCount[c]++;
        }
        int oddCount = 0;
        for (int count: charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
            if (oddCount > 1) {
                System.out.println("Not a Palindrome");
                return;
            }
        }
        System.out.println("Possible Palindrome");
    }
}
