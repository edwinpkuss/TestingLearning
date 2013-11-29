package platform.testcases;

import base.Navigation;
import base.TestBase;

public class LoginTest extends TestBase{
	
	Navigation navi = new Navigation();
	public void testLogin() throws Exception{
		navi.login();
	}
	
	public static void main(String[] args) throws Exception{
		new LoginTest().testLogin();
	}
}
