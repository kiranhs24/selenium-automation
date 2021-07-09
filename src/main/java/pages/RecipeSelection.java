package pages;

import org.openqa.selenium.By;

public class RecipeSelection {
	
	public By recipe = By.xpath("//div[contains(@id, 'recipe-selection')]");
	
	public By recipeSelectionFilter = By.id("recipe-selection-filter-button");
	
	public By plusButton = By.id("add-recipe-to-meal");
	
	public By cancelButton = By.id("cancel-meal-save");
	
	public By saveAndProceed = By.id("save-meal-and-proceed");
	
	public String tickMark = "recipe-added-to-meal";
	
	public By tickMarkLocator = By.id(tickMark);

}
