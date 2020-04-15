package com.amazon.utils;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.amazon.constants.PropertyConstants;

public class ReadPropertiesFile {
	
	private static final Logger LOG = Logger.getLogger(ReadPropertiesFile.class);
	private static Properties properties;

	public static void ReadPropertyFile()
	{
		
		try{
			File resourcesDirectory = new File("src/test/resources");
			
			FileReader reader=new FileReader(resourcesDirectory.getAbsolutePath()+"/amazon.properties");
			properties=new Properties();
			properties.load(reader);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String GetProperty(String key){
		if(properties == null){
			ReadPropertyFile();
		}
		LOG.info("Returnig the value:"+properties.getProperty(key));
		 return properties.getProperty(key);
	}
	
	public static StringBuilder getAppiumURL(){
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(GetProperty(PropertyConstants.APPIUM_SERVER_IP))
					.append(GetProperty(PropertyConstants.APPIUM_SERVER_PORT))
					.append(GetProperty(PropertyConstants.APPIUM_SERVER_DEFAULT_ACCESS_PATH));
		LOG.info("Appium server URL:"+urlBuilder);
		return urlBuilder;
	}	
}
