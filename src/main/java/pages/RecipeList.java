package pages;

import org.openqa.selenium.By;

public class RecipeList {

	public By recipe = By.xpath("//div[contains(@id, 'recipe-list')]");
	
	public By filterButton = By.id("filter-recipes"); 
	
	public By recipeBottomNavBar = By.id("bottom-navigation-recipes");

}
