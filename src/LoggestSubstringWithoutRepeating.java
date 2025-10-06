import java.util.HashMap;
import java.util.Scanner;

public class LoggestSubstringWithoutRepeating {
    public  static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String s = sc.nextLine();
        int maxlength =0, start =0;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1);
            }
            map.put(c, end);
            maxlength = Math.max(maxlength, end - start + 1);
        }
        System.out.println(map);
        System.out.println(maxlength);
    }
}
