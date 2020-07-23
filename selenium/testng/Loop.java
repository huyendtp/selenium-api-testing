package testng;

import java.util.Random;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Loop {
	  @Test(invocationCount = 10)
	  public void loop() {
		  Random rand = new Random();
		  
		  System.out.println(rand.nextInt(99999));
	  }
	  @BeforeClass
	  public void beforeClass() {
	  }

	  @AfterClass
	  public void afterClass() {
	  }

}
