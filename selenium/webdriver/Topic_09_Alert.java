package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		/*
		 * Step 1: Truy cập trang: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Click vào button: Click for JS Alert
		 * Step 3: Verify message hiển thị trong alert là: I am a JS Alert
		 * Step 4: Accept alert and verify message hiển thụ ở Result là: You clicked an alert successfully
		 */
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");	
	}

	@Test
	public void TC_02_Confirm_Alert() {
		/*
		 * Step 1: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Click vào button: Click for JS Confirm
		 * Step 3: Verify message hiển thị trong alert là: I am a JS Confirm
		 * Step 4: Cancel alert and verify message hiển thị tại Result là: You clicked: Cancel
		 */
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}
	
	@Test
	public void TC_03_Prompt_Alert(){
		/*
		 * Step 1: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Click vào button: Click for JS Prompt
		 * Step 3: Verify message hiển thị trong alert là: I am a JS prompt
		 * Step 4: Nhập vào text bất kì (huyendtp) và verify message hiển thị ở Result là: huyendtp
		 */
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("huyendtp");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: huyendtp");

	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		/*
		 * Step 1: Truy cập vào trang: http://the-internet.herokuapp.com/basic_auth
		 * Step 2: Handle authentication alert vs user/pass: admin/admin
		 * Step 3: Verify message hiển thị sau khi login thành công
		 */
		
		/* Sử dụng authenticatin của alert nếu selenium3.+
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		alert.authenticateUsing(new UserAndPassword("admin", "admin"));
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		*/
		// Test case cho selenium 2.53.1
		driver.get("http://the-internet.herokuapp.com/");
		String url = driver.findElement(By.xpath("//a[contains(text(), 'Basic Auth')]")).getAttribute("href");
		driver.get(changeUrl(url, "admin", "admin"));
//		alert = driver.switchTo().alert();
//		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public String changeUrl(String url, String username, String password) {
		
		String [] urlList = url.split("//");
		return urlList[0] + "//" + username + ":" + password + "@" + urlList[1];
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}