package leetcode.array;

/**
 * 二分查找法
 * https://leetcode.com/problems/search-insert-position/
 *
 * @Time 2019-12-19 16:22:00
 */
public class SearchInsertPosition_35 {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int index = new SearchInsertPosition_35().searchInsert(nums, 2);
        System.out.println(index);
    }
}
