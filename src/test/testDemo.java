package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class testDemo {
	
	String URL_login = "http://localhost/wordpress/wp-login.php";
	String URL_dashBoard = "http://localhost/wordpress/wp-admin/";
	String user_login = "user_login";
	String user_pass = "user_pass";
	String submitBtn = "wp-submit";

	WebDriver driver;

	public void login(String userName, String password) {	
		driver.findElement(By.id(user_login)).sendKeys(userName);
		driver.findElement(By.id(user_pass)).sendKeys(password);
		driver.findElement(By.id(submitBtn)).click();
	}

	@BeforeMethod
	public void setUp(){
		driver = new ChromeDriver();
		driver.get(URL_login);
	}

	@Test
	public void loginTestPass() {
		login("QuanThang", "Duyquan3112");
		Assert.assertEquals(driver.getCurrentUrl(), URL_dashBoard);
	}

	@Test
	public void loginTestFail1() {
		login("aaaaa", "Duyquan3112");
		Assert.assertNotEquals(driver.getCurrentUrl(), URL_dashBoard);
	}
	
	@Test
	public void loginTestFail2() {
		login("", "");
		Assert.assertNotEquals(driver.getCurrentUrl(), URL_dashBoard);
	}
	
	@Test
	public void loginTestFail3() {
		login("QuanThang", "12345678");
		Assert.assertNotEquals(driver.getCurrentUrl(), URL_dashBoard);
	}
	
	@Test
	public void loginErrorMsg() {
		login("", "");
		Assert.assertEquals(driver.findElement(By.id("login_error")).getText(), "Error: The username field is empty."
			+"\n"	+ "Error: The password field is empty.");
	}
	
	@Test
	public void loginErrorMsgUsername() {
		login("aaaa", "12345678");
		Assert.assertEquals(driver.findElement(By.id("login_error")).getText(), "Error: The username aaaa is not registered on this site. If you are unsure of your username, try your email address instead.");
	}
	
	@Test
	public void loginErrorMsgPassword() {
		login("QuanThang", "12345678");
		Assert.assertEquals(driver.findElement(By.id("login_error")).getText(), "Error: The password you entered for the username QuanThang is incorrect. Bạn quên mật khẩu?");
	}

//	@AfterMethod
//	public void tearDown() {
//		driver.quit();
//	}
}
