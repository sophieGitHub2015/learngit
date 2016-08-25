package com.putao.nlp.ahocorasick;

/**
 * Created by admin on 16/8/24.
 */
public class NatureFreqElem {
    public NatureFreqElem(String tag, int frequence) {
        this.tag = tag;
        this.frequence = frequence;
    }

    private String tag;
    private int frequence;

    public String toString(){
        String resul = "info:" + "{" + tag + "," + frequence + "}";
        return resul;
    }

    public String getTag() {
        return tag;
    }

    public int getFrequence() {
        return frequence;
    }
}
