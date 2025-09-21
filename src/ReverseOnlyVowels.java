import java.util.Scanner;

public class ReverseOnlyVowels  {
    //IceCreAm
    //AceCreIm
    private static boolean isNotVowel(char c) {
        return !switch (c) {
            case 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' -> true;
            default -> false;
        };
    }
    private static String reverseVowels(String s) {
        char[] a = s.toCharArray();
        int i = 0, j = a.length - 1;

        while (i < j) {
            while (i < j && isNotVowel(a[i])) i++;
            while (i < j && isNotVowel(a[j])) j--;
            if (i < j) {
                char tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++; j--;
            }
        }
        return new String(a);
    }
    public void reverseString(char[] s) {

        int i = 0, j = s.length - 1;
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }


    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String");
        String str = sc.nextLine();
        System.out.println(reverseVowels(str));
    }
}
