package com.example.task02;

import org.graalvm.compiler.api.replacements.Snippet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));
    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        List<Path> paths = new ArrayList<>();
        Stack<Path> stack = new Stack<>();
        stack.add(rootDir);

        while(!stack.empty()){
            Path currentPath = stack.pop();
            if (currentPath.toFile().isFile()){
                paths.add(currentPath);
            } else {
                stack.addAll(getFiles(currentPath));
            }
        }


        return paths;
    }

    public static ArrayList<Path> getFiles(Path pathToFile){
        File file = pathToFile.toFile();
        ArrayList<Path> pathArrayList = new ArrayList<>();

        Arrays.stream(file.listFiles()).map(File::toPath).forEach(pathArrayList::add);
        return pathArrayList;
    }
}
