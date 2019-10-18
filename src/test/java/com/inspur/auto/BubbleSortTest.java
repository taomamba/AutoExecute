package com.inspur.auto;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSortTest {
    public void bubbleSort(int[] a){
        int n = a.length;
        int i,j;
        for (i=0;i<n;i++){ //表示N次排序过程
            for (j=1;j<n-i;j++){
                if (a[j-1]>a[j]){ //前面的数字大于后边的数字就交换
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
    @Test
    public void buttle(){
        BubbleSortTest bubbleSortTest = new BubbleSortTest();
        int[] a = {3,2,123,435,545,45,5,64656};
        bubbleSortTest.bubbleSort(a);
        System.out.println(Arrays.toString(a));

    }
    @Test
    public void test(){
        int[] a = {3,1,2,7,4,5,6,8};
        System.out.println(Arrays.toString(a));
    }
}
