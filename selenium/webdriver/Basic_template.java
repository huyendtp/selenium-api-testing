package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Basic_template {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01() {
	}

	@Test
	public void TC_02() {
	}
	
	@Test
	public void TC_03(){
		
	}
	
	public void sleepTestCase(long timeout) {
		try {
			Thread.sleep(timeout*1000);
	    } catch(InterruptedException e){
	    	e.printStackTrace();
	    }
	    	
	    }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}