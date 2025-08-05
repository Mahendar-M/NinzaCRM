package campaign;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import basetest.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.CampaignPage;
import objectRepositories.HomePage;
@Listeners(listenerutility.ListenerImplementation.class)
public class CampaignTest extends BaseClass {
	
	@Test(groups = "smoke")
	public void toCreateCampaignWithMandatoryFieldsTest() throws IOException {

		PropertiesFileUtility putil = new PropertiesFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();

		String campname = eutil.toreadDataFromExcelFile("Sheet1", 1, 2);
		String target = eutil.toreadDataFromExcelFile("Sheet1", 1, 3);

		HomePage hp = new HomePage(driver);
		hp.getCreateampBtn().click();
		CampaignPage cp = new CampaignPage(driver);
		cp.getCampaignNameTF().sendKeys(campname);
		cp.getTargetTF().sendKeys(target);
		cp.getCreateCampSubmitbtn().click();

		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
        Assert.assertTrue(msg.contains(campname));
		cp.getClosemsg().click();

	}
	
	@Test(groups = "smoke")
	public void toCreateCampaignWithStausTest() throws IOException {
		
		
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
		Assert.assertTrue(msg.contains(campname));
		cp.getClosemsg().click();
    

	}
	
	  @Test(groups = "regression")
		public void toCreateCampaignWithExpDateTest() throws EncryptedDocumentException, IOException {
			PropertiesFileUtility putil =new PropertiesFileUtility();
			WebDriverUtility wutil = new WebDriverUtility();
			ExcelFileUtility eutil = new ExcelFileUtility();
			JavaUtility jutil = new JavaUtility();
			
			
			String campname = eutil.toreadDataFromExcelFile("Sheet1", 1, 2);
			String target = eutil.toreadDataFromExcelFile("Sheet1", 1, 3);
			
			
			String daterequired = jutil.ToGetRequiredDate(20);
			
			HomePage hp = new HomePage(driver);
			hp.getCreateampBtn().click();
			CampaignPage cp = new CampaignPage(driver);
			cp.getCampaignNameTF().sendKeys(campname);
			cp.getTargetTF().sendKeys(target);
			
			WebElement expCloseDate = cp.getDateTF();
			wutil.passInput(driver, expCloseDate, daterequired);
			cp.getCreateCampSubmitbtn().click();

			WebElement toastmsg = cp.getToastmsg();
			wutil.waitForVisibilityOfElement(driver, toastmsg);
			String msg = toastmsg.getText();
			Assert.assertTrue(msg.contains(campname));

			cp.getClosemsg().click(); 

			

		}
}
