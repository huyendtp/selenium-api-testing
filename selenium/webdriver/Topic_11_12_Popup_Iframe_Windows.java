package webdriver;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_12_Popup_Iframe_Windows {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Popup() {
		/*
		 * Step 1: Truy cập vào trang: https://ngoaingu24h.vn
		 * Step 2: Kiểm tra popup có hoặc không xuất hiện
		 * 		+ Có xuất hiện thì nhập thông tin: Tên, sdt, click đăng kí
		 * 		+ Close popup thứ 2
		 * 		+ Không xuất hiện thì qua step 3
		 * Step 3: Nhập từ khóa TOEIC vào khung Search và click search
		 * Step 4: Verify có ít nhất 1 khóa học sau khi Search
		 */
		driver.get("https://ngoaingu24h.vn");
		
		driver.findElement(By.id("header-search-input")).sendKeys("TOEIC");
		sleepTestCase(2);
		driver.findElement(By.id("button-search-main-header-panel")).click();
		Assert.assertTrue(isElelementDisplayed("//label[@id='label-result' and text()='(4 Khoá học)']"));
		
	}

	@Test
	public void TC_02_Popup() {
		/*
		 * Step 1: Truy cập vào trang: https://www.javacodegeeks.com/
		 * Step 2: Kiểm tra popup có hoặc không xuất hiện, có xuất hiện thì close nó
		 * Step 3: Nhập vào email hợp lệ và click Sign up
		 */
		driver.get("https://www.javacodegeeks.com/");
		if (isElelementDisplayed("//div[@id='ulp-AMoliMdl4mr9ETXY']//div[@class='ulp-content']")){
			driver.findElement(By.xpath("//a[text()='Close Popup']")).click();
		}
		
		String email = "huyendtp" + ramdomNumber() + "@gmail.com";
		driver.findElement(By.xpath("//input[@name='ulp-email']")).sendKeys(email);
		clickElementByJS(driver.findElement(By.id("cbsRsocG")));
		clickElementByJS(driver.findElement(By.xpath("//a[text()='Sign up']")));
	}
	
	@Test
	public void TC_03_Iframe(){
		/*
		 * Step 1: Truy cập trang: https://kyna.vn/
		 * Step 2: Verify Facebook iframe hiển thị
		 * Step 3: Verify số lượng like của Facebook là 170k likes
		 * Step 4: Verify Web chat iframe
		 * Step 5: Sendkey vào nhấn Enter - verify form hiển thị
		 * Step 6: Sendkey với từ khóa Java và click Search icon
		 * Step 7: Verify chuyển qua danh sách khóa học có chứa từ từ Java thành công
		 */
		driver.get("https://kyna.vn/");
		driver.switchTo().frame(0);
		String facebookLikes  = driver.findElement(By.className("_1drq")).getText();
		Assert.assertEquals(facebookLikes, "170K likes");
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.className("textarea")).sendKeys("aaa");
		Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER);
        action.perform();
        Assert.assertTrue(driver.findElement(By.tagName("form")).isDisplayed());
        
        driver.switchTo().defaultContent();
        driver.findElement(By.id("live-search-bar")).sendKeys("Java");
        driver.findElement(By.xpath("//button[contains(@class, 'search-button')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()=\"'Java'\"]")).isDisplayed());
	}
	
	@Test
	public void TC_04_WindowsAndTab(){
		/*
		 * Step 1: https://automationfc.github.io/basic-form/index.html
		 * Step 2: Click "GOOGLE" link -> chuyển sang tab mới
		 * Step 3: Kiểm tra title của window mới = Google
		 * Step 4: Switch về parent windows
		 * Step 5: Click "FACEBOOK" link -> switch qua tab mới
		 * Step 6: Kiểm tra title của windows - Facebook - Đăng nhập hoặc đăng ký
		 * Step 7: Switch về parent windows
		 * Step 8: Click TIKI link -> switch qua tab mới
		 * Step 9: Kiểm tra title windows = Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ
		 * Step 10: Close tất cả các windows/tab ngoại trừ parent windows
		 * Step 11: Kiểm tra đã quay về parent windows thành công (title/url)
		 */
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentTitle ="SELENIUM WEBDRIVER FORM DEMO";
		String parentWindows = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowsByTitle("Google");
		Assert.assertEquals("Google", driver.getTitle());
		
		switchToWindowsByTitle(parentTitle);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowsByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals("Facebook - Đăng nhập hoặc đăng ký", driver.getTitle());
		
		switchToWindowsByTitle(parentTitle);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowsByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn", driver.getTitle());
		
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindows: allWindows) {
			if (!runWindows.equals(parentWindows)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindows);
		Assert.assertEquals(parentTitle, driver.getTitle());	
	}
	
	@Test
	public void TC_05_WindowsAndTab() {
		/*
		 * Step 1: Truy cập vào trang: https://kyna.vn/
		 * Step 2: Close popup nếu xuất hiện
		 * Step 3: Click lần lượt các link tại footer
		 * Step 4: Kiểm tra đã chuyển sang tab mới thành công: kiểm tra url hoặc title của bất kì 1 phần tử nào để định danh được page
		 * Step 5: Quay lại parent page and đóng tất cả các tab
		 */
		driver.get("https://kyna.vn/");
		String parentTitle = driver.getTitle();
		String parentId = driver.getWindowHandle();
		
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='facebook']")));
		switchToWindowsByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals("Kyna.vn - Trang chủ | Facebook", driver.getTitle());
		
		switchToWindowsByTitle(parentTitle);
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='youtube']")));
		switchToWindowsByTitle("Kyna.vn - YouTube");
		Assert.assertEquals("Kyna.vn - YouTube", driver.getTitle());
		
		switchToWindowsByTitle(parentTitle);
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='zalo']")));
		switchToWindowsByTitle("Kyna.vn");
		Assert.assertEquals("Kyna.vn", driver.getTitle());
		
		
		switchToWindowsByTitle(parentTitle);
		clickElementByJS(driver.findElement(By.xpath("//img[@alt='apple-app-icon']")));
		switchToWindowsByTitle("‎KYNA on the App Store");
//		Assert.assertEquals("‎KYNA on the App Store", driver.getTitle());
		
		closeWindows(parentId);
		
	}
	
	
	@Test
	public void TC_06_WindowAndTab() {
		// Step 1: Truy cập vào trang: http://live.demoguru99.com/index.php/
		driver.get("http://live.demoguru99.com/index.php/");
		String parentId = driver.getWindowHandle();
		
		// Step 2: Click vào Mobile tab
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		// Step 3: Add sản phẩm Sony Xperia vào Compare (Add to Compare)
		driver.findElement(By.xpath("//a[ @title='Sony Xperia']//parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		// Verify text hiển thị: The product Sony Xperia has been added to comparison list.
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		// Step 4: Add sản phẩm Samsung Galaxy vào Compare (Add to Compare)
		driver.findElement(By.xpath("//a[ @title='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		
		// Verify text hiển thị: The product Samsung Galaxy has been added to comparison list.
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		// Step 5: Click to Compare button
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		// Step 6: Switch qua cửa sổ mới có chứa 2 sản phẩm
		switchToWindowsByTitle("Products Comparison List - Magento Commerce");

		// Step 7: Verify title của cửa sổ bằng: Products Comparison List - Mangeto Commerce
		Assert.assertEquals("Products Comparison List - Magento Commerce", driver.getTitle());
		
		// Step 8: Close tab và chuyển về parent window
		closeWindows(parentId);
		
		// Step 9 Click Clear all và accept alert
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		// Step 10: Verify messge xuất hiện: The comparision list was cleared
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
	}
	
	public void closeWindows(String parentId) {
		Set <String> allWindows = driver.getWindowHandles();
		for (String runWindows: allWindows) {
			if (!runWindows.equals(parentId)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentId);
	}
	
	public boolean isElelementDisplayed(String location) {
		try {
			driver.findElement(By.xpath(location)).isDisplayed();
			return true;
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			return false;
		}
			
	}
	
	public void sleepTestCase(long timeout) {
		try {
			Thread.sleep(timeout*1000);
	    } catch(InterruptedException e){
	    	e.printStackTrace();
	    }
	    	
	  }
	
	public int ramdomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000000);
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
		
		
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}