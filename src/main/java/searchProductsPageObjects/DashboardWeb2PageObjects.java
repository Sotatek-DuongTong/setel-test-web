package searchProductsPageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.Product;
import searchProductsInterfaces.dashboardWeb2PageUIs;

public class DashboardWeb2PageObjects extends BasePage {
	WebDriver driver;
	String nameOfWeb2;

	public DashboardWeb2PageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public String getNameWeb2(WebDriver driver) {
		nameOfWeb2 = getPageTitle(driver);
		return nameOfWeb2;
	}

	public void searchProduct(WebDriver driver, String searchProduct) {
		waitForElementVisible(driver, dashboardWeb2PageUIs.SEARCH_BOX);
		sendkeyToElement(driver, dashboardWeb2PageUIs.SEARCH_BOX, searchProduct);
		clickToElement(driver, dashboardWeb2PageUIs.SEARCH_BUTTON);
	}

	public boolean isNextButtonDisplayed() {
		return isElementDisplayed(driver, dashboardWeb2PageUIs.NEXT_BUTTON);
	}

	public List<Product> getProductsInfoEachPage(WebDriver driver, String webName) {
		List<Product> allProductsEachPage = new ArrayList<Product>();
		waitForAllElementVisible(driver, dashboardWeb2PageUIs.PRODUCT_BOX);

		List<WebElement> allProductElements = getElements(driver, dashboardWeb2PageUIs.PRODUCT_BOX);
		for (WebElement item : allProductElements) {
			String prdName = getChildElement(driver, item, dashboardWeb2PageUIs.PRODUCT_NAME).getText();
			Float productPrice = Float.parseFloat(getChildElement(driver, item, dashboardWeb2PageUIs.PRODUCT_PRICE)
					.getText().replace("£", "").replace(",", ""));
			String productLink = getChildElement(driver, item, dashboardWeb2PageUIs.PRODUCT_LINK).getAttribute("href");
			allProductsEachPage.add(new Product(webName, prdName, productPrice, productLink));
		}
		return allProductsEachPage;
	}

	public List<Product> getAllProducts(WebDriver driver, String webName) {
		List<Product> allProducts = new ArrayList<Product>();
		waitForAllElementVisible(driver, dashboardWeb2PageUIs.PRODUCT_BOX);
		allProducts = getProductsInfoEachPage(driver, webName);
		while (isNextButtonDisplayed()) {
			clickToElement(driver, dashboardWeb2PageUIs.NEXT_BUTTON);
			List<Product> newAllProduct = new ArrayList<Product>();
			newAllProduct = getProductsInfoEachPage(driver, webName);
			allProducts.addAll(newAllProduct);
		}

		return allProducts;
	}

}
