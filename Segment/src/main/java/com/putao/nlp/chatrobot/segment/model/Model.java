package com.putao.nlp.chatrobot.segment.model;

import com.putao.nlp.chatrobot.segment.decoder.Segment;

import java.util.List;
import java.util.Map;

/**
 * Created by taibai on 8/23/16.
 */
public abstract class Model {

    public Map<String,Double> modelWeight;

    /*
    * @ getCandidates
    * @ Sentence
    * @ scoreMargin
    * @ countMargin
    * @ return
    * */

    public abstract List<Segment> getCandidates(String Sentence, double scoreMargin, int countMargin);


    /*
    * @ setModelWeight
    * @ path
    *
    * */
    public abstract void setModelWeight(String path);



}
