package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String ffprobePath = "D:/Dev/JavaProjects/8-java-io2/ffmpeg-2025-11-17-git-e94439e49b-full_build/bin/ffprobe.exe";
        String mp3Path = file.getPath();

        Process process = new ProcessBuilder(ffprobePath, "-v", "error", "-of", "flat", "-show_format", mp3Path).start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String line;
            while((line = br.readLine()) != null){
                if (line.startsWith("format.tags.title")){
                    return line.split("=")[1].replace("\"", "");
                }
            }
        }
        return "exception";
    }
}
