package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Program Files\\Java";
        String fileName = "java.exe";
        File file = new File(path);
        FileTree fileTree = new FileTree(file);

//        fileTree.show();

        System.out.println("搜索路径  ：" + path);
        System.out.println("搜索文件名：" + fileName);

        if (!fileTree.search(fileName)) {
            System.out.println("File Not Found");
        }
    }
}