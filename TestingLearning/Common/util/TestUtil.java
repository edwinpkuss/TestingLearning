package util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.log4testng.Logger;

//import com.adobe.omniture.sys.util.FileUtil;
import com.google.common.base.Function;
import com.thoughtworks.selenium.Selenium;

public class TestUtil {
	
	private static String screenShotDirectory = "./snapshots/";
	
	public static int ONE_SECOND = 1;
	
	public static int TWO_SECONDS = 2;
	
	public static int FIVE_SECONDS = 5;
	
	public static int TEN_SECONDS = 10;
	
	public final static int HALF_MINUTE = 30;
	
    private static int screenShotNumber = 0;
    
    protected static Logger logger = Logger.getLogger(TestUtil.class);
    
	public static void pauseTest(int seconds) { 
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
		}
	}
	
	public static void maximizeWindow(Selenium selenium){
        selenium.windowMaximize();
    }
	
	public static void maximizeWindow(WebDriver driver){
		driver.manage().window().maximize();
	}
	
	public static WebElement waitForElementPresent(WebDriver driver, final By by) {
	    // Waiting 30 seconds for an element to be present on the page, checking
	    // for its presence once every 5 seconds.
		// Why TestConfig.getInstance().getTimeout() == 30000 SECONDS?
//	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//	        				       .withTimeout (TestConfig.getInstance().getTimeout(), TimeUnit.SECONDS)
//	        				       .pollingEvery(5, TimeUnit.SECONDS)
//	        				       .ignoring(NoSuchElementException.class);
//	    
//	    WebElement expectedElement = wait.until(new Function<WebDriver, WebElement>() {
//		      public WebElement apply(WebDriver driver) {
//		        return driver.findElement(by);
//		      }
//	    });
//	    
//	    return expectedElement;
		return waitForElementPresent(driver, by, 30);
	}
	
	public static WebElement waitForElementPresent(WebDriver driver, final By by, long timeout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout (timeout, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);

		try {
			WebElement expectedElement = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
			
			return expectedElement;
		} catch (Exception e) {
			logger.error("Timeout when waiting an element.");
			return null;
		}
	}
	
	public static void assertElementPresent(Selenium selenium, String xpath) {
			boolean presence = selenium.isElementPresent(xpath);
			Assert.assertEquals(presence, true);
	}
	
	
	public static void setScreenShotDir(String picDir)
	{
		screenShotDirectory = picDir;
	}
	
	public static void takeScreenShot(WebDriver driver) {
		takeScreenShot(driver, detectCallerMethodName());
//		takeScreenShot(driver, new Date().toString());
	}
	
	public static void takeScreenShot(WebDriver driver, String snapshotName) {
		
//  Use these code if using jenkins		
		
//		if (TestUtil.online()) {
//			TestUtil.setScreenShotDir(TestUtil.detectSnapshotsOutputDir());
//			System.out.println("Screen shots output directory is: " + TestUtil.detectSnapshotsOutputDir());
//			System.out.println("Screen shots file nam e is: " + TestUtil.detectCallerMethodName());
//		}
		

// use these code if take screen shot on local
//		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		try {
//			FileUtil.copyFile(scrFile, new File(screenShotDirectory + snapshotName + ".jpg"));
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//  Use these code if take screen shot on TouchStone		
//		SeleniumPage s = new SeleniumPage();
//		s.setWebDriver(driver);
//		try {
////			s.screenshot(snapshotName);
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private static String detectCallerMethodName() {
		
		try {
			StackTraceElement[] stackTraceElements = Thread.currentThread()
					.getStackTrace();
			StackTraceElement curStackTraceElement;
			;

			for (int i = stackTraceElements.length - 1; i >= 0; i--) {
				curStackTraceElement = stackTraceElements[i];
				if (curStackTraceElement.getFileName().toLowerCase()
						.indexOf("test.") != -1) {
					return curStackTraceElement.getMethodName();
				}
			}
		} catch (Exception e) {

		}
		
		return "snapshot";
	}
	
	private static String detectSnapshotsOutputDir() {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StackTraceElement curStackTraceElement;
		
		for (int i = stackTraceElements.length - 1; i >= 0; i--) {
			curStackTraceElement = stackTraceElements[i];
			if (curStackTraceElement.getFileName().toLowerCase().indexOf("testcase") != -1) {
				String fileName = curStackTraceElement.getFileName();
				return "./reports/" + fileName.substring(0, fileName.toLowerCase().indexOf(".java")) + "_files/";
			}
		}
		
		return TestUtil.screenShotDirectory;
		
	}
	
	public synchronized static WebElement findElementByXPath(WebDriver driver, String xpath) {
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			e.printStackTrace();
			element = null;
		}
		return element;
	}
	
	public synchronized static WebElement findElementByLinkText(WebDriver driver, String linkText) {
		WebElement element = null;
		try {
			element = driver.findElement(By.linkText(linkText));
		} catch (Exception e) {
			element = null;
		}
		return element;
	}
	
	public synchronized static List<WebElement> findElementsByXPath(WebDriver driver, String xpath) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(By.xpath(xpath));
		} catch (Exception e) {
			elements = null;
		}
		return elements;
	}
	
	public synchronized static WebElement findElementByXPath(WebElement parent, String xpath) {
		WebElement element = null;
		try {
			element = parent.findElement(By.xpath(xpath));
		} catch (Exception e) {
			element = null;
		}
		return element;
	}
	
	public static boolean online() {
		return true;
		//		try {
		//			String curIpAddress = InetAddress.getLocalHost().getHostAddress();
		//			if (curIpAddress.equals("10.162.181.162"))
		//				return true;
		//			else 
		//				return false;
		//		} catch (UnknownHostException e) {
		//			return true;
		//		} 
	}
	
//	public static int getScreenShotNumber()
//	{
//		screenShotNumber++;
//		return screenShotNumber;
//	}
	
	public static long getScreenShotNumber()
	{
		return new Date().getTime();
	}
	
	public static void resetScreenShotNumber()
	{
		screenShotNumber = 0;
	}
	
}
