package com.amazon.utils;

import com.amazon.constants.PropertyConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class APPHelper {

    private static final Logger LOG = Logger.getLogger(APPHelper.class);
    private static int count = 5;

    private AppiumDriver<MobileElement> driver;
    DeviceActionUtils deviceActionUtils;
    boolean isLandscape = false;

    public APPHelper(AppiumDriver<MobileElement> driver) {
        LOG.info("Intatiating appium driver");
        this.driver = driver;
        deviceActionUtils = new DeviceActionUtils(this.driver);
        deviceActionUtils.setScreenOrientation(ReadPropertiesFile.GetProperty(PropertyConstants.ANDROID_ORIENTATION_MODE));
        isLandscape = deviceActionUtils.isLandscape;
    }

    public void isElementEnabled(String identifier, String locator) {
        MobileElement element = getMobileElement(identifier, locator);
        AssertHelper.assertNotNull(element);
        AssertHelper.assertTrue(element.isEnabled());
    }

    public boolean isElementDisplayed(String identifier, String locator) {
        boolean isElementEnabled = false;
        try {
            waitForElement(identifier, locator);
            isElementEnabled = getMobileElement(identifier, locator).isDisplayed();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isElementEnabled;

    }

    public MobileElement getMobileElement(String identifier, String locator) {

        MobileElement mobileElement = null;
        waitForElement(identifier, locator);
        switch (Identifier.valueOf(identifier)) {
            case id:
                mobileElement = driver.findElementById(locator);
                break;
            case css:
                mobileElement = driver.findElementByClassName(locator);
                break;
            case xpath:
                mobileElement = driver.findElementByXPath(locator);
                break;
            case name:
                mobileElement = driver.findElementByName(locator);
                break;
            default:
                LOG.info("No locator is matching in Switch statemet");
                break;
        }
        return mobileElement;
    }

    public List<MobileElement> getMobileElements(String identifier, String locator) {

        List<MobileElement> mobileElements = null;
        switch (Identifier.valueOf(identifier)) {
            case id:
                LOG.info("Getting list of objects from ID");
                mobileElements = driver.findElementsById(locator);
                break;
            case css:
                mobileElements = driver.findElementsByClassName(locator);
                break;
            case xpath:
                LOG.info("Getting list of objects from xpath" + locator);
                mobileElements = driver.findElementsByXPath(locator);
                break;
            case name:
                mobileElements = driver.findElementsByName(locator);
                break;
            default:
                LOG.info("Identifier is not valid");
                break;
        }
        assert mobileElements != null;
        LOG.info("Size of xpath objects list" + mobileElements.size());
        return mobileElements;
    }

    // Before each click, we are taking screen shots
    public void clickByElement(String identifier, String locator) {
        captureScreenShots();
        scrollTillElementAppearsInLandscape(identifier, locator);
        MobileElement element = getMobileElement(identifier, locator);
        AssertHelper.assertNotNull(element);
        element.click();
    }

    public void clickWithoutScroll(String identifier, String locator) {
        MobileElement element = getMobileElement(identifier, locator);
        AssertHelper.assertNotNull(element);
        element.click();
    }

    public void captureScreenShots() {
        String folder_name = "screenshot";
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        new File(folder_name).mkdir();
        String file_name = df.format(new Date()) + ".png";
        try {
            FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterTextByElement(String identifier, String locator, String text) {
        getMobileElement(identifier, locator).click();
        getMobileElement(identifier, locator).sendKeys(text);
        deviceActionUtils.closeKeyPad();
    }

    public void enterTextWithKeyPad(String identifier, String locator, String text) {
        getMobileElement(identifier, locator).sendKeys(text);
    }

    public void verifySizeofSearResults(String identifier, String locator) {
        List<MobileElement> mobileElements = getMobileElements(identifier, locator);
        Assert.assertTrue(mobileElements.size() >= 1);
    }

    public String getTextByLocator(String identifier, String locator) {
        LOG.info("Getting text by locator" + locator);
        String result = null;
        if (getMobileElements(identifier, locator).size() > 0) {
            result = getMobileElement(identifier, locator).getText();
        }
        LOG.info("Locator text value:" + result);
        return result;
    }

    public void selectProductByName(String identifier, String locator, String productName) {
        String data;
        MobileElement element = getMobileElement(identifier, locator);
        List<MobileElement> elements = element.findElementsByXPath("//android.view.View");

        LOG.info("Size fo products list in products page::" + elements.size());
        for (MobileElement eachElement : elements) {
            data = eachElement.getText();
            System.out.println("product name: " + data);
            if (!data.equals("")) {
                if (data.contains(productName)) {
                    eachElement.click();
                    return;
                }
            }
        }
        /*  recursion
            List<MobileElement> elements = element.findElementsByXPath("//android.view.View");
            this command does not get all elements - it only take element that user can see on the screen
            so we have to get again after scroll down that mean run it again
            this recursion will stop after 6 times to make app will not be crashed
         */
        deviceActionUtils.verticalSwipeByPercentages(0.5, 0.3, 0.2);
        // sleep 0.5 second after scroll up to avoid StaleElementException for next loop
        sleep();

        if (count == 0)
            throw new NoSuchElementException("No search result found");
        else {
            selectProductByName(identifier, locator, productName);
            count--;
        }
    }

    public void scrollByOrientation(MobileElement element) {
        if (isLandscape) {
            deviceActionUtils.scrollToElement(element);
        }
    }

    public void waitForElement(String identifier, String locator) {
        int waitTime = 15;
        if (isLandscape || (ReadPropertiesFile.GetProperty(PropertyConstants.EXECUTION_ENVIRONMENT).equals("emulator"))) {
            System.out.println("Waiting for the element");
            waitTime = 100;
        }

        By by = null;
        switch (Identifier.valueOf(identifier)) {
            case id:
                by = By.id(locator);
                break;
            case css:
                by = By.cssSelector(locator);
                break;
            case xpath:
                by = By.xpath(locator);
                break;
            case name:
                by = By.name(locator);
                break;
            default:
                LOG.info("No locator is matching in Switch statement");
                break;
        }

        Wait wait = new FluentWait(driver).withTimeout(waitTime, TimeUnit.SECONDS).pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean isLocatorDisplayed(String identifier, String locator) {
        By by = null;
        switch (Identifier.valueOf(identifier)) {
            case id:
                by = By.id(locator);
                break;
            case css:
                by = By.cssSelector(locator);
                break;
            case xpath:
                by = By.xpath(locator);
                break;
            case name:
                by = By.name(locator);
                break;
            case linkText:
                by = By.linkText(locator);
                break;
            default:
                LOG.info("No locator is matching in Switch statemet");
                break;
        }

        boolean locatorDispalyed = false;
        try {
            assert by != null;
            locatorDispalyed = driver.findElement(by).isDisplayed();
        } catch (Exception ex) {
            LOG.info("Exception occured while verifying locator");
        }
        return locatorDispalyed;

    }

    public void scrollTillElementAppearsInLandscape(String identifier, String locator) {
        if (isLandscape) {
            for (int i = 0; i < 10; i++) {
                if (isLocatorDisplayed(identifier, locator))
                    deviceActionUtils.verticalSwipeByPercentages(0.5, 0.3, 0.4);
            }
        }
    }

    public void scrollToElement(String identifier, String locator) {
        for (int i = 0; i < 10; i++) {
            if (!isLocatorDisplayed(identifier, locator))
                deviceActionUtils.verticalSwipeByPercentages(0.5, 0.3, 0.2);
        }
    }

    public void scrolltoElementAndClick(String identifier, String locator) {
        for (int i = 1; i < 20; i++) {
            if (!isLocatorDisplayed(identifier, locator)) {
                System.out.println("Locator is not displayed scrolling vertically");
                deviceActionUtils.verticalSwipeByPercentages(0.5, 0.3, 0.3);
            } else {
                System.out.println("Locator displayed");
                getMobileElement(identifier, locator).click();
                break;
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {

        }
    }
}
