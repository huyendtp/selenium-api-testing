package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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

public class Topic_13_JavascriptExecutor {
	WebDriver driver;
	JavascriptExecutor je;
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
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver81.exe");
		driver = new ChromeDriver();
		
		je = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
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
	public void TC_01_Javascript_Executor() {
		/*
		 * Step 1: Truy cập vào trang: http://live.demoguru99.com/
		 * Step 2: Sử dụng JS để get domain của page, verify domain của page = live.demoguru99.com
		 * Step 3: Sử dụng JS để get URL của page và verify url
		 * Step 4: Open MOBILE page Sử dung JS
		 * Step 5: Add sản phẩm  Samsung galaxy vào Cart ( click vào add to cart button bằng js)
		 * Step 6: Verify msg hiển thị: Samsung Galaxy was added to your shopping cart (sử dụng JS get innerText)
		 * Step 7: Mở Customer Service page (Sử dụng JS) và verify title của page
		 * Step 8: Scroll tới element Newsletter textbox nằm ở cuối page (Sử dụng JE)
		 * Step 9: Verify text có hiển thị (Sử dụng JS Get innertext)
		 * Step 10: Navigate tới domain: http://demo.guru99.com/v4/ và verify domain sau khi navigate
		 * 
		 */
		driver.get("http://live.demoguru99.com/");
		Assert.assertEquals(getDomainOfPage(), "live.demoguru99.com");
		
		Assert.assertEquals(getUrlOfPage(), "http://live.demoguru99.com/");
		
		WebElement el = driver.findElement(By.xpath("//a[text()='Mobile']"));
		clickElementByJS(el);
		el = driver.findElement(By.xpath("//a[ @title='Samsung Galaxy']//parent::h2/following-sibling::div[@class='actions']//button[@title='Add to Cart']"));
		clickElementByJS(el);
		System.out.print(getInnerTextOfPageByQuerySelector(".success-msg span"));
		Assert.assertEquals(getInnerTextOfPageByQuerySelector(".success-msg span"), "Samsung Galaxy was added to your shopping cart.");
		
		clickElementByJS(driver.findElement(By.xpath("//a[text() ='Customer Service']")));
		Assert.assertEquals(getTitleOfPage(), "Customer Service");
		
		scrollToElement(driver.findElement(By.id("newsletter")));
		Assert.assertTrue(getInnerTextOfPage().contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		
		
}

	@Test
	public void TC_02_Remove_Attribute() {
		/*
		 * Chỉ chạy cho chrome noặc firefox mới nhất
		 * Step 1: Accesss vào trang: http://demo.guru99.com/v4
		 * Step 2: Đăng nhập
		 * Step 3: Chọn menu New Customer
		 * Step 4: Nhập toàn bộ dữ liệu đúng, click Submit
		 * Step 5: Verify tạo mới khách hàng thành công
		 */
		driver.get("http://demo.guru99.com/v4");
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
		
		removeAttribute("type", driver.findElement(birthdayBy));
		driver.findElement(birthdayBy).sendKeys(birthday);
		
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(mobileBy).sendKeys(mobile);
		driver.findElement(emailBy).sendKeys(emailLogin);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(submitBy).click();
		
		sleepTestCase (5);
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer Registered Successfully!!!");
	}
	
	@Test
	public void TC_03_Create_An_Account(){
		/*
		 * Step 01: Truy cập vào trang: http://live.demoguru99.com/ 
		 * Step 2: Click vào link My Account trên header(ẩn) để tới trang đăng nhập (Sử dụng JE)
		 *	Click trực tiếp vào My Account không click vào Account link
		 * Step 3: Click CREATE AN ACCOUNT button để tới trang trang đăng ký tài khoản (Sử dụng JS)
		 * Step 4: Nhập thông tin hợp lệ vào tất cả các field
		 * Step 5: Click register button (Sử dụng JE)
		 * Step 6: Verify message xuất hiện khi đăng ký thành công
		 * Step 7: Logout khỏi hệ thống (Sử dụng JE)
		 * Step 8: Kiểm tra hệ thống navigate về Home page sau khi logout thành công (Sử dụng hàm isDisplayed để check wait)
		 */
		driver.get("http://live.demoguru99.com/");
		clickElementByJS(driver.findElement(By.xpath("//div[@id='header-account']//a[contains(text(),'My Account')]")));
		clickElementByJS(driver.findElement(By.xpath("//a[@title='Create an Account']")));
		
		String firstName = "Phuong Huyen";
		String lastName  = "Doan";
		String email = "huyendtp" + ramdomNumber() + "@gmail.com";
		
		driver.findElement(By.cssSelector("input[id='firstname']")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input[id='lastname']")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input[id='email_address']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[id='password']")).sendKeys("123456");
		driver.findElement(By.cssSelector("input[id='confirmation']")).sendKeys("123456");
		clickElementByJS(driver.findElement(By.xpath("//button[@title='Register']")));
		
		String succcessMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(succcessMessage, "Thank you for registering with Main Website Store.");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(), '" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(., '" + email + "')]")).isDisplayed());
		
		clickElementByJS(driver.findElement(By.xpath("//a[text()='Log Out']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//img")).isDisplayed());
		
		
	}
   
	public void clickElementByJS(WebElement element) {
		je.executeScript("arguments[0].click();", element);	
	}
	
	public void refreshBrower() {
		je.executeScript("history.go(0)");
	}
	
	public String getInnerTextOfPage() {
		return je.executeScript("return document.documentElement.innerText;").toString();
		
	}
	
	public String getInnerTextOfPageByQuerySelector(String el) {
		return je.executeScript("return document.querySelector('" + el + "').innerText;").toString();
	}
	
	public String getTitleOfPage() {
		return je.executeScript("return document.title;").toString();
	}
	
	public String getUrlOfPage() {
		return je.executeScript("return document.URL;").toString();
	}
	
	public String getDomainOfPage() {
		return je.executeScript("return document.domain;").toString();
	}
	
	public void scrollToPixel(int pixel) {
		String script = "window.scrollBy(0," + pixel + ");";
		je.executeScript(script);
	}
	
	public void scrollToButtomPage() {
		je.executeScript("window.scrollBy(0, document.body.scrollHeigt)");
	}
	
	public void scrollToElement(WebElement element) {
		je.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void hightLightElement(WebElement element) {
		je.executeScript("arguments[0].style.border='2px groov greent'", element);
	}
	
	public void navigateToPage(String url) {
		String location = "window.location='" + url + "'" + ";\"";
		je.executeScript(location);
	}
	
	public void removeAttribute(String attribute, WebElement element) {
		je.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}
	public int ramdomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000000);
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