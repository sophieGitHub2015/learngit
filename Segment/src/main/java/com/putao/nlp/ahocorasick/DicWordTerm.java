package com.putao.nlp.ahocorasick;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16/8/24.
 */
public class DicWordTerm {
    public DicWordTerm(String word, List<NatureFreqElem> infoList) {
        this.infoList = infoList;
        this.word = word;
    }

    public DicWordTerm() {

    }

    private List<NatureFreqElem> infoList;
    private String word;

    public List<NatureFreqElem> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<NatureFreqElem> infoList) {
        this.infoList = infoList;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void addItem(NatureFreqElem elem){
        if(null == elem){
            return;
        }

        if(null == infoList || infoList.isEmpty()){
            infoList = new ArrayList<NatureFreqElem>();
        }
        infoList.add(elem);
    }

    public String getWord() {
        return word;
    }

    public String toString(){
        String result = "resul:" + "{" + "word:" + word + ",Info:" + infoList.toString() + "}";
        return result;
    }

}
