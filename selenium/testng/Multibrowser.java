package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Multibrowser {
  WebDriver driver;
  @Test
  public void f() {
	  driver.get("https://www.facebook.com/");
  }
  @BeforeClass
  public void beforeClass() {
	 
  }

  @AfterClass
  public void afterClass() {
  }

}
