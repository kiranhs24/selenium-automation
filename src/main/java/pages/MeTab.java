package pages;

import org.openqa.selenium.By;

public class MeTab {

	public By meTabBottomNav = By.id("bottom-navigation-profile");
	
	public By addEditPreference = By.xpath("//div[normalize-space()='Add/Edit Preferences']");
	
	public By tutorials = By.xpath("//div[normalize-space()='Tutorial']");
	
	public By logout = By.xpath("//div[normalize-space()='Logout']");
	
	public By addURL = By.xpath("//div[normalize-space()='Add URL']");
	
	public By myList = By.xpath("//div[normalize-space()='Shopping List']");
	
}
