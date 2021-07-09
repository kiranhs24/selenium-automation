package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	WebDriver driver;
	public static Login login = new Login();
	public static TutorialScreen tutorialScreen = new TutorialScreen();
	public static MeTab meTab = new MeTab();
	public static PreferenceQuestions preferenceQuestion = new PreferenceQuestions();
	public static Home homePage = new Home();
	public static Planner plannerPage = new Planner();
	public static RecipeList recipeList = new RecipeList();
	public static RecipeSelection recipeSelectionPage = new RecipeSelection();
	public static ToolTip toolTip = new ToolTip();
	public static ReviewMyMealPlan reviewMealPlan = new ReviewMyMealPlan();
	public static ShoppingList shoppingList = new ShoppingList();
	public static RecipeDetail recipeDetailPage = new RecipeDetail();
	public static CollectionTab collectionTab = new CollectionTab();
	public static AddRecipeByCalendar addRecipeByCalendar = new AddRecipeByCalendar();
	public static ReviewMyMealPlan reviewMealPlanPage = new ReviewMyMealPlan();
	public static Filters filters = new Filters();
	public static Search search = new Search();
	public static VeryOwn veryOwn = new VeryOwn();
	public static ShoppingCart cart = new ShoppingCart();
	public static MoreItems moreItems = new MoreItems();

	public WebDriver initChromeDriver() throws IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--allow-insecure-localhost");

		driver = new ChromeDriver(options);
		driver.get("https://smart-mealplanner.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		return driver;

	}

	public WebDriver initFirefoxDriver() throws IOException, InterruptedException {

		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();

		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--allow-insecure-localhost");

		driver = new FirefoxDriver(options);
		driver.get("https://smart-mealplanner.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;

	}
	
	public String getScreenshot(WebDriver driver, String fileName ) throws IOException {
		
		TakesScreenshot shot = (TakesScreenshot) driver;
		String img = shot.getScreenshotAs(OutputType.BASE64);
		String destination = "data:image/png;base64," + img;
		return destination;
		
	}

}
