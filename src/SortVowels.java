import java.util.*;

public class SortVowels {
    private static boolean isVowel(char c) {
        char toLower = Character.toLowerCase(c);
        return toLower == 'a' || toLower == 'e' || toLower == 'i' || toLower == 'o' || toLower == 'u';
    }

    private static String sortVowel(String s) {
        List<Integer> vowelIndices = new ArrayList<>();
        List<Character> vowelChars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isVowel(c)) {
                vowelIndices.add(i);
                vowelChars.add(c);
            }
        }
            vowelChars.sort(Comparator.naturalOrder());
            char[] result = s.toCharArray();
            for (int j = 0; j < vowelIndices.size(); j++) {
                result[vowelIndices.get(j)] = vowelChars.get(j);
            }
            return new String(result);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String input = sc.nextLine();
        String output = sortVowel(input);
        System.out.println(output); // Output: "leotcedeisacommunityforcoders"
    }
}
