package com.sunjinwei.array;

import java.util.Arrays;

/**
 * @program: com.sunjinwei.array
 * @author: sun jinwei
 * @create: 2021-11-26 09:34
 * @description: 将数组的0移到末尾 力扣283 难度 简单
 **/
public class MoveZeroes {

    /**
     * 思路：维护一个索引 将不为0的数字往前移动
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        // 1 维护一个索引
        int index = 0;

        // 2 将不是0的数据 往前挪
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }

        // 3 如果index小于数组长度 那么这些剩余的空位都是0
        while (index < nums.length) {
            nums[index] = 0;
            index++;
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 3, 12};

        MoveZeroes moveZeroes = new MoveZeroes();

        moveZeroes.moveZeroes(arr);

        System.out.println(Arrays.toString(arr));
    }
}