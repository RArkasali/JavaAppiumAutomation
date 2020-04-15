package com.amazon.utils;

import com.amazon.constants.PropertyConstants;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks {
	
	private static final Logger LOG = Logger.getLogger(Hooks.class);	
	public static AndroidDriver<MobileElement> driver;
	
	@Before("@regression")
	public void setupDriverDetails() {
		LOG.info("Intiating the drivers based onf properties file configuration");
		if(ReadPropertiesFile.GetProperty(PropertyConstants.EXECUTION_ENVIRONMENT).equals("realdevice"))
			configureAndroidRealDevice();
		else
			configureAndroidEmulator();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	public void configureAndroidRealDevice() {
		if(driver == null) {
			LOG.info("Instantating the Real device capabilities and starting the app");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, ReadPropertiesFile.GetProperty(PropertyConstants.PLATFORM_NAME));
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, ReadPropertiesFile.GetProperty(PropertyConstants.DEVICE_NAME));
			cap.setCapability(MobileCapabilityType.VERSION, ReadPropertiesFile.GetProperty(PropertyConstants.VERSION));
			File resourcesDirectory = new File(ReadPropertiesFile.GetProperty(PropertyConstants.APP_PATH));
			File fs  = new File (resourcesDirectory, ReadPropertiesFile.GetProperty(PropertyConstants.APK_SUB_FOLDER)+ReadPropertiesFile.GetProperty(PropertyConstants.APP));
			cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath() );
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			cap.setCapability(PropertyConstants.CAPABILITY_APP_PACKAGE, ReadPropertiesFile.GetProperty(PropertyConstants.APP_PACKAGE));
			cap.setCapability(PropertyConstants.CAPABILITY_APP_ACTIVITY, ReadPropertiesFile.GetProperty(PropertyConstants.APP_ACTIVITY));
			
			URL url;
			
			try {
				url = new URL(ReadPropertiesFile.getAppiumURL().toString());
				driver = new AndroidDriver<>(url, cap);
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void configureAndroidEmulator() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ReadPropertiesFile.GetProperty(PropertyConstants.EMULATOR_PLATFORM_NAME));
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ReadPropertiesFile.GetProperty(PropertyConstants.EMULATOR_VERSION));
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ReadPropertiesFile.GetProperty(PropertyConstants.EMULATOR_DEVICE_NAME));
		capabilities.setCapability(MobileCapabilityType.UDID, ReadPropertiesFile.GetProperty(PropertyConstants.EMULATOR_UDID_DEVICE));
		capabilities.setCapability(PropertyConstants.CAPABILITY_APP_PACKAGE, ReadPropertiesFile.GetProperty(PropertyConstants.APP_PACKAGE));
		capabilities.setCapability(PropertyConstants.CAPABILITY_APP_ACTIVITY, ReadPropertiesFile.GetProperty(PropertyConstants.APP_ACTIVITY));
		File resourcesDirectory = new File(ReadPropertiesFile.GetProperty(PropertyConstants.APP_PATH));
		File fs  = new File (resourcesDirectory, ReadPropertiesFile.GetProperty(PropertyConstants.APK_SUB_FOLDER)+ReadPropertiesFile.GetProperty(PropertyConstants.APP));
		capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath() );
		
		URL url;
		
		try {
			url = new URL(ReadPropertiesFile.getAppiumURL().toString());
			driver = new AndroidDriver<>(url, capabilities);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.resetApp();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static AndroidDriver<MobileElement> getDriver() {
		return driver;
	}

}
