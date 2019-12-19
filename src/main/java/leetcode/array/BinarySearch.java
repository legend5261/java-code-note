package leetcode.array;

/**
 * 二分查找，非递归
 *
 * @Time 2019-12-19 11:50:00
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int search = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            search++;

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                System.out.println(String.format("查找次数:%d", search));
                return mid;
            }

        }

        System.out.println(String.format("查找%d次, 没有找到目标值: %d", search, target));
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 23, 43, 134};
        int index = new BinarySearch().search(nums, 23);
        System.out.println(index);
    }
}
