import java.util.Arrays;
import java.util.Scanner;

public class PalindromePermutation {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        // tactcoa
        char[] arr = s.toCharArray();
        int length = s.length();
        int left = 0;
        int right = length - 1;
        while (left < right) {
            for (int counter = left + 1; counter < right; counter ++) {
                if (arr[counter] == arr[left]) {
                    char temp = arr[counter];
                    arr[counter] = arr[right];
                    arr[right] = temp;
                    break;
                }
            }
            right--;
            left++;

        }
        System.out.println(new String(arr));

    }
}
