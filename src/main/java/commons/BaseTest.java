package commons;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;



public class BaseTest {
	private WebDriver driver;
	public SoftAssert soft;
	
	protected WebDriver getBrowserDriver() {
		System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
		soft = new SoftAssert();
		return driver;
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	
	public boolean verifySearchResult(WebDriver driver, List<Product> allSearchResult, String keyword, String nameOfWeb) {
		boolean pass = true;
		try {
			for (Product result : allSearchResult) {
			String resultName = result.getProductName();
			Assert.assertTrue(resultName.contains(keyword));
		}  
			} catch (Throwable e) {
			System.out.println("Exist Product not match keyword " + nameOfWeb + e.getMessage());
			pass = false;
			}
		return pass;
	}
	
	
	public void printResult(WebDriver driver, List<Product> allProducts) {
		System.out.println("Search result in ascending order of price:");
		for (Product result : allProducts) {
			System.out.println(result);
		}
	}
	
	
	public void sortProduct(WebDriver driver, List<Product> searchProducts) {
		Collections.sort(searchProducts, new PriceCompare());
	}
	
}
