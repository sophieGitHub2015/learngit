package com.putao.nlp.chatrobot.segment.lw;

import com.putao.nlp.chatrobot.segment.decoder.Segment;
import com.putao.nlp.conf.Config;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xhqiu on 16/8/25.
 */
public class LWModelTest {
    private static final Logger logger = LoggerFactory.getLogger(LWModelTest.class);
    @Test
    public void getCandidates() throws Exception {
        String dirName = Config.get().get("dic.path");
        logger.info("dirName:" + dirName);
        String fwModelFilePath = LWModel.class.getResource("/" + dirName).getPath();
        if(null != fwModelFilePath){
            LWModel model = new LWModel();
            try {
                model.loadModel(fwModelFilePath);
            } catch (LWModel.ExceptionWithLoadModelError exceptionWithLoadModelError) {
                exceptionWithLoadModelError.printStackTrace();
            }

            long begTime = System.currentTimeMillis();
            String sentence = "退伍兵";
            List<Segment> segments = model.getCandidates(sentence, 0, 0);
            logger.info("segments:" + segments.toString());
            logger.info("used Time:" + (System.currentTimeMillis() - begTime));
            assertTrue(segments != null && !segments.isEmpty());
        }
    }

}