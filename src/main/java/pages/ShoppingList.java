package pages;

import org.openqa.selenium.By;

public class ShoppingList {
	
	public By addItemsToCart = By.id("add-items-to-cart");
	
	public By collapseIngredients = By.id("collapse-ingredients");
	
	public By expandIngredients = By.id("expand-ingredients");
	
	public By checkedIngredients = By.xpath("//ul//li//input");
	
	public By uncheckedIngredients = By.xpath("//div[contains(text(), 'in your kitchen')]/./..//li//input");
	
	public By saveToList = By.id("save-shopping-list");
	
	public By myList = By.id("go-to-shopping-list");
	
	public By ok = By.id("create-shopping-list");
	
	public By myListPage = By.xpath("//div[contains(text(), 'Shopping')]");
	
	public By updateList = By.id("update-shopping-list");
	
	public By cancel = By.id("cancel-my-list-navigation");
	
	public By myListIngredients = By.xpath("//div[contains(@id, 'list-item')]");
	
	public By pickUpAdressPopUp = By.xpath("//h3[contains(text(), 'pick up address')]");
	
	public By selectAddress = By.xpath("//button[text()='Use this address']");
	
}
