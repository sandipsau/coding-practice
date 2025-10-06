import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FirstUniqueCharacterInAString {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String input = sc.nextLine();
        //leetcode
        int[] charCount = new int[128];
        HashMap<Character, Integer> map = new LinkedHashMap<>();
        char[] arr = input.toCharArray();
        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        System.out.println("The number of unique characters in the string is: " + map.entrySet().toString());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("First unique character is: " + entry.getKey());
                return;
            }
        }
    }
}
