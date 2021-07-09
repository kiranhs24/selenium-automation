package pages;

import org.openqa.selenium.By;

public class VeryOwn {
	
	public By title =By.xpath("//input[@name='title']");
	
	public By description = By.xpath("//textarea[@name='description']");
	
	public By cuisine = By.xpath("//div[contains(@id, 'select__cuisine')]");
	
	public By cuisineOption = By.xpath("//li[contains(@data-value, 'Indian')]");
	
	public By ingredient = By.xpath("//input[contains(@placeholder, 'Ingredient 1')]");
	
	public By quantity = By.xpath("//input[contains(@placeholder, 'Quantity')]");
	
	public By measurementDropDown = By.xpath("//div[contains(@id, 'select__ingredients[0].quantity.unit')]");
	
	public By measurementValue = By.xpath("//li[contains(@data-value, 'ounce')]");
	
	public By prepTimeDropDown = By.xpath("//div[contains(@id, 'select__prepTime')]");
	
	public By prepTimeValue = By.xpath("//li[contains(@data-value, '3600')]");
	
	public By cookTimeDropDown = By.xpath("//div[contains(@id, 'select__cookTime')]");
	
	public By cookTimeValue = By.xpath("//li[contains(@data-value, '7200')]");
	
	public By save = By.xpath("//button[@type='submit']");
	
	public By cancel = By.xpath("//button[@cancel='true']");
	
	public By accpetPopUp = By.xpath("//button[text()='Ok']");

}
