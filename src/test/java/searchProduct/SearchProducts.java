package searchProduct;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.Product;
import searchProductsPageObjects.DashboardWeb1PageObjects;
import searchProductsPageObjects.DashboardWeb2PageObjects;
import searchProductsPageObjects.PageGeneratorManager;

public class SearchProducts extends BaseTest {
	WebDriver driver;
	List<Product> searchResult1 = new ArrayList<Product>();
	List<Product> searchResult2 = new ArrayList<Product>();
	List<Product> allSearchResult = new ArrayList<Product>();

	@BeforeClass
	public void initBrowser() {
		driver = getBrowserDriver();

	}

	@Test
	public void TC_SearchProducts() {
		openPageUrl(driver, GlobalConstants.WEB1);
		dashboardWeb1Page = PageGeneratorManager.getDashboardWeb1Page(driver);
		String nameOfWeb1 = dashboardWeb1Page.getNameWeb1(driver);

		dashboardWeb1Page.searchProduct(driver, GlobalConstants.PRODUCTNAME);
		searchResult1 = dashboardWeb1Page.getProductsInfo(driver, nameOfWeb1);
		verifySearchResult(driver, searchResult1, GlobalConstants.PRODUCTNAME, nameOfWeb1);

		openPageUrl(driver, GlobalConstants.WEB2);
		dashboardWeb2Page = PageGeneratorManager.getDashboardWeb2Page(driver);
		String nameOfWeb2 = dashboardWeb2Page.getNameWeb2(driver);

		dashboardWeb2Page.searchProduct(driver, GlobalConstants.PRODUCTNAME);
		searchResult2 = dashboardWeb2Page.getAllProducts(driver, nameOfWeb2);
		verifySearchResult(driver, searchResult1, GlobalConstants.PRODUCTNAME, nameOfWeb2);

		allSearchResult.addAll(searchResult1);
		allSearchResult.addAll(searchResult2);
		sortProduct(driver, allSearchResult);
		printResult(driver, allSearchResult);

	}

	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}

	DashboardWeb1PageObjects dashboardWeb1Page;
	DashboardWeb2PageObjects dashboardWeb2Page;
}
