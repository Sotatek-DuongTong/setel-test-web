package searchProductsPageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	private static DashboardWeb1PageObjects dashboardWeb1Page;
	private static DashboardWeb2PageObjects dashboardWeb2Page;

	public static DashboardWeb1PageObjects getDashboardWeb1Page(WebDriver driver) {
		if (dashboardWeb1Page == null) {
			dashboardWeb1Page = new DashboardWeb1PageObjects(driver);
		}
		return dashboardWeb1Page;
	}

	public static DashboardWeb2PageObjects getDashboardWeb2Page(WebDriver driver) {
		if (dashboardWeb2Page == null) {
			dashboardWeb2Page = new DashboardWeb2PageObjects(driver);
		}
		return dashboardWeb2Page;
	}

}
