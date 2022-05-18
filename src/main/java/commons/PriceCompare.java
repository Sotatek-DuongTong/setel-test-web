package commons;

import java.util.Comparator;

public class PriceCompare implements Comparator<Product> {
	public int compare(Product first, Product second) {
		return first.getProductPrice().compareTo(second.getProductPrice());
	}
}