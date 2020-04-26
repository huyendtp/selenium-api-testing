package webdriver;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
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
	Alert alert;
	JavascriptExecutor javascriptExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascriptExecutor = (JavascriptExecutor) driver;
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
    
	@Test
	public void TC_05_RightClick(){
		/*
		 * Step 1: Truy cập vào trang: http://swisnl.github.io/jQuery-contextMenu/demo.html
		 * Step 2: Right click vào element: right click me
		 * Step 3: Hover chuột vào element: Quit
		 * Step 4: Verify element Quit(visible + hover) với xpath:
		 * 		+ Đây là giá trị ở attribute class không phải dang nói đến visible của Selenium
		 * 		+ Giá trị visible + hover chỉ được hiển thị khi mình hover chuột vào
		 * Step 5: Click chọn Quit
		 * Step 6: Verify alert
		 */
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action = new Actions(driver);
		action.contextClick(driver.findElement(By.className("context-menu-one"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class, 'quit')]"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'quit') and contains(@class, 'visible')  and contains(@class, 'hover')]")).isDisplayed());
		action.click(driver.findElement(By.xpath("//li[contains(@class, 'quit')]"))).perform();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
	}
	
	@Test
	public void TC_06_DragAndDrop() {
		/*
		 * Step 1: Truy cập vào trang: http://demos.telerik.com/kendo-ui/dragdrop/angular
		 * Step 2: Kéo hình tròn nhỏ vào hình tròn lớn
		 * Step 3: Verify message đã thay đổi: You đi great!
		 */
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		action = new Actions(driver);
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();
		action.release();
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
	}
	
	@Test
	public void TC_07_DrapAndDropHTML5() throws IOException{
		/*
		 * Step 1: Truy cập vào trang: https://automationfc.github.io/drag-drop-html5/
		 * Step 2: Sử dụng Javascript/Jquery để kéo thả từ A sang B và ngược lại
		 */
		String rootFolder = System.getProperty("user.dir");
		String javascriptPath = rootFolder + "\\selenium\\webdriver\\helpers\\DragAndDrop.js";
		
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);
		
		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");
		// B to A
		javascriptExecutor.executeScript(java_script);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "A");
	}
	
	@Test
	public void TC_07_DragAndDropBy_Point() throws AWTException{
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepTestCase(5);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");
		
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepTestCase(5);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "A");
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	public void sleepTestCase(long timeout) {
		try {
			Thread.sleep(timeout*1000);
	    } catch(InterruptedException e){
	    	e.printStackTrace();
	    }
	    	
	    }
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}