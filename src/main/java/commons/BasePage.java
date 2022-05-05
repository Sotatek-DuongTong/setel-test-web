package commons;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


public class BasePage  {
	
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openPageUrl (WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle (WebDriver driver) {
		return driver.getTitle();
	}
	
	
	public void sleepInSecond(long timeout) {
		try {
		Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}
	
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	
	public void clickToElement(WebDriver driver, String locator) {
		getElement(driver, locator).click();
	}
	
	
 	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(value);
	}
	
 	public int getElementSize(WebDriver driver, String locator) {
 		return getElements(driver, locator).size();
 	}
 	
	

	public String getElementAttribute (WebDriver driver, String locator, String attributeName) {
		return getElement(driver, locator).getAttribute(attributeName);
	}
	
	
	public String getElementText (WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}
	
	
	public WebElement getChildElement (WebDriver driver, WebElement parent, String locator) {
		return parent.findElement(By.xpath(locator));
	}
	
	public void checkToCheckboxOrRadio (WebDriver driver, String locator) {
		if (!getElement(driver, locator).isSelected()) {
			getElement(driver, locator).click();
		}
	}
	
	public void uncheckToCheckbox (WebDriver driver, String locator) {
		if (getElement(driver, locator).isSelected()) {
			getElement(driver, locator).click();
		}
	}
	
	public boolean isElementDisplayed (WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public boolean isElementSelected (WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public boolean isElementEnabled (WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}



	public void waitForElementVisible (WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	

	public void waitForAllElementVisible (WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
	}
	
	public void waitForElementClickable (WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	
	public void waitForElementInvisible (WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	
	private WebDriverWait explicitWait;
	protected long timeout = 30;
	SoftAssert soft;
	private JavascriptExecutor jsExecutor;
}
