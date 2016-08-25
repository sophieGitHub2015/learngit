package com.putao.nlp.chatrobot.segment.util;

import com.putao.nlp.chatrobot.segment.decoder.Segment;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by admin on 16/8/25.
 */
public class UtilTest {
    @Test
    public void filterSegment() throws Exception {
        Segment s1 = new Segment("abc", 1, 4, 1.0, "LW");
        Segment s2 = new Segment("abc1", 1, 4, 1.5, "LW");
        Segment s3 = new Segment("abc2", 1, 4, 1.2, "LW");
        Segment s4 = new Segment("abc3", 1, 4, 1.3, "LW");
        Segment s5 = new Segment("abc4", 1, 4, 1.4, "LW");
        List<Segment> segments = new ArrayList<Segment>();
        segments.add(s1);
        segments.add(s2);
        segments.add(s3);
        segments.add(s4);
        segments.add(s5);
        List<Segment> resulSegments = Util.filterSegment(segments, 0.5, 4);
        System.out.println("resul:" + resulSegments);
        assertTrue(resulSegments.size() == 4);

    }

}