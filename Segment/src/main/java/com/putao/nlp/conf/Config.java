package com.putao.nlp.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 *
 */
public class Config {

	public static String getConfDir() {
        String path = Config.class.getResource("/").getPath();
        System.out.println("path:" + path);
        return path;
	}
			
	Map<String, String> kvPairs = new HashMap<String, String>();
	
	final File confFile;
	 
    Config(String confPath){
		confFile = new File(confPath);
	}
    
    public Properties copy() {
    	Properties p = new Properties();
    	for(Entry<String, String> entry:kvPairs.entrySet()) {
    		p.put(entry.getKey(), entry.getValue());
    	}
    	return p;
    }
	
    void load() throws FileNotFoundException, IOException {
    	Map<String, String> newKvPairs = new HashMap<String, String>();
    	InputStream is = null;
    	InputStreamReader isr = null;
    	try{
	    	is = new FileInputStream(confFile);
	    	isr = new InputStreamReader(is, "UTF-8");
	    	Properties properties = new Properties();
	        properties.load(isr);
	        
	    	for(Entry<Object, Object> entry:properties.entrySet()) {
	    		newKvPairs.put(((String)entry.getKey()).toLowerCase(), (String)entry.getValue());
	    	}
	    	kvPairs = newKvPairs;
    	} finally{
    		try {
				if (isr != null) {
					isr.close();
				}
			} finally {
				if (is != null) {
					is.close();
				}
			}
    	}
    }
    
    public String get(String key, String defaultValue){
    	if(key != null) {
    		key = key.trim().toLowerCase();
    	}
        String value = kvPairs.get(key);
        if (value == null){
            return defaultValue;
        }
        return value.trim();
    }

    
    public int getInt(String key, int defaultValue){
        String value = get(key);
        if (value == null){
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public float getFloat(String key, float defaultValue){
        String value = get(key);
        if (value == null){
            return defaultValue;
        }
         try {
             return Float.parseFloat(value); 
         } catch (Exception e) {
             return defaultValue;
         }
    }
    
    public boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null){
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    public String get(String key) {
    	if(key != null) {
    		key = key.trim().toLowerCase();
    	}
        return kvPairs.get(key);
    }
    
    public void releaseSource()throws IOException{
    	//sub class should do something
    }

    private volatile static Config CONFIG;
    
    public static final Config get(){
    	if(CONFIG == null){
    		synchronized (DefaultConfig.class) {
    			if(CONFIG == null){
    				CONFIG = new DefaultConfig();
    			}
			}
    	}
        return CONFIG;
    }


    private static class DefaultConfig extends Config{
        
        long lastModified = -1;
        
        Timer timer = new Timer();
        
        DefaultConfig(){
        	super(getConfDir() + "config.properties");
        	try{
        		//TODO should be uncomment subsequently
        		//LoadConfig.get().load(true);
        	} catch(Exception e){
        		e.printStackTrace();
        	}

            load();

            timer.schedule(
	            new TimerTask(){
	
					@Override
					public void run() {
						load();					
					}
	            	
	            }, 1000, 10*1000
            );

            Runtime.getRuntime().addShutdownHook(
            	new Thread(){
                    public void run(){
                        timer.cancel();
                    }
                }
            );
        }
        
        @Override
        void load() {
        	try{
        		long newLastModified = confFile.lastModified();
        		if(newLastModified > 0 && newLastModified != lastModified){
            		super.load();
            		lastModified = newLastModified;
        		}
        	} catch(Exception e){
        		e.printStackTrace();
        	}
        }

        @Override
        public void releaseSource()throws IOException{
        	timer.cancel();
        }

    }

}
