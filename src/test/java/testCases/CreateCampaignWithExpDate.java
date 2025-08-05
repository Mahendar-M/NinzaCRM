package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignWithExpDate {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Commondata.properties");

		Properties prop = new Properties();

		prop.load(fis);

		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");
		
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		Row r = sh.getRow(1);
		String campname = r.getCell(2).toString();

		Cell c = r.getCell(3);
		String targetsize = c.getStringCellValue();
		wb.close();
		
		Random r1 = new Random();
		int rcount = r1.nextInt(1000);
		
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 20);
		String daterequired = sim.format(cal.getTime());
		
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
		
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname+rcount);
		driver.findElement(By.name("campaignStatus")).sendKeys("Pass");
		
		WebElement target =driver.findElement(By.name("targetSize"));
		target.clear();
		target.sendKeys(targetsize);
		Thread.sleep(2000);
			
		WebElement expCloseDate = driver.findElement(By.name("expectedCloseDate"));
		Actions act = new Actions(driver);
		act.click(expCloseDate).sendKeys(daterequired).perform();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebElement toasting = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(toasting));
		String msg = toasting.getText();

		if (msg.contains(campname)) {
			System.out.println("Campaign Created");
		} else {
			System.out.println("Campaign Not Created");
		}

		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		WebElement icon =driver.findElement(By.xpath("//div[@class='user-icon']"));
		act.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();
		driver.quit();


	}



	}


