package com.inspur.auto;

import org.python.util.PythonInterpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOTest {
    public static void main(String[] args) {
      IOTest ioTest = new IOTest();
      ioTest.testList();
    }
    public void testList(){
        List<String> nameList = Arrays.asList("tom","arry");
        nameList.add("felif");
        System.out.println(nameList.toString());
    }
}
