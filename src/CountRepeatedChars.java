import java.util.Scanner;

public class CountRepeatedChars {
    //aaabbcdddeefgh
    //a3b2cd3e2fgh
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String input = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            int count = 1;
            int j = i + 1;
            char ch = input.charAt(i);
            while (j < length && input.charAt(j) == ch) {
                count++;
                j++;
            }
            sb.append(input.charAt(i));
            if(count > 1){
                sb.append(count);
            }
            i = j - 1;
        }
        System.out.println(sb.toString());
    }
}
