package com.inspur.auto;

import java.io.File;
import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) {
        long le = Long.parseLong("43101");
        String format = Test.format(le, "yyyy-mm-dd");
        System.out.println("ddddd"+format);
    }
    public static final String format(Long date ,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);

    }
}
