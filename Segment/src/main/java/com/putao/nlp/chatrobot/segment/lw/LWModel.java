package com.putao.nlp.chatrobot.segment.lw;
import com.putao.nlp.ahocorasick.AhoCorasickDoubleArrayTrie;
import com.putao.nlp.ahocorasick.DicWordTerm;
import com.putao.nlp.ahocorasick.NatureFreqElem;
import com.putao.nlp.chatrobot.segment.decoder.Segment;
import com.putao.nlp.chatrobot.segment.model.Model;
import com.putao.nlp.chatrobot.segment.util.Util;
import com.putao.nlp.conf.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import java.util.*;

/**
 * Created by admin on 16/8/24.
 */
public class LWModel extends Model{
    private static final Logger logger = LoggerFactory.getLogger(LWModel.class);
    private AhoCorasickDoubleArrayTrie<DicWordTerm> act = null;
    private TreeMap<String, DicWordTerm> treeMap = null;

    public static void main(String []args){
        String dirName = Config.get().get("dic.path");
        logger.info("dirName:" + dirName);
        String fwModelFilePath = LWModel.class.getResource("/" + dirName).getPath();
        if(null != fwModelFilePath) {
            LWModel model = new LWModel();
            try {
                model.loadModel(fwModelFilePath);
            } catch (ExceptionWithLoadModelError e) {
                e.printStackTrace();
            }

            long begTime = System.currentTimeMillis();
            String sentence = "退伍兵";
            List<Segment> segments = model.getCandidates(sentence, 0, 0);
            logger.info("segments:" + segments.toString());
            logger.info("used Time:" + (System.currentTimeMillis() - begTime));
        }
    }


    /*
  * @ ExceptionWithLoadModelError 导入模型文件错误
  * */
    public static class ExceptionWithLoadModelError extends Exception {
        public ExceptionWithLoadModelError(String s) {
            super(s);
        }

        public String toString() {
            return getMessage();
        }
    }


    /**
     *
     * @param dirPath
     * @throws ExceptionWithLoadModelError
     */
    public void loadModel(String dirPath) throws ExceptionWithLoadModelError{
        act = new AhoCorasickDoubleArrayTrie<DicWordTerm>();
        treeMap = new TreeMap<String, DicWordTerm>();
        try {
            File dir = new File(dirPath);
            if (dir.isDirectory()) {
                File[] fileList = dir.listFiles();
                for (int i = 0; i < fileList.length; i++) {
                    String dicName = fileList[i].getName().replace(".dic", "");
                    List<String> datas = Util.readFileByLine(fileList[i].getAbsolutePath());
                    buildDicMap(datas, treeMap, dicName);
                    logger.info("treeMap size:" + treeMap.size());
                }
            }

            act.build(treeMap);
        }catch(Exception e){
           throw new ExceptionWithLoadModelError("load ac model failed");
        }
    }

    /**
     *
     * @param datas
     * @param treeMap
     * @param tag
     */
    private void buildDicMap(List<String> datas, TreeMap<String, DicWordTerm> treeMap, String tag){
        if(null == datas || datas.isEmpty()){
            return;
        }
        for(String data : datas){
            data = data.trim();
            String splits[] = data.split("\t");
            if(splits.length == 3){
                try {
                    String word = splits[1].trim();
                    if(word.isEmpty()){
                        continue;
                    }

                    int frequence = Integer.valueOf(splits[2]);
                    NatureFreqElem termElem = new NatureFreqElem(tag, frequence);
                    if (treeMap.containsKey(word)) {
                        treeMap.get(word).addItem(termElem);
                    }
                    else{
                        List<NatureFreqElem> puTermElems = new ArrayList<NatureFreqElem>();
                        puTermElems.add(termElem);
                        treeMap.put(word, new DicWordTerm(word, puTermElems));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Segment> getCandidates(String Sentence, double scoreMargin, int countMargin) {
        if(null == Sentence || Sentence.isEmpty()){
            return null;
        }
        else{
            return segSentence(Sentence);
        }
    }

    /**
     * 给一个句子给出所有匹配到的串
     *
     * @param sentence 待匹配句子
     * @return 单词列表
     */
    private List<Segment> segSentence(String sentence){
        if (act == null)
        {
            logger.error("还未传入AhoCorasickDoubleArrayTrie");
            return Collections.emptyList();
        }

        char[] charArray = sentence.toCharArray();
        final Map<Integer, List<DicWordTerm> > startPosTermMap = new HashMap<Integer, List<DicWordTerm>>();
        act.parseText(sentence, new AhoCorasickDoubleArrayTrie.IHit<DicWordTerm>() {
            @Override
            public void hit(int begin, int end, DicWordTerm value) {
                if(!startPosTermMap.containsKey(begin)){
                    startPosTermMap.put(begin, new ArrayList<DicWordTerm>());
                }
                startPosTermMap.get(begin).add(value);

            }
        });

        List<Segment> segments = new ArrayList<Segment>();
        for (int i = 0; i < charArray.length; i++ ) {
            List<DicWordTerm> terms = startPosTermMap.get(i);
            if(null != terms) {
                for (DicWordTerm term : terms) {
                    String word = term.getWord();
                    List<NatureFreqElem> termElems = term.getInfoList();
                    int startPos = i;
                    int endPos = i + word.length();
                    if (null != termElems) {
                        for (NatureFreqElem termElem : termElems) {
                            String tag = termElem.getTag();
                            int frequence = termElem.getFrequence();
                            Segment segment = new Segment(word, startPos, endPos, 1.0 * frequence, tag);
                            segments.add(segment);
                        }
                    }
                }
            }
        }
        return segments;
    }

    @Override
    public void setModelWeight(String path) {

    }
}
