import java.util.Scanner;

public class StringReversal {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        // Hello World
        int first = 0;
        int last = s.length() - 1;
        char[] arr = s.toCharArray();
        while (first < last) {
            char temp = arr[first];
            arr[first] = arr[last];
            arr[last] = temp;
            first++;
            last--;
        }
        System.out.println(new String(arr));
    }
}
