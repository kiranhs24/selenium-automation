package test_cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.BaseClass;
import utilities.Utilities;


public class TestMealPlannerForExistingUser extends BaseClass {

	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor je;
	public HashMap<String, String> userData;
	public String created_email;
	public String userId;
	public int previousShoppingList;

	@BeforeClass
	public void beforeTest() throws IOException, InterruptedException {

		driver = initFirefoxDriver();
		wait = new WebDriverWait(driver, 20);
		je = (JavascriptExecutor) driver;
		String id = UUID.randomUUID().toString().replace("-", "");
		String email = "Kiran" + id + "@gmail.com";
		userData = Utilities.getNewEmail(email);
		created_email = userData.get("email");
		userId = userData.get("id");
		Thread.sleep(2000);
		Utilities.enterText(driver, login.email, created_email);
		Utilities.clickElement(driver, login.proceed);

	}

	@Test(priority = 1)
	public void existingUserLandOnHomePageWhenClickedOnPlannerFromBottomNavigationBar() throws InterruptedException, IOException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.verifyElement(driver, plannerPage.dinnerMealType, "present");
		Utilities.verifyElement(driver, plannerPage.lunchMealType, "present");
		Utilities.verifyElement(driver, plannerPage.breakfastMealType, "present");
		Utilities.verifyElement(driver, plannerPage.reviewMealPlanButton, "present");
		Utilities.verifyElement(driver, plannerPage.plannerPage, "present");

	}

	@Test(priority = 2)
	public void existingUserLandOnHomePageWhenClickedOnPlanYourMealFromHomePage() throws InterruptedException, IOException {
		
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.clickElement(driver, homePage.planYourMeal);
		try {
			Utilities.clickElement(driver, preferenceQuestion.skipToPlan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Utilities.verifyElement(driver, plannerPage.lunchMealType, "present");
		Utilities.verifyElement(driver, plannerPage.breakfastMealType, "present");
		Utilities.verifyElement(driver, plannerPage.reviewMealPlanButton, "present");
		Utilities.verifyElement(driver, plannerPage.plannerPage, "present");

	}

	@Test(priority = 3)
	public void existingUserLandOnRecipeDetailPageWhenClickedOnARecipeFromRecipesYouMayLike() throws InterruptedException, IOException {
		
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.scrollToElement(driver, homePage.recipeFromYouMayLike);
		Utilities.clickElement(driver, homePage.recipeFromYouMayLike);
		Utilities.verifyRecipeDetailPage(driver, recipeDetailPage, "RYML");

	}

	@Test(priority = 4)
	public void existingUserLandOnRecipeDetailPageWhenClickedOnARecipeFromRecipeListPage() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		Utilities.clickElement(driver, recipeList.recipe);
		Utilities.verifyRecipeDetailPage(driver, recipeDetailPage, "RL");

	}

	@Test(priority = 5)
	public void existingUserLandOnRecipeDetailPageWhenClickedOnARecipeFromRecipeSelectionPage()	throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.addToMealPlan));
		Utilities.clickElement(driver, toolTip.addToMealPlan);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.backButton));
		driver.navigate().back();
		wait.until(ExpectedConditions.elementToBeClickable(recipeSelectionPage.recipe));
		Utilities.clickElement(driver, recipeSelectionPage.recipe);
		Utilities.verifyRecipeDetailPage(driver, recipeDetailPage, "");

	}

	@Test(priority = 6)
	public void existingUserLandOnRecipeDetailPageWhenClickedOnARecipeFromTodaysSpecial() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Utilities.clickElement(driver, recipeSelectionPage.cancelButton);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.clickElement(driver, homePage.recipeFromTodaySpecial);
		Utilities.verifyRecipeDetailPage(driver, recipeDetailPage, "TS");

	}

	@Test(priority = 7)
	public void existingUserLandOnRecipeDetailPageWhenClickedOnARecipeFromQuickRecipesUnder30Mins() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.scrollToElement(driver, homePage.recipeFromQuickRecipesUnder30Mins);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.recipeFromQuickRecipesUnder30Mins));
		Utilities.clickElement(driver, homePage.recipeFromQuickRecipesUnder30Mins);
		Utilities.verifyRecipeDetailPage(driver, recipeDetailPage, "QRU30");

	}

	@Test(priority = 8)
	public void existingUserAddRecipeToFavoritesFromRecipeYouMayLikeSection() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.recipeFromYouMayLike));
		Utilities.scrollToElement(driver, homePage.recipeFromYouMayLike);
		Utilities.clickElement(driver, homePage.recipeFromYouMayLike);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.thumbsButton);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.verifyThumbsUpTag(driver, recipeDetailPage, collectionTab, recipeTitle);

	}

	@Test(priority = 9)
	public void existingUserAddRecipeToFavoritesFromRecipeListPage() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		List<WebElement> recipes = driver.findElements(recipeList.recipe);
		recipes.get(5).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.thumbsButton);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.verifyThumbsUpTag(driver, recipeDetailPage, collectionTab, recipeTitle);

	}

	@Test(priority = 10)
	public void existingUserAddRecipeToFavoritesFromRecipeSelectionPage() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.breakfastMealType);
		List<WebElement> recipes = driver.findElements(recipeSelectionPage.recipe);
		recipes.get(8).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.thumbsButton);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.cancelButton));
		Utilities.clickElement(driver, recipeSelectionPage.cancelButton);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.verifyThumbsUpTag(driver, recipeDetailPage, collectionTab, recipeTitle);

	}

	@Test(priority = 11)
	public void existingUserAddRecipeToFavoritesFromTodaysSpecial() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.clickElement(driver, homePage.recipeFromTodaySpecial);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.thumbsButton);
		Thread.sleep(3000);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.verifyThumbsUpTag(driver, recipeDetailPage, collectionTab, recipeTitle);

	}

	@Test(priority = 12)
	public void existingUserAddRecipeToMealPlanFromTodaysSpecial() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.clickElement(driver, homePage.recipeFromTodaySpecial);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "LUNCH");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		Utilities.verifyElement(driver, reviewMealPlanPage.lunchTag, "present");
		Utilities.verifyElement(driver, addedRecipe, "present");

	}

	@Test(priority = 13)
	public void existingUserAddRecipeToMealPlanFromRecipeSelectionPage() throws InterruptedException, IOException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.lunchMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		List<WebElement> recipes = driver.findElements(recipeSelectionPage.plusButton);
		recipes.get(10).click();
		By selectedRecipe = By.xpath("//div[@id='" + recipeSelectionPage.tickMark + "']/./../div[1]/h3");
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectedRecipe));
		String recipeTitle = Utilities.getText(driver, selectedRecipe);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		Thread.sleep(3000);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(addedRecipe));
		Utilities.verifyElement(driver, reviewMealPlanPage.lunchTag, "present");
		Utilities.verifyElement(driver, addedRecipe, "present");

	}

	@Test(priority = 14)
	public void existingUserCancelAddingRecipeToMealPlanFromRecipeSelectionPage() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.breakfastMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		List<WebElement> recipes = driver.findElements(recipeSelectionPage.plusButton);
		recipes.get(12).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.cancelButton));
		Utilities.clickElement(driver, recipeSelectionPage.cancelButton);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.breakfastTag), false);

	}

	@Test(priority = 15)
	public void existingUserAddRecipeToMealPlanFromRecipeListPage() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.elementToBeClickable(reviewMealPlan.convertToShoppingList));
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		List<WebElement> recipe = driver.findElements(recipeList.recipe);
		recipe.get(15).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.doneButton));
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "DINNER");
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(addedRecipe));
		Utilities.verifyElement(driver, addedRecipe, "present");
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.dinnerTag), true);

	}

	@Test(priority = 16)
	public void existingUserAddRecipeToMealPlanFromRecipeYouMayLike() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.scrollToElement(driver, homePage.recipeFromYouMayLike);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.recipeFromYouMayLike));
		Utilities.clickElement(driver, homePage.recipeFromYouMayLike);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.doneButton));
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "BREAKFAST");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		Thread.sleep(3000);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		Utilities.verifyElement(driver, addedRecipe, "present");
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.lunchTag), true);

	}

	@Test(priority = 17)
	public void existingUserAddRecipeToMealPlanFromRecipeListPageFilteredList() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		Utilities.clickElement(driver, recipeList.filterButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(filters.dietStyle));
		Utilities.clickElement(driver, filters.dietStyle);
		driver.findElement(By.id(filters.filterOptions + "1")).click();
		driver.findElement(By.id(filters.filterOptions + "2")).click();
		Utilities.clickElement(driver, filters.apply);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		List<WebElement> recipe = driver.findElements(recipeList.recipe);
		recipe.get(17).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.doneButton));
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "DINNER");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		Thread.sleep(3000);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		Utilities.verifyElement(driver, addedRecipe, "present");
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.breakfastTag), true);

	}

	@Test(priority = 18)
	public void existingUserCheckReviewMyMealPlanIfMealPlanIsCreated() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		Thread.sleep(3000);
		Utilities.verifyElement(driver, reviewMealPlanPage.shareMealPlan, "present");
		Utilities.verifyElement(driver, reviewMealPlanPage.convertToShoppingList, "present");
		Utilities.verifyElement(driver, reviewMealPlanPage.moreOptions, "present");
		Utilities.clickElement(driver, reviewMealPlanPage.moreOptions);
		Utilities.verifyElement(driver, reviewMealPlanPage.editDay, "present");
		Utilities.verifyElement(driver, reviewMealPlanPage.deleteDay, "present");
		Utilities.clickElement(driver, reviewMealPlanPage.closeMoreOptions);
		Utilities.verifyElement(driver, reviewMealPlanPage.removeRecipe, "present");
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.dinnerTag), true);
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.lunchTag), true);
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.breakfastTag), true);

	}

	@Test(priority = 19)
	public void existingUserCheckTutorialsScreenAfterMealPlanIsCreated() throws InterruptedException, IOException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, meTab.logout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(login.email));
		Utilities.enterText(driver, login.email, created_email);
		Thread.sleep(3000);
		Utilities.clickElement(driver, login.proceed);
		assertEquals(Utilities.isElementPresent(driver, tutorialScreen.firstTutorialScreen), false);

	}
	
	@Test(priority = 20)
	public void existingUserAddRecipeToMealPlanFromRecipeSelectionPageFilteredList() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.recipeSelectionFilter));
		Utilities.clickElement(driver, recipeSelectionPage.recipeSelectionFilter);
		Utilities.clickElement(driver, filters.dietStyle);
		driver.findElement(By.id(filters.filterOptions + "1")).click();
		driver.findElement(By.id(filters.filterOptions + "2")).click();
		Utilities.clickElement(driver, filters.apply);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		By selectedRecipe = By.xpath("//div[@id='" + recipeSelectionPage.tickMark + "']/./../div[1]/h3");
		wait.until(ExpectedConditions.visibilityOfElementLocated(selectedRecipe));
		String recipeTitle = Utilities.getText(driver, selectedRecipe);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(addedRecipe));
		Utilities.verifyElement(driver, addedRecipe, "present");
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.dinnerTag), true);

	}
	
	@Test(priority = 21)
	public void existingUserAddRecipeToMealPlanFromRecipeListPageSearch() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		Utilities.clickElement(driver, search.searchButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(search.searchBox));
		Utilities.enterText(driver, search.searchBox, "coffee cake");
		driver.findElement(search.searchBox).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		List<WebElement> recipe = driver.findElements(recipeList.recipe);
		int searchRecipeList = recipe.size();
		recipe.get(searchRecipeList - 2).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.doneButton));
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "BREAKFAST");
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		Utilities.verifyElement(driver, addedRecipe, "present");

	}

	@Test(priority = 22)
	public void existingUserAddRecipeToMealPlanFromRecipeSelectionPageSearch() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.breakfastMealType);
		Utilities.clickElement(driver, search.searchButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(search.searchBox));
		Utilities.enterText(driver, search.searchBox, "coffee cake");
		driver.findElement(search.searchBox).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		List<WebElement> recipe = driver.findElements(recipeSelectionPage.plusButton);
		int searchRecipeList = recipe.size();
		recipe.get(searchRecipeList - 2).click();
		By selectedRecipe = By.xpath("//div[@id='" + recipeSelectionPage.tickMark + "']/./../div[1]/h3");
		String recipeTitle = Utilities.getText(driver, selectedRecipe);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		Utilities.verifyElement(driver, addedRecipe, "present");

	}
	
	@Test(priority = 23)
	public void existingUserNavigateToMyListFromShoppingListPage() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.breakfastTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.saveToList));
		Utilities.clickElement(driver, shoppingList.saveToList);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.myList));
		Utilities.clickElement(driver, shoppingList.myList);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.myListIngredients));
		assertTrue(driver.findElements(shoppingList.myListIngredients).size() > 0);
		
	}
	
	@Test(priority = 24)
	public void existingUserCheckMyListPageAfterSavingShoppingList() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.breakfastTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.saveToList));
		Utilities.clickElement(driver, shoppingList.saveToList);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.updateList));
		Utilities.clickElement(driver, shoppingList.updateList);
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().back();
		wait.until(ExpectedConditions.elementToBeClickable(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, meTab.myList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.myListPage));
		assertTrue(driver.findElements(shoppingList.myListIngredients).size() > 0);
		previousShoppingList = driver.findElements(shoppingList.myListIngredients).size();
		
	}
	
	@Test(priority = 25)
	public void existingUserUpdateAlternativeListPage() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.scrollToElement(driver, plannerPage.lunchMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.lunchMealType));
		Utilities.clickElement(driver, plannerPage.lunchMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		List<WebElement> recipes = driver.findElements(recipeSelectionPage.plusButton);
		recipes.get(22).click();
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.elementToBeClickable(reviewMealPlan.lunchTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.saveToList));
		Utilities.clickElement(driver, shoppingList.saveToList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.updateList));
		Utilities.clickElement(driver, shoppingList.updateList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.myListPage));
		assertTrue(driver.findElements(shoppingList.myListIngredients).size() >= previousShoppingList);
		previousShoppingList = driver.findElements(shoppingList.myListIngredients).size();
		
	}

	@Test(priority = 26)
	public void existingUserCancelUpdatingAlternativeListPage() throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.backButton));
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.lunchMealType));
		Utilities.scrollToElement(driver, plannerPage.lunchMealType);
		Utilities.clickElement(driver, plannerPage.lunchMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		List<WebElement> recipes = driver.findElements(recipeSelectionPage.plusButton);
		je.executeScript("arguments[0].scrollIntoView(true)", recipes.get(16));
		recipes.get(16).click();
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.elementToBeClickable(reviewMealPlan.lunchTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.saveToList));
		Utilities.clickElement(driver, shoppingList.saveToList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.cancel));
		Utilities.clickElement(driver, shoppingList.cancel);
		Thread.sleep(3000);
		driver.navigate().back();
		wait.until(ExpectedConditions.elementToBeClickable(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, meTab.myList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.myListPage));
		assertTrue(driver.findElements(shoppingList.myListIngredients).size() == previousShoppingList);
		
	}

	@AfterClass
	public void afterClass() {
		
		Utilities.deleteCustomer(userId);
		driver.quit();

	}

}
