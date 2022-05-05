package commons;

public class Product {
	String prdName;
	Float productPrice;
	String productUrl;
	String webName;

	public Product(String webName, String prdName, Float productPrice, String productUrl) {
		this.prdName = prdName;
		this.productPrice = productPrice;
		this.productUrl = productUrl;
		this.webName = webName;
	}

	public String getProductName() {
		return prdName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public String toString() {
		return  "[Product Price = " + productPrice + ", Website Name = " + webName + ", Product Name = " + prdName + ", Product Url = " + productUrl + "]";
	}
}
