package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button_Javascript_Executor(){
		/*
		 * Step 1: Truy cập vào trang: http://live.guru99.com/
		 * Step 2: Click vào link My account dưới footer(Sử dụng javascript executor)
		 * Step 3: Kiểm tra url của page khi click vào là: http://live.demoguru99.com/index.php/customer/account/login/
		 * Step 4: Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor cod)
		 * Step 5: Kiểm tra url sau khi click là: http://live.demoguru99.com/index.php/customer/account/create/
		 */
		driver.get("http://live.guru99.com/");
		clickElementByJS(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		clickElementByJS(driver.findElement(By.xpath("//a[@title='Create an Account']")));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Default_CheckBox_Radio_Button() {
		/*
		 * Step 1: Truy cập vào trang: https://demos.telerik.com/kendo-ui/checkbox/index
		 * Step 3: Click chọn vào checkbox: Dual-zone air conditioning
		 * Step 3: Kiểm tra checkbox đó đã chọn
		 * Step 4: Sau khi checkbox đã được chọn, bỏ chọn nó và kiểm tra nó chưa được chọn
		 * Step 5: Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
		 * Step 6: Click chọn radion button: 2.0 Petrol, 147kW
		 * Step 7: Kiểm tra radio button đó đã được chọn hay chưa, nếu chưa thì chọn lại
		 */
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement checkbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		checkbox.click();
		Assert.assertTrue(checkbox.isSelected());
		
		checkbox.click();
		Assert.assertFalse(checkbox.isSelected());
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement radioButton = driver.findElement(By.id("engine6"));
		radioButton.click();
		Assert.assertTrue(radioButton.isSelected());
		if (!radioButton.isSelected()) {
			radioButton.click();
		}
	}
	
	@Test
	public void TC_03_Custom_Checkbox_Or_Radio_Button(){
		/*
		 * Step 1: Truy cập vào trang: https://material.angular.io/components/radio/examples
		 * Step 2: Click vào radio button: Summer
		 * Step 3: Kiểm tra radio đó được chọn hay chưa, nếu chưa thì chọn lại
		 * Step 4: Truy cập vào trang: https://material.angular.io/components/checkbox/examples
		 * Step 5: Click vào checkbox
		 * 		+ Checked
		 * 		+ Indeterminate
		 * Step 6: Kiểm tra checkbox được chọn
		 * Step 7: Sau khi checkbox đã được chọn, thì bỏ chọn nó và kiểm tra nó chưa được chọn
		 */
		driver.get("https://material.angular.io/components/radio/examples");
		clickElementByJS(driver.findElement(By.xpath("//div[contains(text(), 'Summer')]/preceding-sibling::div//input")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Summer')]/preceding-sibling::div//input")).isSelected());
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement checkedBox = driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::div/input"));
		WebElement indenterminateBox = driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::div/input"));
		clickElementByJS(checkedBox);
		Assert.assertTrue(checkedBox.isSelected());
		
		clickElementByJS(indenterminateBox);
		Assert.assertTrue(indenterminateBox.isSelected());
		
		clickElementByJS(checkedBox);
		Assert.assertFalse(checkedBox.isSelected());
		
		clickElementByJS(indenterminateBox);
		Assert.assertFalse(indenterminateBox.isSelected());
		
		
		
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}