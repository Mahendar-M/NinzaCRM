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
import objectRepositories.HomePage;
import objectRepositories.LoginPage;
import objectRepositories.ProductPage;

public class CreateProductWithMandatoryFields extends BaseClass {
    @Test
	public void toCreateProductWithMandatoryFields() throws IOException, Throwable {
		PropertiesFileUtility putil =new PropertiesFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		
		
		String prodname = eutil.toreadDataFromExcelFile("Product", 1, 0);
		String quantity1 = eutil.toreadDataFromExcelFile("Product", 1, 1);
		String price1 = eutil.toreadDataFromExcelFile("Product", 1, 2);
		
		
		
		HomePage hp = new HomePage(driver);
		hp.getProduct().click();
		
		ProductPage pp = new ProductPage(driver);
		
		pp.getAddProduct().click();
		pp.getProductId().click();
		pp.getProductName().sendKeys(prodname+jutil.togetRandomNumber());

		WebElement quantity = pp.getQuantity();
		quantity.clear();
		quantity.sendKeys(quantity1);

		WebElement price = pp.getPrice();
		price.clear();
		price.sendKeys(price1);

		WebElement categorydropdown =pp.getProductCategory();
		wutil.select(categorydropdown, 3);

		WebElement vendordropdown = pp.getVendorId();
		wutil.select(vendordropdown, "VID_001");
		pp.getAddProdBtn().click();
		
		
		


	
	}

	}

 
