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

public class Topic_08_Textbox_TextArea {
	WebDriver driver;
	String emailLogin, userId, password, customerName, gender, birthday, address, city, state, pin, mobile;
	String addressEdit, cityEdit, stateEdit, pinEdit, mobileEdit, emailEdit;

	By customerBy = By.name("name");
	By emailBy = By.name("emailid");
	By passwordBy = By.name("password");
	By birthdayBy = By.id("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By mobileBy = By.name("telephoneno");
	By submitBy = By.name("sub");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		emailLogin = "huyendtp" + ramdomNumber() + "@gmail.com";
		customerName = "huyendtp";
		birthday = "1996-01-05";
		address = "BinhChanh\nBinhSon\nQuangNgai";
		addressEdit = "A\nB\nC";
		city = "BinhChanh";
		cityEdit = "Binh Chanh";
		state = "BinhSon";
		stateEdit = "Binh Son";
		pin = "123456";
		pinEdit = "123455";
		mobile = "1234567891";
		mobileEdit = "1234567892";
		emailEdit = "huyen@gmail.com";

	}

	@Test
	public void TC_01_LoginAndCreateNewUser() {
		/*
		 * Step 1: Truy cập vào trang: http://demo.guru99.com/v4/ + Cick here + Nhập email -> 
		 * 		submit + Lấy thông tin UserId và Password để thực hiện tiếp step 2
		 * Step 2: Đăng nhập với thông tin ở step 1 + Verify homepage được hiển thị thành công 
		 * Step 3: Click chọn link new Customer 
		 * Step 4: Nhập toàn bộ dữ liệu đúng, nhấn submit 
		 * Step 5: Sau khi hệ thống tạo mới customer thành công + Get ra thông tin của Customer ID 
		 * Step 6: Verify tất cả cá thông tin được tạo thành công 
		 * Step 7: Chọn menu Edit Customer -> Nhập Customer ID -> Submit 
		 * Step 8: Tại màn hình Edit Customer + Verify giá trị 2 field Customer Name và Address đúng với dữ liệu tạo mới 
		 * Step 9: Nhập lại giá trị mới tất cả các field (trừ các field bị disable) -> Submit 
		 * Step 10: Verify giá trị tất cả các field đúng với dữ liệu sau khi Edit thành công
		 */

		String loginUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailLogin);
		driver.findElement(By.name("btnLogin")).click();
		userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginUrl);
		driver.findElement(By.name("uid")).sendKeys(userId);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(customerBy).sendKeys(customerName);
		driver.findElement(birthdayBy).sendKeys(birthday);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(mobileBy).sendKeys(mobile);
		driver.findElement(emailBy).sendKeys(emailLogin);
		driver.findElement(passwordBy).sendKeys(password);

		driver.findElement(submitBy).click();

		String customerId = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), birthday);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailLogin);

		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerId);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(customerBy).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(addressBy).getText(), address);
		
		driver.findElement(addressBy).clear();
		driver.findElement(addressBy).sendKeys(addressEdit);
		driver.findElement(cityBy).clear();
		driver.findElement(cityBy).sendKeys(cityEdit);
		driver.findElement(stateBy).clear();
		driver.findElement(stateBy).sendKeys(stateEdit);
		driver.findElement(pinBy).clear();
		driver.findElement(pinBy).sendKeys(pinEdit);
		driver.findElement(mobileBy).clear();
		driver.findElement(mobileBy).sendKeys(mobileEdit);
		driver.findElement(emailBy).clear();
		driver.findElement(emailBy).sendKeys(emailEdit);
		driver.findElement(submitBy).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressEdit.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), cityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), stateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailEdit);

	}

	public int ramdomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}