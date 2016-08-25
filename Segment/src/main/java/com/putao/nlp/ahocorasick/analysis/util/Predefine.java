package com.putao.nlp.ahocorasick.analysis.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * 一些预定义的静态全局变量
 * 
 * @author
 */
public class Predefine
{
    public final static double MIN_PROBABILITY = 1e-10;
    public final static int CT_SENTENCE_BEGIN = 1;        //Sentence begin
    public final static int CT_SENTENCE_END = 4;          //Sentence ending
    public final static int CT_SINGLE = 5;                //SINGLE byte
    public final static int CT_DELIMITER = CT_SINGLE + 1; //delimiter
    public final static int CT_CHINESE = CT_SINGLE + 2;   //Chinese Char
    public final static int CT_LETTER = CT_SINGLE + 3;    //HanYu Pinyin
    public final static int CT_NUM = CT_SINGLE + 4;       //HanYu Pinyin
    public final static int CT_INDEX = CT_SINGLE + 5;     //HanYu Pinyin
    public final static int CT_OTHER = CT_SINGLE + 12;    //Other
    /**
     * 浮点数正则
     */
    public static final Pattern PATTERN_FLOAT_NUMBER = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");

    //Translation type
    public static int TT_ENGLISH = 0;
    public static int TT_RUSSIAN = 1;
    public static int TT_JAPANESE = 2;

    //Seperator type
    public static String SEPERATOR_C_SENTENCE = "。！？：；…";
    public static String SEPERATOR_C_SUB_SENTENCE = "、，（）“”‘’";
    public static String SEPERATOR_E_SENTENCE = "!?:;";
    public static String SEPERATOR_E_SUB_SENTENCE = ",()*'";
    //注释：原来程序为",()\042'"，"\042"为10进制42好ASC字符，为*
    public static String SEPERATOR_LINK = "\n\r 　";

    //Sentence begin and ending String
    public static String SENTENCE_BEGIN = "始##始";
    public static String SENTENCE_END = "末##末";

    //Seperator between two words
    public static String WORD_SEGMENTER = "@";

    public static int CC_NUM = 6768;

    //The number of Chinese Char,including 5 empty position between 3756-3761
    public static int WORD_MAXLENGTH = 100;
    public static int WT_DELIMITER = 0;
    public static int WT_CHINESE = 1;
    public static int WT_OTHER = 2;

    public static int MAX_WORDS = 650;
    public static int MAX_SEGMENT_NUM = 10;

    public static final int MAX_FREQUENCY = 25146057; // 现在总词频25146057
    /**
     * Smoothing 平滑因子
     */
    public static final double dTemp = (double) 1 / MAX_FREQUENCY + 0.00001;
    /**
     * 平滑参数
     */
    public static final double dSmoothingPara = 0.1;

    public static int MAX_SENTENCE_LEN = 100;

    public static int MIN_PROBLEM = 1;
    public static double INFINITE_VALUE = 10000.00;

    public static int MAX_WORDS_PER_SENTENCE = 120;
    public static int MAX_UNKNOWN_PER_SENTENCE = 200;
    public static int MAX_POS_PER_WORD = 20;
    public static int LITTLE_FREQUENCY = 6;

    /**
     * 地址 ns
     */
    public final static String TAG_PLACE = "未##地";
    /**
     * 句子的开始 begin
     */
    public final static String TAG_BIGIN = "始##始";
    /**
     * 其它
     */
    public final static String TAG_OTHER = "未##它";
    /**
     * 团体名词 nt
     */
    public final static String TAG_GROUP = "未##团";
    /**
     * 数词 m
     */
    public final static String TAG_NUMBER = "未##数";
    /**
     * 数量词 mq （现在觉得应该和数词同等处理，比如一个人和一人都是合理的）
     */
    public final static String TAG_QUANTIFIER = "未##量";
    /**
     * 专有名词 nx
     */
    public final static String TAG_PROPER = "未##专";
    /**
     * 时间 t
     */
    public final static String TAG_TIME = "未##时";
    /**
     * 字符串 x
     */
    public final static String TAG_CLUSTER = "未##串";
    /**
     * 结束 end
     */
    public final static String TAG_END = "末##末";
    /**
     * 人名 nr
     */
    public final static String TAG_PEOPLE = "未##人";

    /**
     * 日志组件
     */
    public static Logger logger = Logger.getLogger("HanLP");
    static
    {
        logger.setLevel(Level.SEVERE);
    }

}
