package testNGPractice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Dependson {
	@Test
	public void addCart() {
		Reporter.log("added", true);
	}
	@Test(dependsOnMethods = "addCart")
	public void editCart() {
		Reporter.log("edited", true);
	}
	@Test(dependsOnMethods = "editCart")
	public void deleteCart() {
		Reporter.log("deleted", true);
	}
	

}
