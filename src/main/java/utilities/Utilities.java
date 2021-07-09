package utilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.AddRecipeByCalendar;
import pages.CollectionTab;
import pages.PreferenceQuestions;
import pages.RecipeDetail;
import pages.TutorialScreen;

public class Utilities {
	
	static WebDriver driver;
	static Properties prop;
	
	public static String getScreenshot(WebDriver driver, String fileName) throws IOException {

		TakesScreenshot shot = (TakesScreenshot) driver;
		File img = shot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshot/" + fileName + ".png";
		File fileDestination = new File(destination);
		FileUtils.copyFile(img, fileDestination);
		return destination;

	}
	
	public static Properties getUrls() throws IOException {

		FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/config/server_config.properties");
		prop = new Properties();
		prop.load(reader);
		reader.close();
		return prop;
		
	}
	
	public static void throwException(WebDriver driver, NoSuchElementException e, String testName) throws IOException {
		
		getScreenshot(driver, testName + "_" + "failed");
		throw new NoSuchElementException(e.getMessage());
		
	}
	
	public static HashMap<String, String> getNewEmail(String email_id) throws IOException {
		
		HashMap<String, String> userData = new HashMap<String, String>();
		JSONObject request = new JSONObject();
		request.put("name", "Kiran");
		request.put("email", email_id);
		request.put("phoneNumber", "+919988776655");
		
		RestAssured.baseURI ="https://smart-mealplanner.com/api/v1";
		RequestSpecification rs = RestAssured.given();
		rs.header("Content-Type", "application/json");
		rs.body(request.toJSONString());
		rs.relaxedHTTPSValidation();
		Response response = rs.post("/customers");
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().asInputStream()));
		String s = (String) reader.readLine();
		String[] parsed = s.split(":");
		String created_email = parsed[3].split(",")[0].replace("\"", "");
		String userID = s.split(":")[1].split(",")[0];
		userData.put("email", created_email);
		userData.put("id", userID);
		reader.close();
		return userData;
		
	}
	
	public static void deleteCustomer(String customerID) {
		
		RestAssured.baseURI ="https://smart-mealplanner.com/api/v1";
		RequestSpecification rs = RestAssured.given();
		rs.header("Content-Type", "application/json");
		rs.relaxedHTTPSValidation();
		Response response = rs.delete("/customers/" + customerID.replace("\"", "") + "?deleteRelations=true");
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	public static String getText(WebDriver driver, By element) {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();

	}

	public static void verifyText(WebDriver driver, By element, String actual, String type) {

		if (type == "contains") {
			
			assertTrue(driver.findElement(element).getText().contains(actual));

		} else {

			assertEquals(driver.findElement(element).getText(), actual);

		}

	}

	public static void verifyElement(WebDriver driver, By element, String type) {
		
		if(type == "present") {
			assertEquals(driver.findElement(element).isDisplayed(), true);
		}
		
		if(type == "not_present") {
			assertEquals(driver.findElement(element).isDisplayed(), false);
		}

	}
	
	public static boolean isElementPresent(WebDriver driver, By element) {
		
		try {
			
			driver.findElement(element);
			return true;
			
		}catch (NoSuchElementException e) {
			
			return false;
			
		}
		
	}
	
	public static void verifyElements(WebDriver driver, By element) {
		
		assertTrue(( driver.findElements(element).size() == 30 ), "Recipe list does not match the expected count");
		
	}

	public static void clickElement(WebDriver driver, By element) {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).click();

	}

	public static void enterText(WebDriver driver, By element, String text) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element)).sendKeys(text);

	}
	
	public static void scrollToElement(WebDriver driver, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		je.executeScript("arguments[0].scrollIntoView(true)", wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
		
	}

	public static void verifyTutorialScreen(WebDriver driver, TutorialScreen tutorialScreen) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(tutorialScreen.firstTutorialScreen));
		verifyText(driver, tutorialScreen.firstTutorialScreen, "Meal Planning", "");
		verifyElement(driver, tutorialScreen.next, "present");
		verifyElement(driver, tutorialScreen.skip, "present");
		clickElement(driver, tutorialScreen.next);
		
		Thread.sleep(2000);
		verifyText(driver, tutorialScreen.secondTutorialScreen, "Recipe Selection", "");
		verifyElement(driver, tutorialScreen.next, "present");
		verifyElement(driver, tutorialScreen.skip, "present");
		clickElement(driver, tutorialScreen.next);
		
		Thread.sleep(2000);
		verifyText(driver, tutorialScreen.thirdTutorialScreen, "Taco Tuesdays", "");
		verifyElement(driver, tutorialScreen.next, "present");
		verifyElement(driver, tutorialScreen.skip, "present");
		clickElement(driver, tutorialScreen.next);
		
		Thread.sleep(2000);
		verifyText(driver, tutorialScreen.fourthTutorialScreen, "Editing is Easy", "");
		verifyElement(driver, tutorialScreen.next, "present");
		verifyElement(driver, tutorialScreen.skip, "present");
		clickElement(driver, tutorialScreen.next);
		
		Thread.sleep(2000);
		verifyText(driver, tutorialScreen.fifthTutorialScreen, "We do the Math", "");
		verifyElement(driver, tutorialScreen.done, "present");
		clickElement(driver, tutorialScreen.done);
		
	}

	public static void verifyPreferenceScreen(WebDriver driver, PreferenceQuestions preferenceQuestion) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.next));
		verifyText(driver, preferenceQuestion.preferenceFirstQuestion, "Family Members", "");
		verifyElement(driver, preferenceQuestion.next, "present");
		verifyElement(driver, preferenceQuestion.skip, "present");
		clickElement(driver, preferenceQuestion.next);
		Thread.sleep(3000);
		
		verifyText(driver, preferenceQuestion.preferenceSecondQuestion, "food allergies", "contains");
		verifyElement(driver, preferenceQuestion.skip, "present");
		scrollToElement(driver, preferenceQuestion.next);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.next));
		verifyElement(driver, preferenceQuestion.next, "present");
		verifyElement(driver, preferenceQuestion.previous, "present");
		clickElement(driver, preferenceQuestion.next);
		Thread.sleep(2000);
		
		scrollToElement(driver, preferenceQuestion.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.preferenceThirdQuestion));
		verifyText(driver, preferenceQuestion.preferenceThirdQuestion, "preferred diet", "contains");
		verifyElement(driver, preferenceQuestion.skip, "present");
		scrollToElement(driver, preferenceQuestion.next);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.next));
		verifyElement(driver, preferenceQuestion.next, "present");
		verifyElement(driver, preferenceQuestion.previous, "present");
		clickElement(driver, preferenceQuestion.next);
		Thread.sleep(2000);
		
		scrollToElement(driver, preferenceQuestion.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.preferenceFourthQuestion));
		verifyText(driver, preferenceQuestion.preferenceFourthQuestion, "favourite cuisines", "contains");
		verifyElement(driver, preferenceQuestion.skip, "present");
		scrollToElement(driver, preferenceQuestion.next);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.next));
		verifyElement(driver, preferenceQuestion.next, "present");
		verifyElement(driver, preferenceQuestion.previous, "present");
		clickElement(driver, preferenceQuestion.next);
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.preferenceFifthQuestion));
		verifyText(driver, preferenceQuestion.preferenceFifthQuestion, "level of difficulty", "contains");
		verifyElement(driver, preferenceQuestion.skip, "present");
		verifyElement(driver, preferenceQuestion.done, "present");
		
	}
	
	public static void verifyRecipeDetailPage(WebDriver driver, RecipeDetail recipeDetailPage, String pageType) {
		
		verifyElement(driver, recipeDetailPage.thumbsButton, "present");
		
		try {
			
			verifyElement(driver, recipeDetailPage.showMoreButton, "present");
			clickElement(driver, recipeDetailPage.showMoreButton);
			verifyElement(driver, recipeDetailPage.showLessButton, "present");
			
		} catch (NoSuchElementException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		verifyElement(driver, recipeDetailPage.ingredientsTab, "present");
		verifyElement(driver, recipeDetailPage.preparationTab, "present");
		
		try {
			
			verifyElement(driver, recipeDetailPage.nutritionTab, "present");
			
		}catch (NoSuchElementException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		if(pageType == "") {
			return;
		}
			
		if(pageType == "RYML" || pageType == "RL" || pageType == "TS" || pageType == "QRU30" || pageType == "CR")
		{
			verifyElement(driver, recipeDetailPage.addToMealPlanButton, "present");
			
			if(pageType == "CR") {
				
				verifyElement(driver, recipeDetailPage.editButton, "present");
				verifyElement(driver, recipeDetailPage.deleteButton, "present");
				
			}
			
		}
		
	}
	
	public static void verifyThumbsUpTag(WebDriver driver, RecipeDetail recipeDetailPage, CollectionTab collectionTab, String recipeTitle) throws InterruptedException {

		clickElement(driver, collectionTab.collectionBottomNavBar);
		Thread.sleep(3000);
		By favoriteRecipe = By.xpath("//h3[contains(text(), " + "\"" + recipeTitle + "\"" + ")]//parent::div/./.././..");
		verifyElement(driver, favoriteRecipe, "present");
		verifyElement(driver, collectionTab.collectionThumbsTag, "present");
		
	}
	
	public static void addRecipeByCalendar(WebDriver driver, AddRecipeByCalendar addRecipeByCalendar, String mealType) throws InterruptedException {
		
		try {
			
			List<WebElement> todaysDate = driver.findElements(By.xpath(addRecipeByCalendar.todaysDate));
			todaysDate.get(0).click();
			
		} catch (Exception e) {
			
			List<WebElement> todaysDate = driver.findElements(By.xpath(addRecipeByCalendar.todaysDate));
			todaysDate.get(1).click();

		}
		
		Thread.sleep(3000);
		scrollToElement(driver, addRecipeByCalendar.selectMealDropDown);
		Thread.sleep(3000);
		clickElement(driver, addRecipeByCalendar.selectMealDropDown);
		Thread.sleep(3000);
		
		if(mealType == "DINNER") {
			clickElement(driver, addRecipeByCalendar.dinnerMealType);
		}
		else if(mealType == "LUNCH") {
			clickElement(driver, addRecipeByCalendar.lunchMealType);
		}
		else if(mealType == "BREAKFAST") {
			clickElement(driver, addRecipeByCalendar.breakfastMealType);
		}
		Thread.sleep(3000);
		clickElement(driver, addRecipeByCalendar.doneButton);
		
	}

}
