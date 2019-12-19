package leetcode.array;

/**
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * @Time 2019-12-18 11:19:00
 */
public class RemoveDuplicatesFromSortArray_26 {
    public int removeDuplicates(int[] nums) {
        int len = 0;

        for (int num : nums) {
            if (len == 0 || num > nums[len - 1]) {
                nums[len] = num;
                len++;
            }
        }

        return len;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortArray_26 obj = new RemoveDuplicatesFromSortArray_26();
        int[] arrays = {0, 0, 1, 1, 1, 2, 2, 3, 4};

        int len = obj.removeDuplicates(arrays);
        System.out.println("Arrays: ");
        for (int i = 0; i < arrays.length; i++) {
            if (i > 0)
                System.out.print(",");
            System.out.print(arrays[i]);
        }
        System.out.println("");
        System.out.println("Remove duplicates arrays length: " + len);
    }

}
