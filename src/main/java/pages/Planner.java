package pages;

import org.openqa.selenium.By;

public class Planner {
	
	public By plannerPage = By.xpath("//p[contains(text(), 'Plan Your')]");
	
	public By reviewMealPlanButton = By.id("go-to-review-meal-plan");
	
	public By dinnerMealType = By.id("meal-type-dinner");
	
	public By lunchMealType = By.id("meal-type-lunch");
	
	public By breakfastMealType = By.id("meal-type-breakfast");
	
	public By plannerBottomNavBar = By.id("bottom-navigation-planner");
	
	public By datePicker = By.id("DatePickerContainer");

}
