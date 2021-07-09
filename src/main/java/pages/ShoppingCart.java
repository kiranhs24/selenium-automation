package pages;

import java.time.LocalDate;

import org.openqa.selenium.By;

public class ShoppingCart {
	
	int tomorrowsDate = LocalDate.now().getDayOfMonth() + 1;
	
	public By checkout = By.id("cart-checkout");
	
	public By decreaseProductCount = By.id("decrease-product-count");
	
	public By increaseProductCount = By.id("increase-product-count");
	
	public By selectQuantity = By.id("select-product-quantity");
	
	public By addMoreItemsToCart = By.id("add-more-items-to-cart");
	
	public By TotalPrice = By.xpath("//div[contains(text(), 'Total')]");
	
	public By itemPrice = By.xpath("//div[contains(text(), '$')]");
	
	public By goToHome = By.xpath("//button");
	
	public By orderPlacedTag = By.xpath("//div[contains(text(), 'Order Placed')]");
	
	public By orderPendingTag = By.xpath("//div[contains(text(), 'Order Pending')]");
	
	public By tomorrowsSlot = By.xpath("//span[contains(text(), '" + tomorrowsDate + "')]/parent::div");
	
	public By pickUpTime = By.xpath("//span[@aria-disabled='false']");
	
	public By schedulePickup = By.id("schedule-pickup-button");
	
	public By confirmOrder = By.id("confirm-order");
	
}
