package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromeNumber {

    /*
     * 121 true
     * -121 false
     */
    public static boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0)
            return false;

        if (x < 10)
            return true;

        List<Integer> list = new ArrayList<Integer>();
        while (x > 0) {
            int t = x % 10;
            list.add(t);
            x = x / 10;
        }

        int size = list.size() - 1;
        for (int i = 0; i <= size / 2; i++) {
            if (list.get(i) != list.get(size - i))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }
}
