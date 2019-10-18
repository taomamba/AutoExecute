package com.inspur.auto;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;

public class MD5Sec {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\安全资料\\工具\\路径&字典\\login\\弱口令.txt");
        File outFile = new File("D:\\安全资料\\工具\\路径&字典\\login\\密码1.txt");

        //创建fileInputStream对象读取文件
        FileInputStream fileInputStream = null;
        //创建fileOutStream对象写入文件
        FileOutputStream fileOutputStream =null;
        //创建inputStreamReader对象进行读取文件
        InputStreamReader inputStreamReader = null;
        //创建outputStreamWriter对象进行写入
        OutputStreamWriter outputStreamWriter = null;
        //创建reader缓冲区将文件流装进去
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(outFile);
            inputStreamReader = new InputStreamReader(fileInputStream);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            // 从缓冲区中逐行读取文件，调用rederLine方法
            String readerLine = null;
            while ((bufferedReader.readLine()) != null){
                readerLine = bufferedReader.readLine();
                String salt = "1#2$3%4(5)6@7!poeeww$3%4(5)djjkkldss";
                String password = DigestUtils.md5Hex(readerLine + "{" + salt + "}");
                bufferedWriter.write(password);
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bufferedWriter.close();
        }
    }




}
