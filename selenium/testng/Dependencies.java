package testng;

import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Dependencies {
	  @Test
	  public void TC_01() {
		  Random rand = new Random();
		  
		  System.out.println(rand.nextInt(99999));
	  }
	  
	  
	  @Test(dependsOnMethods = "TC_01")
	  public void TC_02(){
		  System.out.println("TC 02");
	  }
	  @BeforeClass
	  public void beforeClass() {
	  }

	  @AfterClass
	  public void afterClass() {
	  }

}
