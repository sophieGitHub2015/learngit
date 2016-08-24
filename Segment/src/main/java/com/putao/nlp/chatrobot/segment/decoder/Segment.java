package com.putao.nlp.chatrobot.segment.decoder;

/**
 * Created by taibai on 8/19/16.
 */
public class Segment implements Comparable<Segment> {


    String text;
    int start;
    int end;
    double score;
    String tag;

    public Segment(String text, int start, int end, double score, String tag) {
        this.text = text;
        this.start = start;
        this.end = end;
        this.score = score;
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public double getScore() {
        return score;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Segment segment = (Segment) o;

        if (start != segment.start) return false;
        if (end != segment.end) return false;
        return tag.equals(segment.tag);

    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + end;
        result = 31 * result + tag.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "text='" + text + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", score=" + score +
                ", tag='" + tag + '\'' +
                '}';
    }

    @Override
    public int compareTo(Segment o) {
        return Double.valueOf(this.getScore()).compareTo(Double.valueOf(o.getScore()));
    }
}
