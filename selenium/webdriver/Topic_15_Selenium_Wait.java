package webdriver;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_Selenium_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String userDir = System.getProperty("user.dir");
	String imageDir = "D:";
	String image1 = "snoopy1.jpg";
	String image2 = "snoopy2.jpg";
	String image3 = "snoopy3.jpg";
	String image1Path = userDir + "\\images\\" +  image1;
	String image2Path = userDir + "\\images\\" + image2;
	String image3Path = userDir + "\\images\\" + image3;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Wait_Visible() {

		driver.get("https://www.facebook.com/");
		driver.findElement(By.name("lastname")).sendKeys("Doan");
		driver.findElement(By.name("reg_email__")).sendKeys("huyen@gmail.com");
		Assert.assertTrue(driver.findElement(By.name("reg_email_confirmation__")).isDisplayed());
	}

//	@Test
	public void TC_02_Wait_InVisible() {
		// Có trong DOM
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

		
	}

//	@Test
	public void TC_02_Wait_InVisible_With_Not_In_Dom() {
		// Không có trong DOM
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("re_email_confirmation__")));

		
	}
	
//	@Test
	public void TC_03_Wait_Presence() {
		// Chỉ cần có trong DOM, ko quan tâm có hiển thị trên UI
		
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));

	}
	
//	@Test
	public void TC_04_Wait_Staleness_In_Dom() {
		// Có trong DOM
		// Tìm element trong dom -> pass
		// Stateless -> fail
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.name("reg_email_confirmation__"))));
	}
	
//	@Test
	public void TC_04_Wait_Staleness_Not_In_Dom() {
		// Không có trong DOM
		// Tìm element trong dom -> fail
		driver.get("https://www.facebook.com/");
		explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.name("re_email_confirmation__"))));

	}
	
	
//	@Test
	public void TC_01_Wait_FindElement() {
		// Không tìm thấy element nào hết, thì chờ hết timeout của implicit
		// Trong thời gian chờ hết timeout -> 0.5s tìm 1 lần
		// Hết timeout thì fail test case
	
			driver.get("https://www.facebook.com/");
			WebElement element = driver.findElement(By.xpath("//button[@name='huyen']"));
		
	}
	
//	@Test
	public void TC_01_Wait_FindElements() {
		// Tìm được nhiều elements, thì thao tác vs thằng đầu tiên
		// Không tìm được, nếu chỉ lấy size thì sẽ ko bị fail test case
		driver.get("https://www.facebook.com/");
		
		List<WebElement> elements = driver.findElements(By.xpath("//button[@name='huyen']"));
		System.out.println("Size of element: " +  elements.size());
		
		List<WebElement> list_elements = driver.findElements(By.xpath("//input[@name='sex']"));
		System.out.println("Size of element: " +  list_elements.size());
		for (WebElement el: list_elements) {
			el.click();
		}
	}
	
//	@Test
	public void TC_02_Static_Wait() throws InterruptedException {
		/*
		 * Step 1: Truy cập trang: http://the-internet.herokuapp.com/dynamic_loading/2
		 * Step 2: Click vào start button
		 * Step 3: Define a static wait (thread.sleep)
		 * Step 4: Chờ cho result text xuất hiện (sử dụng hàm isDisplayed)
		 * Step 5: Check result text  = "Hello world" 
		 */
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
		
	}
	
//	@Test
	public void TC_03_Implicit_Wait(){
		/*
		 * Step 1: Truy cập trang: http://the-internet.herokuapp.com/dynamic_loading/2
		 * Step 2: Click vào start button
		 * Step 3: Define a implicit wait
		 * Step 4: Chờ cho result text xuất hiện (sử dụng hàm isDisplayed)
		 * Step 5: Check result text  = "Hello world" 
		 */
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");	
	
	}
	
	
//	@Test
	public void TC_04_Explicit_Wait(){
		/*
		 * Step 1: Truy cập trang: http://the-internet.herokuapp.com/dynamic_loading/2
		 * Step 2: Click vào start button
		 * Step 3: Define a explicit wait
		 * Step 4: Chờ cho result text xuất hiện (sử dụng hàm isDisplayed)
		 * Step 5: Check result text  = "Hello world" 
		 */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");	
	
	}
	
//	@Test
	public void TC_05_Explicit_Wait_Visible(){
		/*
		 * Step 1: Truy cập trang: http://the-internet.herokuapp.com/dynamic_loading/2
		 * Step 2: Click vào start button
		 * Step 3: Define a explicit wait
		 * Step 4: Chờ cho result text xuất hiện (sử dụng hàm isDisplayed)
		 * Step 5: Check result text  = "Hello world" 
		 */
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");	
	
	}
	
//	@Test
	public void TC_06_ExplicitWait() {
		/*
		 * Step 1: Truy cập vào trang: https://bit.ly/explicit-wait
		 * Step 2: Wait cho Datetime picker được hiển thị (sử dụng presence hoặc visibility)
		 * Step 3: In ra ngày đã chọn (before ajax call). Hiện chưa chọn nên in ra ="No selected dates to display"
		 * Step 4: Chọn ngày hiện tại hoặc 1 ngày bất kì trong năm tháng thiện tại
		 * Step 5: Wait đến khi ajax loading icon ko còn visible
		 * Step 6: Wait cho select date = 22 được chọn, sử dụng visibility
		 * Step 7: Verify ngày được chọn
		 */
		driver.get("https://bit.ly/explicit-wait");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		driver.findElement(By.xpath("//a[text()='3']/parent::td")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style, 'position: absolute')]//div[@class='raDiv']")));
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, June 3, 2020");
	}
	
//	@Test
	public void TC_07_Explicit_Wait_Upload_File()
	{
		/*
		 * Step 1: Truy cập: https://gofile.io/?t=uploadFiles
		 * Step 2: Upload các file và verify file đã được load lên thành công
		 * Step 3: Click upload button
		 * Step 4: Click ok button ở popup sau khi upload thành công
		 * Step 5: Click vào download link
		 * Step 6: Chuyển sang tab/window mới -> kiểm tra có icon download và play hiển thị
		 */
		driver.get("https://gofile.io/?t=uploadFiles");
		driver.findElement(By.id("inputFile")).sendKeys(image1Path + "\n" + image2Path + "\n" + image3Path);
		sleepTestCase(5);
		Assert.assertEquals(driver.findElements(By.xpath("//table[@id='datatable']//td[contains(@title, 'image')]")).size(), 3);
		
		driver.findElement(By.id("btnUpload")).click();
		if (driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).isDisplayed()) {
			driver.findElement(By.xpath("//button[@class='swal2-confirm swal2-styled']")).click();
		}
				
		driver.findElement(By.id("link")).click();
		switchToWindowsByTitle("Gofile");
		
		List<WebElement> buttonVPN = driver.findElements(By.xpath("//button[text()='I have a VPN already']"));
		System.out.println("Size is " + buttonVPN.size());
		if (buttonVPN.size()>0) {
			buttonVPN.get(0).click();
		}
		
		Assert.assertTrue(isElementDisplayed(image1, "download"));
		Assert.assertTrue(isElementDisplayed(image1, "showInfo"));
		Assert.assertTrue(isElementDisplayed(image2, "download"));
		Assert.assertTrue(isElementDisplayed(image2, "showInfo"));
		Assert.assertTrue(isElementDisplayed(image3, "download"));
		Assert.assertTrue(isElementDisplayed(image3, "showInfo"));
		 
	}

//	@Test
	public void TC_08_FluentWait()
	{
		/*
		 * Step 1: Truy cập trang: https://automationfc.github.io/fluent-wait/
		 * Step 2: Wait cho đến khi countdown time được visibility
		 * Step 3: Sử dụng Fluent wait để:
		 *	 + Mỗi 1s kiểm tra countdown  = 2 được xuát hiện trên page hay chưa 
		 */
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement element = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentElement = new FluentWait<WebElement> (element);
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
			.pollingEvery(1,  TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.until(new Function<WebElement, Boolean>(){
				public Boolean apply(WebElement element) {
					boolean flag = element.getText().endsWith("02");
					System.out.print("Time =" + element.getText());
					return flag;
				}
			});
	}
	
	@Test
	public void TC_09_FluentWait() {
		/*
		 * Step 1: Truy cập trang: http://the-internet.herokuapp.com/dynamic_loading/2
		 * Step 2: Click the start button
		 * Step 3: Apply fluent wait để 0,1s  kiểm tra xem Hello word được hiển thị
		 */
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		 waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']"), 10, 1);
	}
	
	public WebElement waitedElement(By locator, long timeout, long interval) {
		
		FluentWait<WebDriver> wait  = new FluentWait<WebDriver>(driver)
		.withTimeout(timeout, TimeUnit.SECONDS)
		.pollingEvery(interval,  TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
		
	}
	public Boolean waitForElementAndDisplayed(By locator, long timeout, long interval) {
		WebElement element   = waitedElement(locator, 5, 1);
		FluentWait<WebElement>wait = new FluentWait<WebElement> (element)
			.withTimeout(timeout, TimeUnit.SECONDS)
			.pollingEvery(interval,  TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class);
		boolean isDisplayed = wait.until(new Function <WebElement, Boolean>(){
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;
		
	}
	public Boolean isElementDisplayed(String fileName, String icon)
	{
		WebElement el  = driver.findElement(By.xpath("//td[contains(text(), '" + fileName + "')]/following-sibling::td/a[contains(@class, '" + icon + "')]"));
		return el.isDisplayed();
		
	}
	public void switchToWindowsByTitle(String title) {
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindows: allWindows) {
			driver.switchTo().window(runWindows);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
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