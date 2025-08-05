package basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import genericUtilities.PropertiesFileUtility;
import genericUtilities.WebDriverUtility;
import objectRepositories.HomePage;
import objectRepositories.LoginPage;

public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver sdriver = null;//for listeners
	public PropertiesFileUtility putil = new PropertiesFileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();

	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethod() throws IOException {
		String BROWSER = putil.togetDataFromPropertiesFile("Browser");
		String URL = putil.togetDataFromPropertiesFile("Url");
		String USERNAME = putil.togetDataFromPropertiesFile("Username");
		String PASSWORD = putil.togetDataFromPropertiesFile("Password");

		driver.get(URL);
		driver.manage().window().maximize();
		wutil.waitForPageToLoad(driver);

		LoginPage lp = new LoginPage(driver);
		
		lp.getUN().sendKeys(USERNAME);
		lp.getPW().sendKeys(PASSWORD);
		lp.getLoginBtn().click();

		System.out.println("login");

	}

	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethod() {
		HomePage hp = new HomePage(driver);
		WebElement icon = hp.getUsericon();
		wutil.mouseHoverOnwebElement(driver, icon);
		WebElement logout = hp.getLogoutBtn();
		wutil.clickOnWebElement(driver, logout);

		System.out.println("logout");
	}
    //@Parameters("BROWSER")
	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClass() throws IOException {
		//String BROWSER=browser;
		String BROWSER = putil.togetDataFromPropertiesFile("Browser");
		if (BROWSER.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		}
		sdriver=driver;
		System.out.println("launching browser");
	}

	@AfterClass(groups = {"smoke","regression"})
	public void afterClass() {
		driver.quit();
		System.out.println("closing browser");
	}

	@BeforeSuite(groups = {"smoke","regression"})
	public void beforeSuite() {
		System.out.println("DB connectivity open");
	}

	@AfterSuite(groups = {"smoke","regression"})
	public void afterSuite() {
		System.out.println("DB close");
		
	}

}



