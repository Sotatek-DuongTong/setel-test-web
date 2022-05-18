package searchProductsPageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.Product;
import searchProductsInterfaces.dashboardWeb1PageUIs;

public class DashboardWeb1PageObjects extends BasePage {
	WebDriver driver;
	String nameOfWeb1;

	public DashboardWeb1PageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getNameWeb1(WebDriver driver) {
		nameOfWeb1 = getPageTitle(driver);
		return nameOfWeb1;
	}

	public void searchProduct(WebDriver driver, String searchProduct) {
		waitForElementVisible(driver, dashboardWeb1PageUIs.SEARCH_BOX);
		sendkeyToElement(driver, dashboardWeb1PageUIs.SEARCH_BOX, searchProduct);
		clickToElementByJS(driver, dashboardWeb1PageUIs.SEARCH_BUTTON);
	}

	public List<Product> getProductsInfo(WebDriver driver, String webName) {
		List<Product> allProductsEachPage = new ArrayList<Product>();
		waitForAllElementVisible(driver, dashboardWeb1PageUIs.PRODUCT_BOX);

		List<WebElement> allProductElements = getElements(driver, dashboardWeb1PageUIs.PRODUCT_BOX);
		for (WebElement item : allProductElements) {
			String prdName = getChildElement(driver, item, dashboardWeb1PageUIs.PRODUCT_NAME).getText();
			Float productPrice = Float.parseFloat(getChildElement(driver, item, dashboardWeb1PageUIs.PRODUCT_PRICE)
					.getText().replace("from £", "").replace(",", ""));
			String productLink = getChildElement(driver, item, dashboardWeb1PageUIs.PRODUCT_LINK).getAttribute("href");
			allProductsEachPage.add(new Product(webName, prdName, productPrice, productLink));
		}
		return allProductsEachPage;
	}

}
