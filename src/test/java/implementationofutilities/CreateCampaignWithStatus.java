package implementationofutilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import basetest.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.CampaignPage;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;

public class CreateCampaignWithStatus extends BaseClass {
    @Test
	public void toCreateCampaignWithStaus() throws IOException {
		
		
		PropertiesFileUtility putil =new PropertiesFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
	
		String campname = eutil.toreadDataFromExcelFile("Sheet1", 1, 2);
		String target = eutil.toreadDataFromExcelFile("Sheet1", 1, 3);
		
        
		HomePage hp = new HomePage(driver);
		hp.getCreateampBtn().click();
		CampaignPage cp = new CampaignPage(driver);
		cp.getCampaignNameTF().sendKeys(campname);
		cp.getStatusTF().sendKeys("pass");
		cp.getTargetTF().sendKeys(target);
		cp.getCreateCampSubmitbtn().click();
		

		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();

		if (msg.contains(campname)) {
			System.out.println("Campaign Created");
		} else {
			System.out.println("Campaign Not Created");
		}

		cp.getClosemsg().click();
    

	}





	}


