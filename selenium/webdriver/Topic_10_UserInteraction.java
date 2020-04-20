package webdriver;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteraction {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_HoverElement() {
		/*
		 * Step 1: Truy cập trang: http://www.myntra.com/
		 * Step 2: Hover chuột vào DISCOVER
		 * Step 3: Chọn American Eagle
		 * Step 4: Verify đã chuyển sang page và click thành công
		 */
		driver.get("http://www.myntra.com/");
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Discover']"))).perform();
		action.click(driver.findElement(By.xpath("//a[text()='American Eagle']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='American Eagle']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "American Eagle");
	}

	@Test
	public void TC_02_ClickAndHoldElement() {
		/*
		 * Step 1: Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
		 * Step 2: Click and hold từ 1-4
		 * Step 3: Sau khi chọn, kiểm tra có đúng 4 phần tử được chọn
		 */
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		action = new Actions(driver);
		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		action.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
		List <WebElement> itemsChosen = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		List<String> valuesChosen = new ArrayList<String>();
		 for (WebElement el : itemsChosen){
			 valuesChosen.add(el.getText());
		 }
		Assert.assertEquals(itemsChosen.size(), 4);
		Assert.assertTrue(valuesChosen.contains("1"));
		Assert.assertTrue(valuesChosen.contains("2"));
		Assert.assertTrue(valuesChosen.contains("3"));
		Assert.assertTrue(valuesChosen.contains("4"));
	}
	
	@Test
	public void TC_03_ClickAndSelectElement(){
		/*
		 * Step 1: Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html
		 * Step 2: Click and select random item: 1, 3, 6, 11
		 * Step 3: Sau khi chọn kiểm tra 4 phần tử được chọn thành công vs xpath
		 */
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		action = new Actions(driver);
		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
		action.keyDown(Keys.CONTROL).perform();
		listItems.get(0).click();
		listItems.get(2).click();
		listItems.get(5).click();
		listItems.get(10).click();
		action.keyUp(Keys.CONTROL).perform();
		
		List <WebElement> itemsChosen = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		List<String> valuesChosen = new ArrayList<String>();
		 for (WebElement el : itemsChosen){
			 valuesChosen.add(el.getText());
		 }
		Assert.assertEquals(itemsChosen.size(), 4);
		Assert.assertTrue(valuesChosen.contains("1"));
		Assert.assertTrue(valuesChosen.contains("3"));
		Assert.assertTrue(valuesChosen.contains("6"));
		Assert.assertTrue(valuesChosen.contains("11"));	
	}
	
	@Test
	public void TC_04_DoubleClick() {
		/*
		 * Step 1: Truy cập vào trang: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Double click vào button: Double click me
		 * Step 3: Verify text được hiển thị ở result là: Hello Automation Guys!
		 */
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action =  new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}