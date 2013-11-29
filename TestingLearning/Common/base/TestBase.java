package base;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.Configuration;
import util.Constants;
import util.TestUtil;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class TestBase{
	protected static Selenium selenium;
	protected WebDriver webDriver;
	private static Logger logger = LogManager.getLogger(TestBase.class.getName());
	
	public TestBase(){
		super();
	}
	
	

	@Before
	public void setUp() {
		
		logger.trace("Set Up...");
		webDriver =  new ChromeDriver();
//		selenium = new WebDriverBackedSelenium(webDriver, Configuration.getInstance().getProperty(Constants.BASE_URL));
		
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.baidu.com/");
		
		logger.info("Opening browser, navigating to " 
				   + Configuration.getInstance().getProperty(Constants.BASE_URL)
				   + ", and maximizing window.");
//		TestUtil.maximizeWindow(selenium);
		TestUtil.maximizeWindow(webDriver);
	}
	
}
