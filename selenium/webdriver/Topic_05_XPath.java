package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_XPath {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_01_By_Id() {
		// Get element by Id and send value to it: Automation Testing
		driver.findElement(By.id("firstname")).sendKeys("Automation Testing");
		sleepTestCase(3);
	}

	@Test
	public void TC_02_By_className() {
		// Get element by classNam and send value to it: password
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.className("value-password")).sendKeys("password");
		sleepTestCase(3);
	}
	
	@Test
	public void TC_03_By_Name(){
		// Get element by name and send value to them: username: dtphuyen2506@gmail.com and password: password
		
		driver.findElement(By.name("login[username]")).sendKeys("dtphuyen2506@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("Password");
		sleepTestCase(3);
	}
	
	@Test
	public void TC_04_By_TagName(){
		// Get all input tags and count them
		
		int count = driver.findElements(By.tagName("input")).size();
		System.out.print("input = " + count);
		
				}
	
	@Test(description="absolute link")
	public void TC_05_LinkText(){
		 // Get absolute link and click them
		
		driver.findElement(By.linkText("CUSTOMER SERVICE")).click();
		sleepTestCase(3);
	}
	
	@Test(description="partial link")
	public void TC_06_PartialText(){
		 // Get partial link and click them
		
		driver.findElement(By.partialLinkText("ACCOUNT")).click();
		sleepTestCase(3);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepTestCase(long timeout) {
		try {
			Thread.sleep(timeout*1000);
	    } catch(InterruptedException e){
	    	e.printStackTrace();
	    }
	    	
	    }
}