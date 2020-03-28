package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com");
	}

	@Test
	public void TC_01_VerifyUrl(){
	/*
	 Verify url (getCurrentUrl)
	 	* Step 1: Truy cập vào trang http://live.demoguru99.com
	 	* Step 2: Click MY ACCOUNT link tại footer
	 	* Step 3: Verify url của Login Page = http://live.demoguru99.com/index.php/customer/account/login/
	 	* Step 4: Click CREATE AN ACCOUNT button
		 * Step 5: Verify url của Register Page = http://live.demoguru99.com/index.php/customer/account/create/
		 */
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		String registerPage = driver.getCurrentUrl();
		Assert.assertEquals(registerPage, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		
	}

	@Test
	public void TC_02_VerifyTitle() {
		/*
		 Verify Title (getTitle)
		 	* Step 1: Truy cập vào trang http://live.demoguru99.com
		 	* Step 2: Click MY ACCOUNT link tại footer
		 	* Step 3: Verify title Login Page = Customer Login
		 	* Step 4: Click CREATE AN ACCOUNT button
		 	* Step 5: Verify title của Register Page = Create New Customer Account
		 */
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String titleLoginPage = driver.getTitle();
		Assert.assertEquals(titleLoginPage, "Customer Login");
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		String tilteRegisterPage = driver.getTitle();
		Assert.assertEquals(tilteRegisterPage, "Create New Customer Account");
		
	}
	
	@Test
	public void TC_03_NavigateFunction(){
		/*
		 Navigate function (back/forward)
		 	* Step 1: Truy cập vào trang http://live.demoguru99.com
		 	* Step 2: Click MY ACCOUNT link tại footer
		 	* Step 3: Click CREATE AN ACCOUNT button
		 	* Step 4: Verify url của Register Page = http://live.demoguru99.com/index.php/customer/account/create/
			* Step 5: Back lại trang Login Page
		 	* Step 6: Verify url của Login Page = http://live.demoguru99.com/index.php/customer/account/login/
		 	* Step 7: Forward tới trang Register Page
		 	* Step 8: Verify title của Register Page = Create New Customer Account
		 	*/
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		String registerPage = driver.getCurrentUrl();
		Assert.assertEquals(registerPage, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		String tilteRegisterPage = driver.getTitle();
		Assert.assertEquals(tilteRegisterPage, "Create New Customer Account");
		}
		
	
	@Test
	public void TC_04_GetPageSource(){
		/*
		 Get page source (getPageSource)
		 	* Step 1: Truy cập vào trang http://live.demoguru99.com
		 	* Step 2: Click MY ACCOUNT link tại footer
		 	* Step 3: Verify Login Page chứa text: Login or Create an Account
		 	* Step 4: Click CREATE AN ACCOUNT button 
			* Step 5: Verigy Register Page chứa text : Create an Account
		 	*/
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}