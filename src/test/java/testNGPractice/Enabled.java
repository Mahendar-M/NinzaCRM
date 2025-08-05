package testNGPractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Enabled {
	
	@Test
	public void a11() {
		Reporter.log("a11 executed", true);
	}
	
	@Test(enabled = false)
	public void a15() {
		Reporter.log("a15 executed", true);
			
		}
	@Test(invocationCount = 0)
	public void b55() {
		Reporter.log("b55 executed", true);
	}
	
	@Test
	public void b22() {
		Reporter.log("b22 executed", true);
	}
	@Test
	public void b12() {
		Reporter.log("b12 created", true);
	}
	}
	
	



