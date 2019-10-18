package com.inspur.auto;

import sun.misc.BASE64Encoder;

import java.io.*;

public class Base64AndFile {
    public static String fileToBase64(File file) {
        String encode = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
           // int length = in.read(bytes);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            encode = base64Encoder.encode(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }
}
