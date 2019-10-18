package com.inspur.auto;

import org.junit.Test;

public class BiSearchTest {
    public int bitSerche(int[] array,int a){
        int lo = 0;
        int hi = array.length - 1;
        int mid = 0;
        while (lo <= hi){
            mid = (lo + hi)/2;
            if (array[mid] == a){
                return mid + 1;
            }else if (array[mid] < a){ //向右查找
                lo = mid + 1;
            }else {
                hi = mid - 1;
            }
        }
        return -1;

    }
    @Test
    public void test(){
        BiSearchTest biSearchTest = new BiSearchTest();
        int[] array = {1,2,3,4,5,6,7,8};
        int i = biSearchTest.bitSerche(array, 3);
        System.out.println(i);
    }
}
