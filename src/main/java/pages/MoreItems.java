package pages;

import org.openqa.selenium.By;

public class MoreItems {
	
	public By increaseProductCount = By.xpath("//button[contains(@id, 'increase-count')]");
	
	public By decreaseProductCount = By.xpath("//button[contains(@id, 'decrease-count')]");
	
	public By productTitle = By.xpath("//div[contains(@id, 'product')]");
	
	public By searchBox = By.id("search-product-textbox");

}
