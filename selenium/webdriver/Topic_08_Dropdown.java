package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Dropdownlist() {
		/*
		 *  Step 1: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html
		 *  Step 2: Kiểm tra dropdown Job Role 01 - Single Dropdown không hỗ trợ tính năng multiple select
		 *  Step 3: Chọn giá trị Mobile Testing bằng phương thức selectByVisibleText
		 *  Step 4: Kiểm tra giá trị chọn thành công  
		 * 	Step 5: Chọn giá trị Manual Testing bằng phương thức selectByVisibleText
		 *  Step 6: Kiểm tra giá trị chọn thành công
		 *  Step 7: Chọn giá trị Functional UI Testing bằng phương thức selectByVisibleText
		 *  Step 8: Kiểm tra giá trị chọn thành công
		 *  Step 9: Kiểm tra dropdown có dủ 10 giá trị
		 *  Step 10: Kiểm tra Job Role 02 - Multiple dropdown có hỗ trợ multiple choice
		 *  Step 11: Chọn nhiều giá trị cùng lúc
		 *  	+ Automation
		 *  	+ Mobile
		 *  	+ Desktop
		 *  Step 12: Kiểm tra 3 giá trị được chọn thành công
		 *  Step 13: De-select tất cả 3 giá trị
		 *  Step 14: Kiểm tra không còn giá trị nào được chọn nữa
		 
		 */
		 driver.get("https://automationfc.github.io/basic-form/index.html");
		 Select jobRole01 = new Select(driver.findElement(By.id("job1")));
		 Assert.assertFalse(jobRole01.isMultiple());
		 
		 jobRole01.selectByVisibleText("Mobile Testing");
		 Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Mobile Testing");
		 
		 jobRole01.selectByVisibleText("Manual Testing");
		 Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Manual Testing");
	
		 jobRole01.selectByVisibleText("Functional UI Testing");
		 Assert.assertEquals(jobRole01.getFirstSelectedOption().getText(), "Functional UI Testing");
		 
		 Assert.assertEquals(jobRole01.getOptions().size(), 10);
		 
		 Select jobRole02 = new Select(driver.findElement(By.id("job2")));
		 Assert.assertTrue(jobRole02.isMultiple());
		 
		 jobRole02.selectByVisibleText("Automation");
		 jobRole02.selectByVisibleText("Mobile");
		 jobRole02.selectByVisibleText("Desktop");
		 Assert.assertEquals(jobRole02.getAllSelectedOptions().size(), 3);
		 
		 List<WebElement> elementSelected = jobRole02.getAllSelectedOptions();
		 List<String> valueDropdown = new ArrayList<String>();
		 for (WebElement el : elementSelected){
			 valueDropdown.add(el.getText());
		 }
		 Assert.assertTrue(valueDropdown.contains("Automation"));
		 Assert.assertTrue(valueDropdown.contains("Mobile"));
		 Assert.assertTrue(valueDropdown.contains("Desktop"));
		 
		 jobRole02.deselectAll();
		 Assert.assertEquals(jobRole02.getAllSelectedOptions().size(), 0);
		 
	}
	
	@Test
	public void TC_02_Handle_Dropdown(){
		/*
		 * Step 1:  Truy cập vào trang: https://demo.nopcommerce.com
		 * Step 2: Click Register link trên Header
		 * Step 3: Nhập cá thông tin hợp lệ vào form
		 * 		+ Chọn các giá trị trong dropdown DOB
		 * 			+ Day= 1 -> kiểm tra dropdown có 32 giá trị
		 * 			+ Month = May -> Kiểm tra dropdown có 13 giá trị
		 * 			+ Year  1980 -> Kiểm tra dropdown có 112 giá trị
		 * Step 4: Click Register button
		 * Step 5: Vefiry đã vào Home page sau khi đăng ký thành công
		 * 		+ Có hiện My account
		 * 		+ Có hiện Log out
		 * 		+ Xuất hiện thông báo: Your registration completed
		 */
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Phuong Huyen");
		driver.findElement(By.id("LastName")).sendKeys("Doan");
		Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(day.getOptions().size(), 32);
		day.selectByVisibleText("1");
		
		Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(month.getOptions().size(), 13);
		month.selectByVisibleText("May");
		
		Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(year.getOptions().size(), 112);
		year.selectByVisibleText("1980");
		
		String email = "huyendtp" + ramdomNumber() + "@gmail.com";
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(), "Your registration completed");
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());
		
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