package test_cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BaseClass;
import utilities.Utilities;


public class TestMealPlannerForNewUser extends BaseClass {

	public WebDriver driver;
	public HashMap<String, String> userData;
	public String created_email;
	public String userId;
	public WebDriverWait wait;

	@BeforeMethod
	public void beforeTest() throws IOException, InterruptedException {

		driver = initChromeDriver();
		wait = new WebDriverWait(driver, 20);
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
	public void newUserLandOnTutorialScreenAfterSuccessfulLogin() throws InterruptedException, IOException {

		Utilities.verifyTutorialScreen(driver, tutorialScreen);

	}

	@Test(priority = 2)
	public void newUserLandOnTutorialScreenFromMeTab() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, meTab.tutorials);
		Utilities.verifyTutorialScreen(driver, tutorialScreen);

	}

	@Test(priority = 3)
	public void newUserLandOnTutorialScreenWhenNoMealPlanIsCreated() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, meTab.logout);
		Thread.sleep(2000);
		Utilities.enterText(driver, login.email, created_email);
		Utilities.clickElement(driver, login.proceed);
		Utilities.verifyTutorialScreen(driver, tutorialScreen);

	}

	@Test(priority = 4)
	public void newUserLandOnPreferenceScreenAfterSkippingTutorialScreen() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.startQuestions));
		Utilities.clickElement(driver, preferenceQuestion.startQuestions);
		Thread.sleep(3000);
		Utilities.verifyPreferenceScreen(driver, preferenceQuestion);

	}

	@Test(priority = 5)
	public void newUserLandOnPreferenceScreenFromMeTab() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.addEditPreference));
		Utilities.clickElement(driver, meTab.addEditPreference);
		Thread.sleep(3000);
		Utilities.verifyPreferenceScreen(driver, preferenceQuestion);

	}

	@Test(priority = 6)
	public void newUserLandOnPreferenceScreenWhenPreferenceQuestionsAreNotAnswered() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.logout));
		Utilities.clickElement(driver, meTab.logout);
		Thread.sleep(2000);
		Utilities.enterText(driver, login.email, created_email);
		Thread.sleep(2000);
		Utilities.clickElement(driver, login.proceed);
		wait.until(ExpectedConditions.visibilityOfElementLocated(tutorialScreen.skip));
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.startQuestions));
		Utilities.clickElement(driver, preferenceQuestion.startQuestions);
		Thread.sleep(3000);
		Utilities.verifyPreferenceScreen(driver, preferenceQuestion);

	}

	@Test(priority = 7)
	public void newUserLandOnHomePageAfterSkippingOnBothTutorialsAndPreferenceScreen() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.verifyElement(driver, homePage.planYourMeal, "present");

	}

	@Test(priority = 8)
	public void newUserLandOnHomePageAfterClickingFinishOnLastPreferenceQuestion() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.startQuestions));
		Utilities.clickElement(driver, preferenceQuestion.startQuestions);
		Thread.sleep(3000);
		Utilities.verifyPreferenceScreen(driver, preferenceQuestion);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.done));
		Utilities.clickElement(driver, preferenceQuestion.done);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.planYourMeal));
		Utilities.verifyElement(driver, homePage.planYourMeal, "present");

	}

	@Test(priority = 9)
	public void newUserLandOnHomePageAfterClickingOnSkipOnAnyOneOfThePreferenceQuestion() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.startQuestions));
		Utilities.clickElement(driver, preferenceQuestion.startQuestions);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.next));
		Utilities.clickElement(driver, preferenceQuestion.next);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.skip));
		Utilities.clickElement(driver, preferenceQuestion.skip);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.planYourMeal));
		Utilities.verifyElement(driver, homePage.planYourMeal, "present");

	}

	@Test(priority = 10)
	public void newUserNavigateToHomePageAfterSkippingTutorials() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Utilities.verifyElement(driver, homePage.planYourMeal, "present");

	}

	@Test(priority = 11)
	public void newUserLandOnHomePageAfterClickingSkipOnTutorialPageAndIfPreferenceQuestionsAreAnswered() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.startQuestions));
		Utilities.clickElement(driver, preferenceQuestion.startQuestions);
		Thread.sleep(3000);
		Utilities.clickElement(driver, preferenceQuestion.next);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.skip));
		Utilities.clickElement(driver, preferenceQuestion.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.logout));
		Utilities.clickElement(driver, meTab.logout);
		Thread.sleep(2000);
		Utilities.enterText(driver, login.email, created_email);
		Thread.sleep(2000);
		Utilities.clickElement(driver, login.proceed);
		wait.until(ExpectedConditions.visibilityOfElementLocated(tutorialScreen.skip));
		Utilities.clickElement(driver, tutorialScreen.skip);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.planYourMeal));
		Utilities.verifyElement(driver, homePage.planYourMeal, "present");

	}

	@Test(priority = 12)
	public void newUserLandOnPlannerPageAfterClickingSkipToMealPlanOnTransitionPage() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(preferenceQuestion.skipToPlan));
		Utilities.clickElement(driver, preferenceQuestion.skipToPlan);
		Thread.sleep(3000);
		Utilities.verifyElement(driver, plannerPage.reviewMealPlanButton, "present");
		Utilities.verifyElement(driver, plannerPage.dinnerMealType, "present");
		Utilities.verifyElement(driver, plannerPage.breakfastMealType, "present");
		Utilities.verifyElement(driver, plannerPage.lunchMealType, "present");
		Utilities.verifyText(driver, plannerPage.plannerPage, "Plan Your", "contains");
		Utilities.verifyElement(driver, plannerPage.datePicker, "present");

	}

	@Test(priority = 13)
	public void newUserLandOnRecipeListPageAfterClickingOnRecipeFromBottomNavigationBar() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		Utilities.verifyElement(driver, recipeList.filterButton, "present");
		Utilities.verifyElements(driver, recipeList.recipe);

	}

	@Test(priority = 14)
	public void newUserCheckToolTipOnRecipeSelectionPage() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		Utilities.verifyElement(driver, toolTip.addToMealPlan, "present");

	}

	@Test(priority = 15)
	public void newUserCloseToolTipOnRecipeSelectionPage() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.dinnerMealType));
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.addToMealPlan));
		Utilities.verifyElement(driver, toolTip.addToMealPlan, "present");
		Utilities.clickElement(driver, toolTip.addToMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.recipe));
		assertEquals(Utilities.isElementPresent(driver, toolTip.addToMealPlan), false);

	}

	@Test(priority = 16)
	public void newUserCheckToolTipOnShoppingCartPage() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.addToMealPlan));
		Utilities.clickElement(driver, toolTip.addToMealPlan);
		Thread.sleep(3000);
		driver.navigate().back();
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Utilities.scrollToElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.dinnerTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.addItemsToCart));
		Utilities.clickElement(driver, shoppingList.addItemsToCart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.pickUpAdressPopUp));
		Utilities.clickElement(driver, shoppingList.selectAddress);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.swipeOnItem));
		Utilities.verifyElement(driver, toolTip.swipeOnItem, "present");

	}

	@Test(priority = 17)
	public void newUsercloseToolTipOnShoppingCartPage() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.addToMealPlan));
		Utilities.clickElement(driver, toolTip.addToMealPlan);
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Utilities.scrollToElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.dinnerTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.addItemsToCart));
		Utilities.clickElement(driver, shoppingList.addItemsToCart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.pickUpAdressPopUp));
		Utilities.clickElement(driver, shoppingList.selectAddress);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.swipeOnItem));
		Utilities.verifyElement(driver, toolTip.swipeOnItem, "present");
		Utilities.clickElement(driver, toolTip.swipeOnItem);
		Thread.sleep(3000);
		assertEquals(Utilities.isElementPresent(driver, toolTip.swipeOnItem), false);

	}

	@Test(priority = 18)
	public void newUserCheckReviewMyMealPlanPageWhenNoMealPlanIsCreated() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		Thread.sleep(3000);
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlan.dinnerTag), false);
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlan.lunchTag), false);
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlan.breakfastTag), false);

	}

	@Test(priority = 19)
	public void newUserLandOnRecipeDetailPageFromTodaysBreakfastOrLunchOrDinner() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.breakfastMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.addToMealPlan));
		Utilities.clickElement(driver, toolTip.addToMealPlan);
		Thread.sleep(3000);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.lunchMealType));
		Utilities.clickElement(driver, plannerPage.lunchMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.dinnerMealType));
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.todaysRecipe));
		Utilities.verifyElement(driver, homePage.todaysRecipe, "present");
		Utilities.clickElement(driver, homePage.viewRecipe);
		Utilities.verifyRecipeDetailPage(driver, recipeDetailPage, "");

	}

	@Test(priority = 20)
	public void newUserAddRecipeToFavoritesFromTodaysBreakfastOrLunchOrDinner() throws InterruptedException, IOException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Thread.sleep(3000);
		Utilities.clickElement(driver, plannerPage.breakfastMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.addToMealPlan));
		Utilities.clickElement(driver, toolTip.addToMealPlan);
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.lunchMealType));
		Utilities.clickElement(driver, plannerPage.lunchMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.dinnerMealType));
		Utilities.clickElement(driver, plannerPage.dinnerMealType);
		Utilities.clickElement(driver, recipeSelectionPage.plusButton);
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		Thread.sleep(3000);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.homePageBottomNavBar));
		Utilities.clickElement(driver, homePage.homePageBottomNavBar);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.todaysRecipe));
		Utilities.verifyElement(driver, homePage.todaysRecipe, "present");
		Utilities.clickElement(driver, homePage.viewRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.thumbsButton));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.thumbsButton);
		Thread.sleep(3000);
		driver.navigate().back();
		Utilities.verifyThumbsUpTag(driver, recipeDetailPage, collectionTab, recipeTitle);

	}

	@Test(priority = 21)
	public void newUserAddRecipeViaUrlFromCollectionTab() throws IOException, InterruptedException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.addRecipeButton));
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		Utilities.enterText(driver, collectionTab.addRecipeByURL, "https://www.allrecipes.com/recipe/14087/vietnamese-rice-noodle-salad/");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.proceed));
		Utilities.clickElement(driver, collectionTab.proceed);
		wait.until(ExpectedConditions.elementToBeClickable(recipeDetailPage.saveRecipe));
		Utilities.clickElement(driver, recipeDetailPage.saveRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionUrlSavedTag));
		Utilities.verifyElement(driver, collectionTab.collectionUrlSavedTag, "present");
		Utilities.verifyElement(driver, collectionTab.collectionRecipeLocator, "present");

	}
	
	@Test(priority = 22)
	public void  newUserAddRecipeViaUrlFromMeTab() throws IOException, InterruptedException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.addURL));
		Utilities.clickElement(driver, meTab.addURL);
		Utilities.enterText(driver, collectionTab.addRecipeByURL, "https://www.allrecipes.com/recipe/14087/vietnamese-rice-noodle-salad/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.proceed));
		Utilities.clickElement(driver, collectionTab.proceed);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(recipeDetailPage.saveRecipe));
		Utilities.clickElement(driver, recipeDetailPage.saveRecipe);
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionUrlSavedTag));
		Utilities.verifyElement(driver, collectionTab.collectionUrlSavedTag, "present");
		Utilities.verifyElement(driver, collectionTab.collectionRecipeLocator, "present");

	}
	
	@Test(priority = 23)
	public void newUserCancelAddRecipeViaUrlFromCollectionTab() throws IOException, InterruptedException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		Utilities.enterText(driver, collectionTab.addRecipeByURL, "https://www.allrecipes.com/recipe/14087/vietnamese-rice-noodle-salad/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.proceed));
		Utilities.clickElement(driver, collectionTab.proceed);
		wait.until(ExpectedConditions.elementToBeClickable(recipeDetailPage.cancelRecipe));
		Utilities.clickElement(driver, recipeDetailPage.cancelRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.addRecipeButton));
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionUrlSavedTag), false);
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionRecipeLocator), false);

	}
	
	@Test(priority = 24)
	public void newUserCancelAddRecipeViaUrlFromMeTab() throws IOException, InterruptedException {

		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, meTab.addURL);
		Utilities.enterText(driver, collectionTab.addRecipeByURL, "https://www.allrecipes.com/recipe/14087/vietnamese-rice-noodle-salad/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.proceed));
		Utilities.clickElement(driver, collectionTab.proceed);
		wait.until(ExpectedConditions.elementToBeClickable(recipeDetailPage.cancelRecipe));
		Utilities.clickElement(driver, recipeDetailPage.cancelRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionUrlSavedTag), false);
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionRecipeLocator), false);

	}
	
	@Test(priority = 25)
	public void newUserLandOnRecipeDetailPageFromCollectionTab() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.addRecipeByURL));
		Utilities.enterText(driver, collectionTab.addRecipeByURL, "https://www.allrecipes.com/recipe/14087/vietnamese-rice-noodle-salad/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.proceed));
		Utilities.clickElement(driver, collectionTab.proceed);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(recipeDetailPage.saveRecipe));
		Utilities.clickElement(driver, recipeDetailPage.saveRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.collectionRecipeLocator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.verifyRecipeDetailPage(driver, recipeDetailPage, "CR");
		
	}
	
	@Test(priority = 26)
	public void newUserAddRecipeToFavoritesFromCollectionTab() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.addRecipeByURL));
		Utilities.enterText(driver, collectionTab.addRecipeByURL, "https://www.allrecipes.com/recipe/14087/vietnamese-rice-noodle-salad/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.proceed));
		Utilities.clickElement(driver, collectionTab.proceed);
		wait.until(ExpectedConditions.elementToBeClickable(recipeDetailPage.saveRecipe));
		Utilities.clickElement(driver, recipeDetailPage.saveRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.collectionRecipeLocator);
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.thumbsButton));
		Utilities.clickElement(driver, recipeDetailPage.thumbsButton);
		driver.navigate().back();
		Thread.sleep(3000);
		Utilities.verifyThumbsUpTag(driver, recipeDetailPage, collectionTab, recipeTitle);
		
	}
	
	@Test(priority = 27)
	public void newUserAddRecipeToMealPlanFromCollectionTabURLSavedRecipe() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.addRecipeByURL));
		Utilities.enterText(driver, collectionTab.addRecipeByURL, "https://www.allrecipes.com/recipe/14087/vietnamese-rice-noodle-salad/");
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.proceed));
		Utilities.clickElement(driver, collectionTab.proceed);
		wait.until(ExpectedConditions.elementToBeClickable(recipeDetailPage.saveRecipe));
		Utilities.clickElement(driver, recipeDetailPage.saveRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.collectionRecipeLocator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "LUNCH");
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		Thread.sleep(3000);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		Utilities.verifyElement(driver, addedRecipe, "present");
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.lunchTag), true);
		
	}
	
	@Test(priority = 28)
	public void newUserAddCustomRecipeFromCollectionTab() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		Utilities.clickElement(driver, collectionTab.veryOwnRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.title));
		Utilities.enterText(driver, veryOwn.title, "Test 1");
		Utilities.enterText(driver, veryOwn.description, "This is a custom added recipe");
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisine));
		Utilities.clickElement(driver, veryOwn.cuisine);
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisineOption));
		Utilities.clickElement(driver, veryOwn.cuisineOption);
		Utilities.enterText(driver, veryOwn.ingredient, "Eggs");
		Utilities.enterText(driver, veryOwn.quantity, "35");
		Utilities.clickElement(driver, veryOwn.measurementDropDown);
		Utilities.clickElement(driver, veryOwn.measurementValue);
		Utilities.scrollToElement(driver, veryOwn.cookTimeDropDown);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.prepTimeDropDown));
		Utilities.clickElement(driver, veryOwn.prepTimeDropDown);
		Utilities.clickElement(driver, veryOwn.prepTimeValue);
		Utilities.clickElement(driver, veryOwn.cookTimeDropDown);
		Utilities.clickElement(driver, veryOwn.cookTimeValue);
		Utilities.clickElement(driver, veryOwn.save);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.viewOnCollection));
		Utilities.clickElement(driver, collectionTab.viewOnCollection);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionOwnRecipeTag));
		Utilities.verifyElement(driver, collectionTab.collectionOwnRecipeTag, "present");
		Utilities.verifyElement(driver, collectionTab.collectionRecipeLocator, "present");
		
	}
	
	@Test(priority = 29)
	public void newUserAddCustomRecipeFromMeTab() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, collectionTab.veryOwnRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.title));
		Utilities.enterText(driver, veryOwn.title, "Test 1");
		Utilities.enterText(driver, veryOwn.description, "This is a custom added recipe");
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisine));
		Utilities.clickElement(driver, veryOwn.cuisine);
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisineOption));
		Utilities.clickElement(driver, veryOwn.cuisineOption);
		Utilities.enterText(driver, veryOwn.ingredient, "Eggs");
		Utilities.enterText(driver, veryOwn.quantity, "35");
		Utilities.clickElement(driver, veryOwn.measurementDropDown);
		Utilities.clickElement(driver, veryOwn.measurementValue);
		Utilities.scrollToElement(driver, veryOwn.cookTimeDropDown);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.prepTimeDropDown));
		Utilities.clickElement(driver, veryOwn.prepTimeDropDown);
		Utilities.clickElement(driver, veryOwn.prepTimeValue);
		Utilities.clickElement(driver, veryOwn.cookTimeDropDown);
		Utilities.clickElement(driver, veryOwn.cookTimeValue);
		Utilities.clickElement(driver, veryOwn.save);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.viewOnCollection));
		Utilities.clickElement(driver, collectionTab.viewOnCollection);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionOwnRecipeTag));
		Utilities.verifyElement(driver, collectionTab.collectionOwnRecipeTag, "present");
		Utilities.verifyElement(driver, collectionTab.collectionRecipeLocator, "present");
		
	}
	
	@Test(priority = 30)
	public void newUserCancelAddCustomRecipeFromCollectionTab() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		Utilities.clickElement(driver, collectionTab.veryOwnRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.title));
		Utilities.enterText(driver, veryOwn.title, "Test 1");
		Utilities.enterText(driver, veryOwn.description, "This is a custom added recipe");
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisine));
		Utilities.clickElement(driver, veryOwn.cuisine);
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisineOption));
		Utilities.clickElement(driver, veryOwn.cuisineOption);
		Utilities.enterText(driver, veryOwn.ingredient, "Eggs");
		Utilities.enterText(driver, veryOwn.quantity, "35");
		Utilities.clickElement(driver, veryOwn.measurementDropDown);
		Utilities.clickElement(driver, veryOwn.measurementValue);
		Utilities.scrollToElement(driver, veryOwn.cookTimeDropDown);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.prepTimeDropDown));
		Utilities.clickElement(driver, veryOwn.prepTimeDropDown);
		Utilities.clickElement(driver, veryOwn.prepTimeValue);
		Utilities.clickElement(driver, veryOwn.cookTimeDropDown);
		Utilities.clickElement(driver, veryOwn.cookTimeValue);
		Utilities.clickElement(driver, veryOwn.cancel);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.accpetPopUp));
		Utilities.clickElement(driver, veryOwn.accpetPopUp);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.addRecipeButton));
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionOwnRecipeTag), false);
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionRecipeLocator), false);
		
	}
	
	@Test(priority = 31)
	public void newUserCancelAddCustomRecipeFromMeTab() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(meTab.meTabBottomNav));
		Utilities.clickElement(driver, meTab.meTabBottomNav);
		Utilities.clickElement(driver, collectionTab.veryOwnRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.title));
		Utilities.enterText(driver, veryOwn.title, "Test 1");
		Utilities.enterText(driver, veryOwn.description, "This is a custom added recipe");
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisine));
		Utilities.clickElement(driver, veryOwn.cuisine);
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisineOption));
		Utilities.clickElement(driver, veryOwn.cuisineOption);
		Utilities.enterText(driver, veryOwn.ingredient, "Eggs");
		Utilities.enterText(driver, veryOwn.quantity, "35");
		Utilities.clickElement(driver, veryOwn.measurementDropDown);
		Utilities.clickElement(driver, veryOwn.measurementValue);
		Utilities.scrollToElement(driver, veryOwn.cookTimeDropDown);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.prepTimeDropDown));
		Utilities.clickElement(driver, veryOwn.prepTimeDropDown);
		Utilities.clickElement(driver, veryOwn.prepTimeValue);
		Utilities.clickElement(driver, veryOwn.cookTimeDropDown);
		Utilities.clickElement(driver, veryOwn.cookTimeValue);
		Utilities.clickElement(driver, veryOwn.cancel);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.accpetPopUp));
		Utilities.clickElement(driver, veryOwn.accpetPopUp);
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.addRecipeButton));
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionOwnRecipeTag), false);
		assertEquals(Utilities.isElementPresent(driver, collectionTab.collectionRecipeLocator), false);
		
	}
	
	@Test(priority = 32)
	public void newUserAddCustomRecipeToMealPlan() throws IOException, InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(collectionTab.collectionBottomNavBar));
		Utilities.clickElement(driver, collectionTab.collectionBottomNavBar);
		Utilities.clickElement(driver, collectionTab.addRecipeButton);
		Utilities.clickElement(driver, collectionTab.veryOwnRecipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.title));
		Utilities.enterText(driver, veryOwn.title, "Test 1");
		Utilities.enterText(driver, veryOwn.description, "This is a custom added recipe");
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisine));
		Utilities.clickElement(driver, veryOwn.cuisine);
		wait.until(ExpectedConditions.elementToBeClickable(veryOwn.cuisineOption));
		Utilities.clickElement(driver, veryOwn.cuisineOption);
		Utilities.enterText(driver, veryOwn.ingredient, "Eggs");
		Utilities.enterText(driver, veryOwn.quantity, "35");
		Utilities.clickElement(driver, veryOwn.measurementDropDown);
		Utilities.clickElement(driver, veryOwn.measurementValue);
		Utilities.scrollToElement(driver, veryOwn.cookTimeDropDown);
		wait.until(ExpectedConditions.visibilityOfElementLocated(veryOwn.prepTimeDropDown));
		Utilities.clickElement(driver, veryOwn.prepTimeDropDown);
		Utilities.clickElement(driver, veryOwn.prepTimeValue);
		Utilities.clickElement(driver, veryOwn.cookTimeDropDown);
		Utilities.clickElement(driver, veryOwn.cookTimeValue);
		Utilities.clickElement(driver, veryOwn.save);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String recipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.doneButton));
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "LUNCH");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		Thread.sleep(3000);
		By addedRecipe = By.xpath("//span[contains(text(), " + "\"" + recipeTitle + "\"" + ")]");
		Utilities.verifyElement(driver, addedRecipe, "present");
		assertEquals(Utilities.isElementPresent(driver, reviewMealPlanPage.lunchTag), true);
		
	}
	
	@Test(priority = 33)
	public void newUserCheckDetailsOnConvertToShoppingList() throws InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		Utilities.clickElement(driver, recipeList.recipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.scrollToElement(driver, recipeDetailPage.ingredientsList);
		List<WebElement> ingredientsList = driver.findElements(recipeDetailPage.ingredientsList);
		String numberOfItems = Integer.toString(ingredientsList.size());
		By totalCount = By.xpath("//span[contains(text(), " + numberOfItems + ")]");
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "BREAKFAST");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.breakfastTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.collapseIngredients));
		Utilities.clickElement(driver, shoppingList.collapseIngredients);
		wait.until(ExpectedConditions.visibilityOfElementLocated(totalCount));
		assertTrue(Utilities.getText(driver, totalCount).contains(numberOfItems));
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.expandIngredients));
		Utilities.clickElement(driver, shoppingList.expandIngredients);
		List<WebElement> checkedList = driver.findElements(shoppingList.checkedIngredients);
		for (WebElement checkBox : checkedList) {
			
			assertEquals(checkBox.isSelected(), true);
			
		}
		List<WebElement> unCheckedList = driver.findElements(shoppingList.uncheckedIngredients);
		for (WebElement checkBox : unCheckedList) {
			
			assertEquals(checkBox.isSelected(), false);
			
		}
		
	}
	
	@Test(priority = 34)
	public void newUserCheckShoppingCartPageWhenIngredientsAreAdded() throws InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		Utilities.clickElement(driver, recipeList.recipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.scrollToElement(driver, recipeDetailPage.ingredientsList);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "BREAKFAST");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.breakfastTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.addItemsToCart));
		Utilities.clickElement(driver, shoppingList.addItemsToCart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.pickUpAdressPopUp));
		Utilities.clickElement(driver, shoppingList.selectAddress);
		wait.until(ExpectedConditions.elementToBeClickable(cart.checkout));
		
		Utilities.verifyElement(driver, cart.checkout, "ID");
		Utilities.verifyElement(driver, cart.decreaseProductCount, "ID");
		Utilities.verifyElement(driver, cart.increaseProductCount, "ID");
		assertTrue(driver.findElements(cart.itemPrice).size() > 0);
		Utilities.verifyElement(driver, cart.selectQuantity, "ID");
		assertTrue(driver.findElement(cart.TotalPrice).getText().contains("$"));
		
	}
	
	@Test(priority = 36)
	public void newUserCheckOrderPlacedTag() throws InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		Utilities.clickElement(driver, recipeList.recipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.scrollToElement(driver, recipeDetailPage.ingredientsList);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "BREAKFAST");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.breakfastTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.addItemsToCart));
		Utilities.clickElement(driver, shoppingList.addItemsToCart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.pickUpAdressPopUp));
		Utilities.clickElement(driver, shoppingList.selectAddress);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(toolTip.swipeOnItem));
		Utilities.clickElement(driver, toolTip.swipeOnItem);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(cart.checkout));
		Utilities.clickElement(driver, cart.checkout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.tomorrowsSlot));
		Utilities.clickElement(driver, cart.tomorrowsSlot);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.pickUpTime));
		Utilities.clickElement(driver, cart.pickUpTime);
		wait.until(ExpectedConditions.elementToBeClickable(cart.schedulePickup));
		Utilities.clickElement(driver, cart.schedulePickup);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.confirmOrder));
		Utilities.clickElement(driver, cart.confirmOrder);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.goToHome));
		Utilities.scrollToElement(driver, cart.goToHome);
		Utilities.clickElement(driver, cart.goToHome);
		Thread.sleep(3000);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.submitRating));
			Utilities.clickElement(driver, homePage.submitRating);
		} catch (Exception e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.verifyElement(driver, cart.orderPlacedTag, "XPATH");
		
	}
	
	@Test(priority = 35)
	public void newUserCheckOrderPendingTag() throws InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		Utilities.clickElement(driver, recipeList.recipe);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.scrollToElement(driver, recipeDetailPage.ingredientsList);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "BREAKFAST");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.breakfastTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.elementToBeClickable(shoppingList.addItemsToCart));
		Utilities.clickElement(driver, shoppingList.addItemsToCart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.pickUpAdressPopUp));
		Utilities.clickElement(driver, shoppingList.selectAddress);
		wait.until(ExpectedConditions.elementToBeClickable(toolTip.swipeOnItem));
		Utilities.clickElement(driver, toolTip.swipeOnItem);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(cart.checkout));
		Utilities.clickElement(driver, cart.checkout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.tomorrowsSlot));
		Utilities.clickElement(driver, cart.tomorrowsSlot);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.pickUpTime));
		Utilities.clickElement(driver, cart.pickUpTime);
		wait.until(ExpectedConditions.elementToBeClickable(cart.schedulePickup));
		Utilities.clickElement(driver, cart.schedulePickup);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.confirmOrder));
		Utilities.clickElement(driver, cart.confirmOrder);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.goToHome));
		Utilities.scrollToElement(driver, cart.goToHome);
		Utilities.clickElement(driver, cart.goToHome);
		Thread.sleep(3000);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.submitRating));
			Utilities.clickElement(driver, homePage.submitRating);
		} catch (Exception e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Utilities.clickElement(driver, plannerPage.breakfastMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.addToMealPlan));
		Utilities.clickElement(driver, toolTip.addToMealPlan);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.plusButton));
		List<WebElement> recipes = driver.findElements(recipeSelectionPage.plusButton);
		recipes.get(6).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeSelectionPage.saveAndProceed));
		Utilities.clickElement(driver, recipeSelectionPage.saveAndProceed);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.breakfastMealType));
		Utilities.verifyElement(driver, cart.orderPendingTag, "XPATH");
		
	}
	
	@Test(priority = 37)
	public void newUserConvertSelectedDatesToShoppingList() throws InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		Utilities.clickElement(driver, recipeList.recipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		String firstRecipeTitle = Utilities.getText(driver, recipeDetailPage.recipeTitle);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		try {

			List<WebElement> todaysDate = driver.findElements(By.xpath(addRecipeByCalendar.todaysDate));
			todaysDate.get(0).click();

		} catch (Exception e) {

			List<WebElement> todaysDate = driver.findElements(By.xpath(addRecipeByCalendar.todaysDate));
			todaysDate.get(1).click();

		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.selectMealDropDown));
		Utilities.scrollToElement(driver, addRecipeByCalendar.selectMealDropDown);
		Utilities.clickElement(driver, addRecipeByCalendar.selectMealDropDown);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.dinnerMealType));
		Utilities.clickElement(driver, addRecipeByCalendar.dinnerMealType);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addRecipeByCalendar.doneButton));
		Utilities.clickElement(driver, addRecipeByCalendar.doneButton);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		List<WebElement> recipes = driver.findElements(recipeList.recipe);
		recipes.get(3).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		try {

			List<WebElement> todaysDate = driver.findElements(By.xpath(addRecipeByCalendar.tomorrowsDate));
			todaysDate.get(0).click();

		} catch (Exception e) {

			List<WebElement> todaysDate = driver.findElements(By.xpath(addRecipeByCalendar.tomorrowsDate));
			todaysDate.get(1).click();

		}
		wait.until(ExpectedConditions.elementToBeClickable(addRecipeByCalendar.selectMealDropDown));
		Utilities.scrollToElement(driver, addRecipeByCalendar.selectMealDropDown);
		wait.until(ExpectedConditions.elementToBeClickable(addRecipeByCalendar.selectMealDropDown));
		Utilities.clickElement(driver, addRecipeByCalendar.selectMealDropDown);
		wait.until(ExpectedConditions.elementToBeClickable(addRecipeByCalendar.dinnerMealType));
		Utilities.clickElement(driver, addRecipeByCalendar.dinnerMealType);
		wait.until(ExpectedConditions.elementToBeClickable(addRecipeByCalendar.doneButton));
		Utilities.clickElement(driver, addRecipeByCalendar.doneButton);
		wait.until(ExpectedConditions.elementToBeClickable(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.elementToBeClickable(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.dinnerTag));
		List<WebElement> checkboxes = driver.findElements(reviewMealPlan.checkBox);
		try {
			checkboxes.get(1).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.elementToBeClickable(reviewMealPlan.convertToShoppingList));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.collapseIngredients));
		String addedRecipeTitle = "//span[contains(text(), " + "\"" + firstRecipeTitle + "\""+ ")]";
		By recipeAdded = By.xpath(addedRecipeTitle);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeAdded));
		assertEquals(Utilities.isElementPresent(driver, recipeAdded), true);
		
	}
	
	@Test(priority = 38)
	public void newUserAddOtherItemsToShoppingCart() throws InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		Utilities.clickElement(driver, recipeList.recipe);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.scrollToElement(driver, recipeDetailPage.ingredientsList);
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "LUNCH");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.lunchTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.addItemsToCart));
		Utilities.clickElement(driver, shoppingList.addItemsToCart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.pickUpAdressPopUp));
		Utilities.clickElement(driver, shoppingList.selectAddress);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.swipeOnItem));
		Utilities.clickElement(driver, toolTip.swipeOnItem);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.checkout));
		Utilities.clickElement(driver, cart.addMoreItemsToCart);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(moreItems.increaseProductCount));
		Utilities.clickElement(driver, moreItems.increaseProductCount);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.addMoreItemsToCart));
		Utilities.scrollToElement(driver, moreItems.productTitle);
		wait.until(ExpectedConditions.visibilityOfElementLocated(moreItems.productTitle));
		assertEquals(Utilities.isElementPresent(driver, moreItems.productTitle), true);
		
	}
	
	@Test(priority = 39)
	public void newUserAddOtherItemsToShoppingCartFromSearchProductPage() throws InterruptedException {
		
		Utilities.clickElement(driver, tutorialScreen.skip);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipeBottomNavBar));
		Utilities.clickElement(driver, recipeList.recipeBottomNavBar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeList.recipe));
		Utilities.clickElement(driver, recipeList.recipe);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(recipeDetailPage.recipeTitle));
		Utilities.clickElement(driver, recipeDetailPage.addToMealPlanButton);
		Thread.sleep(3000);
		Utilities.addRecipeByCalendar(driver, addRecipeByCalendar, "LUNCH");
		wait.until(ExpectedConditions.visibilityOfElementLocated(plannerPage.plannerBottomNavBar));
		Utilities.clickElement(driver, plannerPage.plannerBottomNavBar);
		wait.until(ExpectedConditions.elementToBeClickable(plannerPage.reviewMealPlanButton));
		Utilities.clickElement(driver, plannerPage.reviewMealPlanButton);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.reviewMyMealPlan));
		Utilities.clickElement(driver, toolTip.reviewMyMealPlan);
		wait.until(ExpectedConditions.visibilityOfElementLocated(reviewMealPlan.lunchTag));
		Utilities.clickElement(driver, reviewMealPlan.convertToShoppingList);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.addItemsToCart));
		Utilities.clickElement(driver, shoppingList.addItemsToCart);
		wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingList.pickUpAdressPopUp));
		Utilities.clickElement(driver, shoppingList.selectAddress);
		wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip.swipeOnItem));
		Utilities.clickElement(driver, toolTip.swipeOnItem);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.checkout));
		Utilities.clickElement(driver, cart.addMoreItemsToCart);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(moreItems.increaseProductCount));
		Utilities.enterText(driver, moreItems.searchBox, "carrot");
		driver.findElement(moreItems.searchBox).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(moreItems.productTitle));
		Utilities.clickElement(driver, moreItems.increaseProductCount);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart.addMoreItemsToCart));
		Utilities.scrollToElement(driver, moreItems.productTitle);
		wait.until(ExpectedConditions.visibilityOfElementLocated(moreItems.productTitle));
		assertEquals(Utilities.isElementPresent(driver, moreItems.productTitle), true);
		
	}
	
	@AfterMethod
	public void afterMethod() throws IOException {
		
		Utilities.deleteCustomer(userId);
		driver.quit();

	}

}
