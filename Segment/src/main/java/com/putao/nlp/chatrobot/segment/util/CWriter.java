package com.putao.nlp.chatrobot.segment.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * C-Version 1.0 FileWriter
 * Reader file one line from file (input file name).
 * @author xhqiu
 */
public class CWriter {
	private BufferedWriter bw;
	
	/**
	 * Create the Writer.
	 * @param fileName file path + fileName
	 */
	public CWriter(String fileName) {
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CWriter(String fileName, String code) {
        try {
        	bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), code));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public void writeLine(String content, boolean newLine) {
		try {
			bw.write(content);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(true == newLine)
		{
			try {
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

	public void close() {
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
