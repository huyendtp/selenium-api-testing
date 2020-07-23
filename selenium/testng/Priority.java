package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Priority {
 
@Test(groups="test1", priority=2)
  public void f() {
  }
  @Test(groups="test1", priority=1)
  public void f2() {
  }
  
  @Test(groups="test2")
  public void f3() {
  }
  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

}
