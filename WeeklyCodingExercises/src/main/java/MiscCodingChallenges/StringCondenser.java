package MiscCodingChallenges;

public class StringCondenser {

    public String condenseString(String s){
        int[] charCounts = new int[26];
        StringBuilder condensedString = new StringBuilder();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            charCounts[c - 'a']++;
        }

        for (int i = 0; i < charCounts.length; i++){
            if (charCounts[i] > 0) {
                char c = (char) ('a' + i);
                condensedString.append(c).append(charCounts[i]);
            }
        }

        return condensedString.toString();
    }
}
