package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	public void switchToFrame(WebDriver driver, WebElement frameEle) {
		driver.switchTo().frame(frameEle);
	}

	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void switchtoAlertAndSendKeys(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public String switchToAlertAndGetText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void select(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void select(String text, WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	public void mouseHoverOnwebElement(WebDriver driver, WebElement Element) {
		Actions act = new Actions(driver);
		act.moveToElement(Element).perform();
	}

	public void clickOnWebElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).click().perform();
	}

	public void doubleClickOnWebElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void rightClickOnWebElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void passInput(WebDriver driver, WebElement element, String text) {
		Actions act = new Actions(driver);
		act.click(element).sendKeys(text).perform();
	}

	public void switchToWindow(WebDriver driver) {

		Set<String> windowid = driver.getWindowHandles();
		for (String id : windowid) {

			driver.switchTo().window(id);
		}

	}

	public void takeScreenShot(WebDriver driver, String filename) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File perm = new File("./errorshots/" + filename + ".png");
		FileHandler.copy(temp, perm);

	}

}
