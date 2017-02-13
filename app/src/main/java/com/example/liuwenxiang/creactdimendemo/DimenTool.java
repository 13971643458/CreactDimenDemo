package com.example.liuwenxiang.creactdimendemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cdy on 2016/2/3.
 * 快速生成适配工具类
 */
public class DimenTool {

    public static void gen() {
        //以此文件夹下的dimens.xml文件内容为初始值参照
        File file = new File("./app/src/main/res/values/dimens.xml");

        BufferedReader reader = null;
        StringBuilder w240 = new StringBuilder();

        StringBuilder w480 = new StringBuilder();
        StringBuilder w600 = new StringBuilder();
        StringBuilder w720 = new StringBuilder();
        StringBuilder w800 = new StringBuilder();
        StringBuilder w820 = new StringBuilder();

        try {
            System.out.println("生成不同分辨率：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");

                    String start = tempString.substring(0, tempString.indexOf(">") + 1);

                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    //截取<dimen></dimen>标签内的内容，从>右括号开始，到左括号减2，取得配置的数字
                    Double num = Double.parseDouble
                            (tempString.substring(tempString.indexOf(">") + 1,
                                    tempString.indexOf("</dimen>") - 2));

                    //根据不同的尺寸，计算新的值，拼接新的字符串，并且结尾处换行。
                    w240.append(start).append(num * 0.75).append(end).append("\r\n");

                    w480.append(start).append(num * 1.5).append(end).append("\r\n");
                    w600.append(start).append(num * 1.87).append(end).append("\r\n");
                    w720.append(start).append(num * 2.25).append(end).append("\r\n");
                    w800.append(start).append(num * 2.5).append(end).append("\r\n");
                    w820.append(start).append(num * 2.56).append(end).append("\r\n");
                } else {
                    w240.append(tempString).append("");

                    w480.append(tempString).append("");
                    w600.append(tempString).append("");
                    w720.append(tempString).append("");
                    w800.append(tempString).append("");
                    w820.append(tempString).append("");
                }
                line++;
            }
            reader.close();
            System.out.println("<!--  w240 -->");
            System.out.println(w240);

            System.out.println("<!--  w480 -->");
            System.out.println(w480);
            System.out.println("<!--  w600 -->");
            System.out.println(w600);
            System.out.println("<!--  w720 -->");
            System.out.println(w720);
            System.out.println("<!--  w800 -->");
            System.out.println(w800);
            String sw240file = "./app/src/main/res/values-w240dp/dimens.xml";

            String sw480file = "./app/src/main/res/values-w480dp/dimens.xml";
            String sw600file = "./app/src/main/res/values-w600dp/dimens.xml";
            String sw720file = "./app/src/main/res/values-w720dp/dimens.xml";
            String sw800file = "./app/src/main/res/values-w800dp/dimens.xml";
            String w820file = "./app/src/main/res/values-w820dp/dimens.xml";
            //将新的内容，写入到指定的文件中去
            writeFile(sw240file, w240.toString());

            writeFile(sw480file, w480.toString());
            writeFile(sw600file, w600.toString());
            writeFile(sw720file, w720.toString());
            writeFile(sw800file, w800.toString());
            writeFile(w820file, w820.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    /**
     * 写入方法
     */

    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        gen();
    }
}