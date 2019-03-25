package leetcode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-number/
 *
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 */
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

    public static int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i == 0 || n > nums[i-1]) {
                nums[i++] = n;
            }

        for (int c:nums) {
            System.out.println("#" + c);
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date(2019, 3, 7);
        System.out.println(start.getTime());

        int[] a = {1,1,2,3};
        System.out.println(removeDuplicates(a));
    }
}
