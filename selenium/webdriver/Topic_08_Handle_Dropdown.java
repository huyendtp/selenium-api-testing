package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Handle_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Dropdown_Jquery(){
		/*
		 * Step 1: Truy cập vào trang https://jqueryui.com/resources/demos/selectmenu/default.html
		 * Step 2: Chọn giá trị 19 ở Dropdown Select a number
		 * Step 3: Verify giá trị vừa chọn
		 */
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItem(By.id("number-button"), By.xpath("//ul[@id='number-menu']//div"), "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
	}

	@Test
	public void TC_02_Dropdown_Angular() {
		/*
		 * Step 1: Truy cập trang: https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding
		 * Step 2: Chọn football ở Dropdown Local Data
		 * Step 3: Verify giá trị vừa chọn
		 */
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItem(By.xpath("//ejs-dropdownlist[@id='games']/span"), By.xpath("//ul[@id='games_options']//li"), "Football");
		Assert.assertEquals(getElementTextByJS("select[id='game_hidden']>option"), "Football");
		
	}
	
	@Test
	public void TC_03_Dropdown_React(){
		/*
		 * Step 1: Truy cập trang: https://react.semantic-ui.com/maximize/dropdown-example-selection/
		 * Step 2: Chọn Matt từ Select Friend
		 * Step 3: Verify giá trị vừa chọn
		 */
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItem(By.xpath("//div[@id='root']//div[contains(text(),'Select Friend')]"), By.xpath("//span[@class='text']"), "Matt");
		Assert.assertEquals(driver.findElement(By.className("text")).getText(), "Matt");
	}
	
	@Test
	public void TC_04_Dropdown_VueJS(){
		/*
		 * Step 1: Truy cập trang: https://mikerodham.github.io/vue-dropdowns/
		 * Step 2: Chọn Second Option
		 * Step 3: Verify giá trị vừa chọn
		 */
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItem(By.xpath("//div[@id='app']//li[@class='dropdown-toggle']"), By.xpath("//ul[@class='dropdown-menu']//a"), "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='app']//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}
	
	@Test
	public void TC_05_Dropdown_Editable(){
		/*
		 * Step 1: Truy cập trang: http://indrimuska.github.io/jquery-editable-select/
		 * Step 2: Chọn Second Option
		 * Step 3: Verify giá trị vừa chọn
		 */
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		selectItem(By.xpath("//div[@id='default-place']/input"), By.xpath("//div[@id='basic-place']//ul[@class='es-list']//li"), "Second Option");

	}
	
	@Test
	public void TC_06_Multiple_Selected() {
		/*
		 * Step 1: Vào trang: http://multiple-select.wenzhixin.net.cn/examples#basic.html
		 * Step 2: Chuyển qua ifame để thao tác
		 * Step 3: Chọn những tháng mong muốn
		 * Step 4: Verify item chọn thành công
		 * 		+ Trường hợp nhỏ hơn 3 thì hiển thị test là: January, February, March
		 * 		+ Trường hợp lớn hơn 3 thì hiển thị số: 5 of 12 selected
		 * 
		 */
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		driver.switchTo().frame(driver.findElement(By.tagName("ifame")));
		
		String [] month = {"January", "February", "March"};
		selectMultipleItems(By.xpath("//button[@class='ms-choice']"), 
							By.xpath("//label[contains(text(), 'Multiple Select')]/following-sibling::div/select/option"),
							month);
		List<WebElement> itemsSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		
	}
	public void selectItem(By parent, By childs, String val) {
		/*
		 * Step 1: Click dropdown cha cho hiện danh sách cần chọn
		 * Step 2: Chọn giá trị mong muốn
		 */
		driver.findElement(parent).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childs));
		List <WebElement> allItems = driver.findElements(childs);
		
		for (WebElement item : allItems) {
			if (item.getText().equals(val)){
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();
				break;
			}
		}
	}
	
	public void selectMultipleItems(By parent, By childs, String[] values) {
		/*
		 * Step 1: Click dropdown cha cho hiện danh sách cần chọn
		 * Step 2: Chọn các giá trị mong muốn
		 */
		driver.findElement(parent).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childs));
		List <WebElement> allItems = driver.findElements(childs);
		
		int count = 0;
		for (WebElement item : allItems) {
			for (String val: values) {
				if (item.getText().equals(val)){
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					jsExecutor.executeScript("arguments[0].click();", item);
					count  = count + 1;
				}
				if (count == values.length) {
					break;
				}
			}
		}
	}
	
	public String getElementTextByJS(String cssLocator) {
		// Sử dụng $ nếu browser có hỗ trợ Jquery
		// return (String)jsExecutor.executeScript("return $(\"" + cssLocator + "\").text");
		
		// Sử dụng DOM nếu browser có hoặc ko hỗ trợ Jquery
		return (String)jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}