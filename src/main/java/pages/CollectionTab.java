package pages;

import org.openqa.selenium.By;

public class CollectionTab {
	
	public By collectionBottomNavBar = By.id("bottom-navigation-favorites");
	
	public By collectionThumbsTag = By.xpath("//*[local-name()='svg' and contains(@id, 'favourited')]");
	
	public By collectionUrlSavedTag = By.xpath("//span[contains(@id, 'url-saved')]");
	
	public By collectionOwnRecipeTag = By.xpath("//span[contains(@id, 'own-recipe')]");
	
	public String collectionRecipe = "//div[contains(@id, 'recipe-collection')]";
	
	public By collectionRecipeLocator = By.xpath(collectionRecipe);
	
	public By addRecipeButton = By.id("add-recipe-to-collection");
	
	public By veryOwnRecipe = By.xpath("//a[@href='/your-very-own-recipe']");
	
	public By addRecipeByURL = By.tagName("input");
	
	public By proceed = By.xpath("//button[text()='Proceed']");
	
	public By urlSavedTag = By.xpath("//span[contains(@id, 'url-saved')]");
	
	public By viewOnCollection = By.id("view-in-collection");

}
