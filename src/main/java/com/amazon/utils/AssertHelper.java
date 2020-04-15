package com.amazon.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import io.appium.java_client.MobileElement;

public class AssertHelper{
	
	private static final Logger LOG = Logger.getLogger(AssertHelper.class);
	
	public static void assertTrue(boolean inputValue) {
		LOG.info("Asserting the boolean value");
		Assert.assertTrue(inputValue);
	}
	
	public static void assertNotNullElements(List<MobileElement> list) {
		LOG.info("Asserting the MobileElement List value");
		Assert.assertNotNull(list);
	}
	
	public static void assertNotNull(MobileElement element) {
		LOG.info("Asserting the MobileElement value");
		Assert.assertNotNull(element);
	}

}
