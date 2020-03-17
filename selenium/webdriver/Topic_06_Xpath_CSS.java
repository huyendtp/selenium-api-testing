package webdriver;

import java.util.Random;
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
    
	@Test
	public void TC_05_LoginValidEmailAndPassword() {
		/*
		 * Step 1: Truy cập trang http://live.demoguru99.com
		 * Step 2: Click vào link "My account" để tới trang đăng nhập
		 * Step 3: Nhập thông tin email và password chính xác
		 * Step 4: Nhấn vào nút Login
		 * Step 5: Kiểm chứng việc hiển thị của My Dashboad, Hello huyendtp, ...
		 * Step 6: Nhấn vào ACCOUNT trên header và nhấn log out để đăng xuất
		 */
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("huyendtp@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()= 'My Dashboard']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//strong[contains(text(), 'Hello, huyen dtp!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(), 'huyen dtp')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(., 'huyendtp@gmail.com')]")).isDisplayed());
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]//a")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//img")).isDisplayed());
	}
	
	@Test
	public void TC_06_CreateANewAccount() {
		/*
		 * Step 1: Truy cập trang http://live.demoguru99.com
		 * Step 2: Click vào link "My account" để tới trang truy cập
		 * Step 3: Click CREATE AN ACCOUNT để tới trang đăng kí tài khoản
		 * Step 4: Nhập thông tin hợp lệ ở FirstName, Lastname, Email, Password, ConfirmPassWord
		 * Step 5: Nhấn REGISTER
		 * Step 6: Kiểm tra việc thông báo xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store
		 * Step 7: Kiểm tra thông tin hiển thị Firstname, lastname, email ở my dashboard
		 * Step 8: Đăng xuất khỏi hệ thống
		 * Step 9: Kiểm tra hệ thống navigate về Home page sau khi đăng xuất thành công
		 */
		String firstName = "Phuong Huyen";
		String lastName  = "Doan";
		String email = "huyendtp" + ramdomNumber() + "@gmail.com";
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		driver.findElement(By.cssSelector("input[id='firstname']")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input[id='lastname']")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input[id='email_address']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[id='password']")).sendKeys("123456");
		driver.findElement(By.cssSelector("input[id='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String succcessMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(succcessMessage, "Thank you for registering with Main Website Store.");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(), '" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(., '" + email + "')]")).isDisplayed());
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]//a")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//img")).isDisplayed());
	}
	public int ramdomNumber() {
        Random rand = new Random(); 
        return  rand.nextInt(1000000);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}