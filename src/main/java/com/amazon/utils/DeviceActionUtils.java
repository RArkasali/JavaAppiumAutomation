package com.amazon.utils;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.android.AndroidTouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class DeviceActionUtils {
	
	private AndroidDriver<MobileElement> driver;
	public boolean isLandscape = false;
	 
    public DeviceActionUtils(AppiumDriver<MobileElement> driver) {
    	this.driver = (AndroidDriver<MobileElement>) driver;
	}
    
    //Tap to an element for 250 milliseconds
    public void tapByElement (AndroidElement androidElement) {
        new TouchAction<AndroidTouchAction>(driver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
 
    //Tap by coordinates
    public void tapByCoordinates (int x,  int y) {
        new TouchAction<AndroidTouchAction>(driver)
                .tap(point(x,y))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
 
    //Press by element
    public void pressByElement (AndroidElement element, long seconds) {
        new TouchAction<AndroidTouchAction>(driver)
                .press(element(element))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }
 
    //Press by coordinates
    public void pressByCoordinates (int x, int y, long seconds) {
        new TouchAction<AndroidTouchAction>(driver)
                .press(point(x,y))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }
 
    //Horizontal Swipe by percentages
    public void horizontalSwipeByPercentage (double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);
 
        new TouchAction<>(driver)
                .press(point(startPoint, anchor))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endPoint, anchor))
                .release().perform();
    }

    // try to use javasript to scroll - deubg

    public void Scroll(String value)   {
        try {
            Thread.sleep(3000);
            String[] values = (value).split(" ");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            HashMap<String, Object> args = new HashMap<String, Object>();
            args.put("direction", values[0]);
            js.executeScript("mobile: scroll", args);
            Thread.sleep(3000);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    //Vertical Swipe by percentages
    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);
 
        new TouchAction<>(driver)
                .press(point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(500)))
                .moveTo(point(anchor, endPoint))
                .release().perform();
    }
 
    //Swipe by elements
    public void swipeByElements (AndroidElement startElement, AndroidElement endElement) {
    	System.out.println("Accessing swipe by element");
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
 
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
 
        new TouchAction<AndroidTouchAction>(driver)
                .press(point(startX,startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }
 
    //Multi touch action by using an android element
    public void multiTouchByElement (AndroidElement androidElement) {
        TouchAction<AndroidTouchAction> press = new TouchAction<AndroidTouchAction>(driver)
                .press(element(androidElement))
                .waitAction(waitOptions(ofSeconds(1)))
                .release();
 
        new MultiTouchAction(driver)
                .add(press)
                .perform();
    }
    
    public void getWindowSize() {
    	Dimension windowSize = driver.manage().window().getSize();
    	System.out.println(windowSize.getHeight());
    	System.out.println(windowSize.getWidth());
    }
    
    public void setScreenOrientation(String orentation) {
    	if(orentation.equalsIgnoreCase("Landscape")) {
    		System.out.println("Rotating the screen to Landscape");
    		driver.rotate(ScreenOrientation.LANDSCAPE);
    		isLandscape = true;
    	}else {
    		driver.rotate(ScreenOrientation.PORTRAIT);
    		isLandscape = false;
    	}
    }
    
    public void closeKeyPad() {
    	driver.hideKeyboard();
    }
    
    public void touchMyCoordinates(int xCordinate,int yCordinate) {
    	TouchAction<AndroidTouchAction> touchAction = new TouchAction<>(driver);
    	touchAction.tap(PointOption.point(xCordinate, yCordinate)).perform();
    }
    
    public void scrollToElement(MobileElement element) {
        	System.out.println("Starated executing the scroll down element");
            boolean flag=true;
            int count=1;
            while(flag){
                try {
                    if(element.isDisplayed()) {
	                    flag=false;
	                    break;
                    }
                }
                catch(Exception NoSuchElementException) {
                    count=count+1;
                    Map<String, Object> params = new HashMap<>();
                    params.put("start","40%,90%");
                    params.put("end","40%,20%");
                    params.put("duration","2");
                    Object res= driver.executeScript("mobile:touch:swipe",params);
	                if(count==5){
	                    break;
	                }
                }
            }
        }
                 
}
