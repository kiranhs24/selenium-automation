package pages;

import org.openqa.selenium.By;

public class RecipeDetail {
	
	public By editButton = By.id("edit-recipe");
	
	public By deleteButton = By.id("delete-recipe");
	
	public By thumbsButton = By.id("like-recipe-button");
	
	public By addToMealPlanButton = By.id("add-to-meal-plan-by-calendar");
	
	public By ingredientsTab = By.id("ingredients-tab");
	
	public By preparationTab = By.id("preparation-tab");
	
	public By nutritionTab = By.id("nutrition-tab");
	
	public By showMoreButton = By.id("show-more");
	
	public By showLessButton = By.id("show-less");
	
	public By recipeTitle = By.id("recipe-title");
	
	public By saveRecipe = By.id("save-recipe");
	
	public By cancelRecipe = By.id("cancel-recipe-save");
	
	public By ingredientsList = By.xpath("//div[contains(@id, 'ingredient')]");

}
