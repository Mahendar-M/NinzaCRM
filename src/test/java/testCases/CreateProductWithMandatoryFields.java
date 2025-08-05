package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateProductWithMandatoryFields {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Commondata.properties");

		Properties prop = new Properties();

		prop.load(fis);

		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");
		
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Product");
		Row r = sh.getRow(1);
		String ProductName = r.getCell(0).toString();
		String Quantity = r.getCell(1).toString();
		String ProductPrice = r.getCell(2).toString();
		wb.close();
		
		Random r1 = new Random();
		int rcount = r1.nextInt(1000);
		
		WebDriver driver = null;
		
		if(BROWSER.equals("Edge"))
		{
			driver = new EdgeDriver();
		}
		else if (BROWSER.equals("Chrome"))
		{
			driver = new ChromeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productId")).click();
		driver.findElement(By.name("productName")).sendKeys(ProductName + rcount);

		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys(Quantity);

		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys(ProductPrice);

		WebElement categorydropdown = driver.findElement(By.name("productCategory"));
		Select drop1 = new Select(categorydropdown);
		drop1.selectByValue("Furniture");

		WebElement vendordropdown = driver.findElement(By.name("vendorId"));
		Select drop2 = new Select(vendordropdown);
		drop2.selectByValue("VID_166");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();
		driver.quit();  
		
		
	}

}
