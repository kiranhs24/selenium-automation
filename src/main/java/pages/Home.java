package pages;

import org.openqa.selenium.By;

public class Home {
	
	public By homePageBottomNavBar = By.id("bottom-navigation-home");
	
	public By planYourMeal = By.id("plan-your-meal");
	
	public By recipeFromYouMayLike = By.xpath("//div[contains(@id, 'cuisine')]");
	
	public By recipesYouMayLike = By.xpath("//p[contains(text(), 'you may like')]");
	
	public By recipeFromTodaySpecial = By.xpath("//div[contains(@id, 'today-special')]");
	
	public By recipeFromQuickRecipesUnder30Mins = By.xpath("//div[contains(@id, 'quick')]");
	
	public By todaysRecipe = By.xpath("//div[contains(@id, 'meal-type')]");
	
	public By viewRecipe = By.xpath("//div[contains(@id, 'view-recipe')]");
	
	public By backButton = By.xpath("//button[@aria-label='back-button']");
	
	public By submitRating = By.xpath("//button[text()='Submit']");
	
}
