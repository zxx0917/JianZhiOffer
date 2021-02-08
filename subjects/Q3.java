import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Q3 {
    //方法一：使用Arrays工具类中的排序方法，将数组排序后从头开始遍历，分别比较前后元素，遇到第一个相同的元素时直接返回
    //因为Arrays.sort()使用了快排，所以该方法的时间复杂度为O(nlogn)，空间复杂度为O(n)
    public int findRepeatNumber1(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1;i < nums.length;i++){
            if(nums[i-1] == nums[i])
                return nums[i];
        }
        return -1;
    }

    /*
     * 因为题目是要求找出任意一个重复的数字，所以可以使用Set的特性，遍历整个数组，如果当前元素在Set中已经存在，则该数字为结果之一，
     * 如果不存在，则将数字加入Set，直至找到结果
     * 因为可能存在没有重复元素的数组，所以该方法的时间复杂度为：O(n)，空间复杂度为：O(n)
     */
    public int findRepeatNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(set.contains(num)) return num;
            set.add(num);
        }
        return -1;
    }

    /*
     * 使用一个萝卜一个坑的思想，因为数组的index为0~n-1，而数组中的元素在0~n-1之间，如果没有重复元素时，数字0对应0号位，数字1对应
     * 1号位，以此内推。所以我们可以采取循环交换的方式，直至将数字放在对应的位置上，当要交换的数字等于当前位置上的数字时，表示该数字
     * 为重复数字。例如
     * 数字   1 0 2 2
     * ------------------ 指针到达索引0，数字1和arr[1]交换，此时0位于索引0的位置，跳出循环，
     * 索引   0 1 2 3
     * 数字   0 1 2 2
     * ------------------ 指针到达索引1，此时1位于索引1的位置，跳出循环
     * 索引   0 1 2 3
     * 数字   0 1 2 2
     * ------------------ 指针到达索引2，此时2位于索引2的位置，跳出循环
     * 索引   0 1 2 3
     * 数字   0 1 2 2
     * ------------------ 指针到达索引3，此时2位于索引3的位置，要和arr[2]进行交换，发现index=2的位置已经被占，所以2就是重复元素
     * 索引   0 1 2 3
     * 该方法的时间复杂度为：O(N)，空间复杂度为：O(1)
     */
    public int findRepeatNumber3(int[] nums) {
        int tmp;
        for(int i = 0;i < nums.length;i++){
            while(nums[i] != i){
                if(nums[i] == nums[nums[i]]) return nums[i];
                tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }
}
