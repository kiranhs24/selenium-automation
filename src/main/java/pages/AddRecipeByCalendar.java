package pages;

import java.time.LocalDate;

import org.openqa.selenium.By;

public class AddRecipeByCalendar {
	
	int date = LocalDate.now().getDayOfMonth();
	public String todaysDate = "//p[normalize-space()='" + date + "']//parent::span/parent::button";
	
	public String tomorrowsDate = "//p[normalize-space()='" + ( date + 1 ) + "']//parent::span/parent::button";
	
	public By selectMealDropDown = By.xpath("//div[contains(@id, 'meals')]");
	
	public By dinnerMealType = By.xpath("//li[@data-value='Dinner']");
	
	public By lunchMealType = By.xpath("//li[@data-value='Lunch']");
	
	public By breakfastMealType = By.xpath("//li[@data-value='Breakfast']");
	
	public By doneButton = By.id("add-recipe-by-calendar-done");
	
}
