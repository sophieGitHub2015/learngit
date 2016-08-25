package com.putao.nlp.ahocorasick.analysis.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * J-Version 1.0 FileReader
 * Reader file one line from file (input file name).
 * @author
 */
public class JReader {
	private BufferedReader br;
	
	/**
	 * Create the Reader.
	 * @param fileName file path + fileName
	 */
	public JReader(String fileName) {
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JReader(String fileName, String code) {
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), code));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public String readline() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read file content 
	 * @param fileName abs path
	 * @return
	 */
	public static String readFile(String fileName) {
		JReader jr = new JReader(fileName);
		String temp;
		StringBuilder content = new StringBuilder("");
		while ((temp = jr.readline()) != null) {
			content.append(temp);
		}
		jr.close();
		return content.toString();
	}
	
	/**
	 * Read file into array, split by \n
	 * @param fileName
	 * @return
	 */
	public static String readFileToArray(String fileName) {
		JReader jr = new JReader(fileName);
		String temp;
		StringBuilder content = new StringBuilder("");
		while ((temp = jr.readline()) != null) {
			content.append(temp);
		}
		jr.close();
		return content.toString();
	}
	
	public Map<String, Boolean> readFileToMap() {
		String temp;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		try {
			while ((temp = br.readLine()) != null) {
				map.put(temp.split("####")[0], true);
				System.out.println(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static void main(String[] args) {
	}
	public static String getClientId(String line) {
        Pattern p = Pattern.compile("clientid=([^ &]*)");
        Matcher m = p.matcher(line.toLowerCase());
        while (m.find()) {
            return m.group(1);
        }
        return null;
    }
	
	public static String getUserId(String line) {
        Pattern p = Pattern.compile("userid=([^ &]*)");
        Matcher m = p.matcher(line.toLowerCase());
        while (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
