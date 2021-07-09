package pages;

import org.openqa.selenium.By;

public class TutorialScreen {
	
	public By skip = By.id("skip-tutorial");

	public By next = By.id("go-to-next-tutorial-page");

	public By done = By.id("finish-tutorial");

	public By firstTutorialScreen = By.xpath("//h3[normalize-space()='Meal Planning']");
	
	public By secondTutorialScreen = By.xpath("//h3[normalize-space()='Recipe Selection']");
	
	public By thirdTutorialScreen = By.xpath("//h3[normalize-space()='Taco Tuesdays']");
	
	public By fourthTutorialScreen = By.xpath("//h3[normalize-space()='Editing is Easy']");
	
	public By fifthTutorialScreen = By.xpath("//h3[normalize-space()='We do the Math']");

}
