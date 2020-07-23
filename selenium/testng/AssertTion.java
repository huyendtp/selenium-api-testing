package testng;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class AssertTion {
  @Test
  public void f() {
	  String demo ="demo";
	  Assert.assertTrue(demo.equals("demo"));
	  Assert.assertFalse(demo.equals("demo1"));
	  Assert.assertEquals(demo, "demo");
	  
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
