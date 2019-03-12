package leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {
    public static Map<Character, Integer> romanMap = new HashMap<>();

    public void initRomanMap() {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }

    public int romanToInt(String s) {
        initRomanMap();

        int value = 0;
        for (int i = 0; i < s.length(); i++) {
            int pre = romanMap.get(s.charAt(i));

            if (i == s.length() - 1)
                return value + pre;

            int nxt = romanMap.get(s.charAt(i + 1));

            value += (pre < nxt ? -pre : pre);
        }

        return value;
    }

    public static void main(String[] args) {
        System.out.println(new RomanNumerals().romanToInt("IV"));
    }
}
