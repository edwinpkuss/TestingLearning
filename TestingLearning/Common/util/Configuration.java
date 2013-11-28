package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class Configuration {
	
	// Implemented as Singleton design pattern ensure a global unique configuration
	// across Social BVT automation project
    private static Configuration _instance = null;

    private Properties props = null;

    private Configuration() {
        props = new Properties();
    	try {
		    FileInputStream fisEnv = new FileInputStream(new File("config/config-staging.properties"));
		    Properties propertiesEnv = new Properties();
		    propertiesEnv.load(fisEnv);
		    props.putAll(propertiesEnv);
		    
//		    FileInputStream fisGlobal = new FileInputStream(new File("config/config-global.properties"));
//		    Properties propertiesGlobal = new Properties();
//		    propertiesGlobal.load(fisGlobal);
//		    props.putAll(propertiesGlobal);
    	}
    	catch (Exception e) {
    	    // catch Configuration Exception right here
    	}
    }

    public synchronized static Configuration getInstance() {
        if (_instance == null) {
        	System.setProperty("file.encoding", "UTF-8");
            _instance = new Configuration();
        }
        return _instance;
    }

    //Get the configuration value by the key
    public String getProperty(String key) {
        
    	String value = null;
        if (props.containsKey(key))
            value = (String) props.get(key);
        
        return value;
    }
}

