package com.putao.nlp.chatrobot.segment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class FileUtil {

    private FileUtil() {}

    private static class FileUtilHolder {
        private static final FileUtil singleton = new FileUtil();
    }

    public static final FileUtil getInstance() {
        return FileUtilHolder.singleton;
    }

    public static List<String> readFileByLine(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        List<String> lines = new LinkedList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                lines.add(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }


        }
        return lines;
    }




}

