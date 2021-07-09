package pages;

import org.openqa.selenium.By;

public class ReviewMyMealPlan {
	
	public By convertToShoppingList = By.id("convert-to-shopping-cart");
	
	public By shareMealPlan = By.id("share-meal-plan");
	
	public By removeRecipe = By.xpath("//*[local-name()='svg' and contains(@id, 'delete-recipe')]");
	
	public By moreOptions = By.xpath("//*[local-name()='svg' and contains(@id, 'option-menu')]");
	
	public By editDay = By.xpath("//div[contains(@id, 'edit-meals')]");
	
	public By deleteDay = By.xpath("//div[contains(@id, 'delete-meals')]");
	
	public By dinnerTag = By.xpath("//span[contains(text(), 'DIN')]");
	
	public By lunchTag = By.xpath("//span[contains(text(), 'LUN')]");
	
	public By breakfastTag = By.xpath("//span[contains(text(), 'BRE')]");
	
	public By closeMoreOptions = By.xpath("//*[local-name()='svg' and contains(@id, 'close-option-menu')]");
	
	public By checkBox = By.xpath("//input[@type='checkbox']");

}
