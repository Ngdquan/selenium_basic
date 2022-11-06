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
		login("", "");
		Assert.assertEquals(driver.getCurrentUrl(), URL_login);
	}
	
	@Test
	public void loginTestFail2() {
		login("", "");
		Assert.assertEquals(driver.getCurrentUrl(), URL_dashBoard);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
