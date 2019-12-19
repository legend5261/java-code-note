package leetcode.array;

/**
 * https://leetcode.com/problems/remove-element/
 * 快慢指针
 *
 * @Time 2019-12-18 06:45:00
 */
public class RemoveElement_27 {
    public int removeElement(int[] nums, int val) {
        int cur = 0, pre = 0;
        while (cur < nums.length) {
            if (nums[cur] != val) {
                nums[pre] = nums[cur];
                pre++;
            }
            cur++;
        }

        return pre;
    }

    public static void main(String[] args) {
        RemoveElement_27 obj = new RemoveElement_27();
        int[] arrays = {0,1,2,2,3,0,4,2};
        int len = obj.removeElement(arrays, 2);

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
