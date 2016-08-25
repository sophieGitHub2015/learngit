package com.putao.nlp.chatrobot.segment.util;

import com.putao.nlp.chatrobot.segment.decoder.Segment;
import com.putao.nlp.conf.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/8/23.
 */
public class Util {

    private volatile static Util singleton;

    private Util() {}

    public static Util getInstance() {
        if (singleton == null) {
            synchronized (Util.class) {
                if (singleton == null) {
                    singleton = new Util();
                }
            }
        }
        return singleton;
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

    /**
     *
     * @param segments
     * @param scoreMargin
     * @param countMargin
     * @return
     */
    public static List<Segment> filterSegment(List<Segment> segments, double scoreMargin, int countMargin){
        if(null == segments || segments.isEmpty()
            || countMargin <= 0){
            return null;
        }

        //Filter by scoreMargin
        List<Segment> segmentByScoreMargins = new ArrayList<Segment>();
        for(Segment segment : segments){
            if(segment.getScore() >= scoreMargin){
                segmentByScoreMargins.add(segment);
            }
        }

        if(segmentByScoreMargins.size() <= countMargin){
            return segmentByScoreMargins;
        }

        //Filter by top N
        Comparator<Segment> countComparator =  new Comparator<Segment>(){
            public int compare(Segment o1, Segment o2) {
                double leftScore = o1.getScore();
                double rightScore = o1.getScore();
                if (rightScore > leftScore) {
                    return 1;
                } else if (rightScore < leftScore) {
                    return -1;
                } else {
                    return 0;
                }
            }

        };
        Queue<Segment> priorityQueue = new PriorityQueue<Segment>(countMargin, countComparator);
        for(Segment segment : segmentByScoreMargins){
            if(priorityQueue.size() < countMargin){
                priorityQueue.add(segment);
            }
            else{
                Segment topSegment = priorityQueue.peek();
                if(topSegment.getScore() < segment.getScore()){
                    priorityQueue.poll();
                    priorityQueue.add(segment);
                }
            }
        }

        List<Segment> resulSegments = new ArrayList<Segment>();

        while(!priorityQueue.isEmpty()){
            Segment segment = priorityQueue.peek();
            resulSegments.add(segment);
            priorityQueue.poll();
        }

        return resulSegments;


    }

    /**
     *
     * @param sentence
     * @return
     */
    public static List<String> splitSentence(String sentence){
       String splitSeperator = Config.get().get("split.separator", ",.,。;");
        return null;
    }


    public static void mergeDicWeight(String dirPath, String outputDir) {
        Map<String, Integer> wordCountMap = null;
            File dir = new File(dirPath);
            if (dir.isDirectory()) {
                File[] fileList = dir.listFiles();
                for (int i = 0; i < fileList.length; i++) {
                    String dicName = fileList[i].getName().replace(".dic", "");
                    List<String> datas = Util.readFileByLine(fileList[i].getAbsolutePath());
                    if (null != datas && !datas.isEmpty()) {
                        wordCountMap = new HashMap<String, Integer>();
                        for (String data : datas) {
                            data = data.trim();
                            String splits[] = data.split("\t");
                            if (splits.length == 3) {
                                String word = splits[1];
                                int freq = Integer.valueOf(splits[2]);
                                if (!wordCountMap.containsKey(word)) {
                                    wordCountMap.put(word, 0);
                                }
                                wordCountMap.put(word, wordCountMap.get(word) + freq);
                            }
                        }

                        if (!wordCountMap.isEmpty()) {
                            String outputFilePath = outputDir + fileList[i].getName();
                            CWriter writer = new CWriter(outputFilePath);
                            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                                writer.writeLine(dicName + "\t" + entry.getKey() + "\t" + entry.getValue(), true);
                            }
                            writer.close();
                        }
                    }
                }
            }
        }

    public static void main(String []args){
        String inputDir = "./LW/";
        String outputDir = "./LW_NEW/";
        mergeDicWeight(inputDir, outputDir);
    }



}

