package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import util.Configuration;
import util.Constants;

public class Navigation extends TestBase {
	private static Logger logger = LogManager.getLogger(Navigation.class.getName());
	private String base_url = Configuration.getInstance().getProperty(Constants.BASE_URL);
	private String username = Configuration.getInstance().getProperty(Constants.USER_NAME);
	private String password = Configuration.getInstance().getProperty(Constants.PASSWORD);
	
	
	public void login(){
		login(username, password);
	}
	
	public void login(String username, String password){
		logger.info("start");
		
		selenium.open(base_url);
//		webDriver.get(base_url);
		
		WebElement loginButton = webDriver.findElement(By.xpath("//div[@id='u']//a[.=¡®µÇÂ¼¡¯]"));
		loginButton.click();
	}
}
