package template.binarySearch;

/**
 * 给定数组和target，寻找第一个大于等于target的数（或者下标）
 */
public class GreaterOrEqual {
    /**
     * 返回值 数组下标
     */

    int lower_bound(int[] nums, int target){
        int len = nums.length;
        int left = 0;
        int right = len - 1;  //闭区间
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }

    int lower_bound2(int[] nums, int target){
        int len = nums.length;
        int left = 0;
        int right = len;  //左闭右开区间
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }

    int lower_bound3(int[] nums, int target){
        int len = nums.length;
        int left = -1;
        int right = len;  //左开右开区间
        while(left  + 1 < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] < target){
                left = mid;
            }else {
                right = mid;
            }
        }
        return right;  //注意是返回right
    }


}
