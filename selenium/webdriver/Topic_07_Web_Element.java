package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element {
	WebDriver driver;
	WebElement element;
	By emailBox = By.id("mail");
	By passwordBox = By.id("password");
	By ageBoxUnder18 = By.id("under_18");
	By eduBox = By.id("edu");
	By ageDisabledRadio = By.id("radio-disabled");
	By jobRole01 = By.id("job1");
	By jobRole02 = By.id("job2");
	By jobRole03 = By.id("job3");
	By checkBoxInterestsDevelopment = By.id("development");
	By checkBoxInterestsDisabed = By.id("check-disbaled");
	By slider01 = By.id("slider-1");
	By slider02 = By.id("slider-2");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test
	public void TC_01_ElementDisplayed() {
		/*
		 * Step 1: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Kiểm tra các phần tử sau có hiển thị trên trang
		 *		Email
		 *		Age (Under 18)
		 *		Education
		 * Step 3: Nếu có thì nhập giá trị
		 * 		Automation Test vào 2 field Email/Education
		 * 		Chọn Age = Under 18
		 */
		if (isElementDisplayed(emailBox)) {
			driver.findElement(emailBox).sendKeys("Automation Tesing");
		}
		
		if (isElementDisplayed(ageBoxUnder18)) {
			driver.findElement(ageBoxUnder18).click();
		}
		
		if (isElementDisplayed(eduBox)) {
			driver.findElement(eduBox).sendKeys("Automation Testing");
		}
	}

	@Test
	public void TC_02_ElementEnabled() {
		/*
		 * Step 1: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Kiểm tra các phần tử sau có enable trên trang
		 * 		Email
		 * 		Age (Under 18)
		 * 		Job Role 01/ Job Role 02
		 * 		Interest (Development)
		 * 		Slide 01
		 * Step 3: Kiểm tra các phần tử
		 * 		Password
		 * 		Age (Radionbutton is disabled)
		 * 		Job Role 03
		 * 		Interests (Checkbox is disabled)
		 * 		Slider 02 (Disabled)
		 * Step 4: Nếu có in ra các element enabled/disabled
		 */
		 Assert.assertTrue(isElementEnabled(emailBox));
		 Assert.assertTrue(isElementEnabled(ageBoxUnder18));
		 Assert.assertTrue(isElementEnabled(jobRole01));
		 Assert.assertTrue(isElementEnabled(jobRole02));
		 Assert.assertTrue(isElementEnabled(slider01));
		 
		 Assert.assertFalse(isElementEnabled(passwordBox));
		 Assert.assertFalse(isElementEnabled(ageDisabledRadio));
		 Assert.assertFalse(isElementEnabled(jobRole03));
		 Assert.assertFalse(isElementEnabled(checkBoxInterestsDisabed));
		 Assert.assertFalse(isElementEnabled(slider02));
		 
	}
	
	@Test
	public void TC_03_ElementSelected(){
		/*
		 * Step 1: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Click chọn
		 * 		Age (Under 18)
		 * 		Interests (Development) checkbox
		 * Step 3: Kiểm tra các phần tử ở step đã được chọn
		 * Step 4: Click bỏ chọn Interests (Development) checkbox
		 * Step 5: Kiểm tra phần tử ở step 4 được bỏ chọn
		 */
		driver.findElement(ageBoxUnder18).click();
		driver.findElement(checkBoxInterestsDevelopment).click();
		Assert.assertTrue(isElementSelected(ageBoxUnder18));
		Assert.assertTrue(isElementSelected(checkBoxInterestsDevelopment));
		
		driver.findElement(checkBoxInterestsDevelopment).click();
		Assert.assertFalse(isElementSelected(checkBoxInterestsDevelopment));
	}

	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.print("Element [" + by + "] is ENABLED\n");
			return true;
		}
		System.out.print("Element [" + by + "] is DISABLED\n");
		return false;
	}
	
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			return true;
		}
		return false;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}