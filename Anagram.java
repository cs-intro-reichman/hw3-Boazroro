public class Anagram {
    public static void main(String args[]) {
		//הדפסות
        System.out.println(isAnagram("silent", "listen"));  // true
        System.out.println(isAnagram("William Shakespeare", "I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie", "Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle", "I am Lord Voldemort")); // true

        System.out.println(preProcess("What? No way!!!"));

        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

        String str = "1234567";
        Boolean pass = true;
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    }

    public static boolean isAnagram(String str1, String str2) {
        str1 = preProcess(str1);
        str2 = preProcess(str2);

        if (str1.length() != str2.length())
            return false;
        for (int i = 0; i < str1.length(); i++) {
            int found = 0;
            for (int j = 0; j < str2.length(); j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    found++;
                    break;
                }
            }
            if (found == 0) {
                return false;
            }
        }
        return true;
    }

    public static String preProcess(String str) {
        str = str.replaceAll("[^a-zA-Z\\s]", "");
        str = str.toLowerCase();
        int i = 0;

        while (i < str.length() && str.charAt(i) != ' ') {
            i++;
        }

        String part1 = "";
        String part2 = "";

        if (i < str.length())
            part1 = str.substring(0, i + 1);
        else
            part1 = str;

        if (i + 1 < str.length())
            part2 = str.substring(i + 1);
        else
            part2 = "";

        part2 = part2.replaceAll("[^a-zA-Z]", "");

        return part1 + part2;
    }

    public static String randomAnagram(String str) {
        int count = 1;
        String remaining = str;
        String result = "";
        while (count < str.length()) {
            if (remaining.isEmpty())
                break;

            int index = (int) (Math.random() * (str.length() - count));
            result = result + remaining.charAt(index);
            remaining = remaining.replace(String.valueOf(result.charAt(result.length() - 1)), "");

            count++;
        }

        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == str.charAt(i) && j != i) {
                    return result + str.charAt(j);
                }
            }
        }
        return result + remaining;
    }
}
