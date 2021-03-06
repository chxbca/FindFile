package com.company;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author chxbca
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Program Files\\Java";
        String fileName = "java.exe";
        File file = new File(path);
        FileTree fileTree = new FileTree(file);
        System.out.println("搜索路径  ：" + path);
        System.out.println("搜索文件名：" + fileName);
        List<File> foundFile = fileTree.search(fileName);
        if (foundFile.size() == 0) {
            System.out.println("File Not Found");
        } else {
            foundFile.forEach(System.out::println);
        }
    }
}