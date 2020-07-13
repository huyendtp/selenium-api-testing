package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UploadFile {
	WebDriver driver;
	JavascriptExecutor je;
	String userDir = System.getProperty("user.dir");
	String imageDir = "D:";
	String image1 = "snoopy1.jpg";
	String image2 = "snoopy2.jpg";
	String image3 = "snoopy3.jpg";
	String image1Path = userDir + "\\images\\" +  image1;
	String image2Path = userDir + "\\images\\" + image2;
	String image3Path = userDir + "\\images\\" + image3;
	
	String snoopy1Path = imageDir + "\\images\\" +  image1;
	String snoopy2Path = imageDir + "\\images\\" + image2;
	String snoopy3Path = imageDir + "\\images\\" + image3;
	
	String fireFoxAutoIT = userDir + "\\uploadAutoIT\\uploadFirefox.exe";
	String chromeAutoIT = userDir + "\\uploadAutoIT\\uploadChrome.exe";
	String fireFoxMutipleAutoIT = userDir + "\\uploadAutoIT\\uploadMutipleFirefox.exe";
	String chromeMutipleAutoIT = userDir + "\\uploadAutoIT\\uploadMulFileChrome.exe";

	@BeforeClass
	public void beforeClass() {
		//Firefox bản cũ + selenium bản cũ
		//Firefox bản mới  + selenium bản mới + gekco
//       System.setProperty("webdriver.gecko.driver", userDir + "\\browserDrivers\\geckodriver.exe");
//	   driver = new FirefoxDriver();
//		
		//Chrome mới nhất + selenium bản nào cũng được
		
		System.setProperty("webdriver.chrome.driver", userDir + "\\browserDrivers\\chromedriver83.exe");
		driver = new ChromeDriver();
		
	    je = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_UploadOneFileBySendKey() {
		/*
		 * Step 1: Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		 * Step 2: Sử dụng phương thức sendkeys để upload từng file cho 2 trình duyệt (chrome, firefox)
		 * Step 3: Kiểm tra file đã được upload thành công
		 * Step 4: Nhấn Click Start để upload cả 3 file
		 * Step 5: Kiểm tra 3 file được upload
		 */
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1Path);
		sleepTestCase(1);
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image2Path);
		sleepTestCase(1);
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image3Path);
		sleepTestCase(1);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepTestCase(1);
		Assert.assertEquals(driver.findElements(By.xpath("//tr[@class='template-download fade image in']")).size(), 3);
		
	}

	@Test
	public void TC_01_UploadMultipleFileBySendKey() {
		//selenium mới nhất + testng mới nhất
		/*
		 * Step 1: Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		 * Step 2: Sử dụng phương thức sendkeys để upload 3 file cho 2 trình duyệt (chrome, firefox)
		 * Step 3: Kiểm tra file đã được upload thành công
		 * Step 4: Nhấn Click Start để upload cả 3 file
		 * Step 5: Kiểm tra 3 file được upload
		 */
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");	
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1Path + "\n" + image2Path + "\n" + image3Path);
		sleepTestCase(2);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepTestCase(5);
		Assert.assertEquals(driver.findElements(By.xpath("//tr[@class='template-download fade image in']")).size(), 3);
	}
	
	@Test
	public void TC_02_UploadOneFileByAutoIt() throws IOException{
		/* Chỉ áp dụng cho windows
		 *  Step 1:  Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		 *  Step 2: Sử dụng AutoIT để upload file chạy cho 2 trình duyệt
		 *  Step 3: Kiểm tra file đã được tải lên thành công
		 *
		 */
		
		driver.get(" http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		
		if (driver.toString().contains("firefox")){
			Runtime.getRuntime().exec(new String[] {fireFoxMutipleAutoIT, image1Path});
		}
		else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] {chromeAutoIT, image1Path});
		}
		
		
	}
	
	@Test
	public void TC_02_UploadMultipleFileByAutoIt() throws IOException{
		/* Chỉ áp dụng cho windows
		 *  Step 1:  Truy cập vào trang: http://blueimp.github.com/jQuery-File-Upload/
		 *  Step 2: Sử dụng AutoIT để upload file chạy cho 2 trình duyệt
		 *  Step 3: Kiểm tra file đã được tải lên thành công
		 *
		 */
		
		driver.get(" http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		
		if (driver.toString().contains("firefox")){
			Runtime.getRuntime().exec(new String[] {fireFoxMutipleAutoIT, snoopy1Path, snoopy2Path, snoopy3Path});
		}
		else if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[] {chromeMutipleAutoIT, snoopy1Path, snoopy2Path, snoopy3Path});
		}
	}
	
	@Test
	public void TC_03_UploadFileByRobotClass() throws AWTException {
		/*
		 * Step 1: Truy cập trang: http://blueimp.github.com/jQuery-File-Upload/
		 * Step 2: Sử dụng robot để upload file chạy cho 2 trình duyệt (firefox, chrome)
		 * Step 3: Kiểm tra file đã được tải lên thành công
		 */
		
		driver.get(" http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepTestCase(2);
		
		
		StringSelection  select = new StringSelection(image1Path);
		System.out.print(select);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		Robot robot = new Robot();
		sleepTestCase(2);
		
		// Nhan phim enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepTestCase(2);
		
		// Nhan xuong ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepTestCase(2);
		
		// Nha ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepTestCase(2);
		
		// Nhan phim enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepTestCase(2);
		
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepTestCase(5);
		Assert.assertEquals(driver.findElements(By.xpath("//tr[@class='template-download fade image in']")).size(), 1);
		
	}
	
	@Test
	public void TC_04_UploadFile() {
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
	
	public Boolean isElementDisplayed(String fileName, String icon)
	{
		
		WebElement el  = driver.findElement(By.xpath("//td[contains(text(), '" + fileName + "')]/following-sibling::td/a[contains(@class, '" + icon + "')]"));
		return el.isDisplayed();
		
	}
	
	public void clickElementByJS(WebElement element) {
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