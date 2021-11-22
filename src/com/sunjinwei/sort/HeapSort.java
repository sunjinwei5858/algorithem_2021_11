package com.sunjinwei.sort;

import java.util.Arrays;

/**
 * @program: com.sunjinwei.sort
 * @author: sun jinwei
 * @create: 2021-11-19 15:52
 * @description: 手写堆排序
 * 参考代码：
 * https://github.com/algorithmzuo/algorithmbasic2020/blob/master/src/class06/Code03_HeapSort.java
 **/
public class HeapSort {

    /**
     * 堆排序核心：
     * 1.首先将待排序的数组构造成一个大根堆，此时，整个数组的最大值就是堆结构的顶端
     * 2.将顶端的数与末尾的数交换，此时，末尾的数为最大值，剩余待排序数组个数为n-1
     * 3.将剩余的n-1个数再构造成大根堆，再将顶端数与n-1位置的数交换，如此反复执行，便能得到有序数组
     *
     * @param arr
     */
    public void sort(int[] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }
        // 1.构造大根堆
        for (int i = 0; i < arr.length; i++) {
            heapUp(arr, i);
        }

        int len = arr.length;
        for (int i = len - 1; i >= 0; i--) {
            // 2.将顶端的数与末尾的数交换，此时，末尾的数为最大值，剩余待排序数组个数为n-1
            swap(arr, 0, i);
            // 3.将剩余的n-1个数再构造成大根堆，再将顶端数与n-1位置的数交换，如此反复执行，便能得到有序数组
            len--;
            heapDown2(arr, 0, len);
        }

    }


    /**
     * 构造大顶堆 上浮
     * 思想：利用二叉堆的性质，父节点大于左节点和右节点，右节点可以不存在
     *
     * @param arr
     * @param child
     */
    private void heapUp(int[] arr, int child) {

        // 1 根据二叉堆的性质 找出child的父节点索引
        int current = child;
        int parent = (current - 1) / 2;
        // while循环的条件是：父节点的值小于孩子节点的值
        while (arr[parent] < arr[current]) {
            // 交换
            swap(arr, parent, current);
            // 进行下一个处理
            current = parent;
            parent = (current - 1) / 2;
        }

    }

    /**
     * 下沉
     *
     * @param arr
     * @param parent
     * @param length
     */
    private void heapDown2(int[] arr, int parent, int length) {

        int left = 2 * parent + 1;
        int right = 2 * parent + 2;
        // while条件：left < length
        while (left < length) {
            // 1判断右孩子是否存在 然后选取较大的值
            // 坑：这里不能写成 (right < length && arr[right] < arr[left])
            int large = (right < length && arr[right] > arr[left]) ? right : left;
            // 2比较父节点的值和较大子节点的大小
            // 如果父节点比子节点更大 那么退出循环即可
            if (arr[parent] > arr[large]) {
                break;
            }

            // 3交换
            swap(arr, parent, large);
            // 更新parent left right
            parent = large;
            left = 2 * parent + 1;
            right = 2 * parent + 2;
        }

    }

    private void swap(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] arr = new int[]{2, 1, 6, 3, 8, 4, 3};
        heapSort.sort(arr);
        System.out.println(Arrays.toString(arr));


    }
}