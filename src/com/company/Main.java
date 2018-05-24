package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Program Files\\Java";
        String fileName = "java.exe";
        File file = new File(path);
        long start = System.currentTimeMillis();
        FileTree fileTree = new FileTree(file);
        long end = System.currentTimeMillis();
        System.out.printf("建树时间为:%d毫秒%n", end - start);

//        fileTree.show();

        System.out.println("搜索路径  ：" + path);
        System.out.println("搜索文件名：" + fileName);

        start = System.currentTimeMillis();
        Collection<?> foundFile = fileTree.search(fileName);
        end = System.currentTimeMillis();

        if (foundFile == null) {
            System.out.println("File Not Found");
        } else {
            foundFile.forEach(System.out::println);
        }
        System.out.printf("搜索时间为:%d毫秒%n", end - start);
    }
}