package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Xpath_CSS {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/");
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		
		String emailNotiError = driver.findElement(By.id("advice-required-entry-email")).getText();
		String passwordNotiError = driver.findElement(By.id("advice-required-entry-pass")).getText();
		
		Assert.assertEquals(emailNotiError, "This is a required field.");
		Assert.assertEquals(passwordNotiError, "This is a required field.");
		
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("huyentest@123.123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		String emailNotiError = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailNotiError, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_LoginWithPasswordLessThanSixCharactors(){
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("huyendtp@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		driver.findElement(By.id("send2")).click();
		
		String passwordNotiError = driver.findElement(By.className("validation-advice")).getText();
		
		Assert.assertEquals(passwordNotiError, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_LoginWithIncorrectPassword(){
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("huyendtp@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345@huyedtp/124");
		driver.findElement(By.id("send2")).click();
		
		String passwordNotiError = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		
		Assert.assertEquals(passwordNotiError, "Invalid login or password.");
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}