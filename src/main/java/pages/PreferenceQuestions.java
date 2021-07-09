package pages;

import org.openqa.selenium.By;

public class PreferenceQuestions {
	
	public By startQuestions = By.id("start-preference-questions");
	
	public By skipToPlan = By.id("skip-preference-go-to-mealplan");

	public By skip = By.id("skip-preference-questions");
	
	public By next = By.id("go-to-next-preference");
	
	public By previous = By.id("go-to-previous-preference");
	
	public By done = By.id("finish-preference-questions");
	
	public By increaseFamilyMembers = By.id("increase-family-member-count");
	
	public By decreaseFamilyMembers = By.id("decrease-family-member-count");
	
	// Food Allergies
	
	String allergyGluten = "allergy-option-gluten";
	
	String allergyPeanuts = "allergy-option-peanuts";
	
	String allergyTreenuts =  "allergy-option-treenuts";
	
	String allergyWheat = "allergy-option-wheat";
	
	String allergyFish = "allergy-option-fish";
	
	String allergySoy = "allergy-option-soy";
	
	String allergyShellfish = "allergy-option-shellfish";
	
	String allergyEggs = "allergy-option-eggs";
	
	String allergyLactose = "allergy-option-lactose";
	
	// Diets 
	
	String dietNonVeg = "diet-option-non-veg";
	
	String dietVegetairan = "diet-option-vegetarian";
	
	String dietVegan = "diet-option-vegan";
	
	String dietKetogenic = "diet-option-ketogenic";
	
	String dietPaleo = "diet-option-paleo";
	
	String dietLowFodmap = "diet-option-low-fodmap";
	
	String dietMediterranean = "diet-option-mediterranean";
	
	String dietRawDiet = "diet-option-raw-diet";
	
	String dietDiabetic = "diet-option-diabetic";
	
	// Favorite Cuisine
	
	String cuisineAmerican = "cuisine-option-american";
	
	String cuisineMexican = "cuisine-option-mexican";
	
	String cuisineEnglish = "cuisine-option-english";
	
	String cuisineItalian = "cuisine-option-italian";
	
	String cuisineAsian = "cuisine-option-asian";
	
	String cuisineIndian = "cuisine-option-indian";
	
	String cuisineChinese = "cuisine-option-chinese";
	
	String cuisineJapanese = "cuisine-option-japanese";
	
	String cuisineFrench = "cuisine-option-french";
	
	String cuisineTurkish = "cuisine-option-turkish";
	
	String cuisineVietnamese = "cuisine-option-vietnamese";
	
	String cuisineLebanese = "cuisine-option-lebanese";
	
	String cuisineGreek = "cuisine-option-greek";
	
	String cuisineSpanish = "cuisine-option-spanish";
	
	String cuisineMoroccan = "cuisine-option-moroccan";
	
	String cuisineHawaiian = "cuisine-option-hawaiian";
	
	String cuisinePortuguese = "cuisine-option-portuguese";
	
	String cuisineSwedish = "cuisine-option-swedish";
	
	String cuisineCuban = "cuisine-option-cuban";
	
	String cuisineIrish = "cuisine-option-irish";
	
	String cuisineThai = "cuisine-option-thai";
	
	// Difficulty Level 
	
	String difficultyBeginner = "difficulty-level-beginner";
	
	String difficultyIntermediate = "difficulty-level-intermediate";
	
	String difficultyAdvanced = "difficulty-level-advanced";
	
	public By preferenceFirstQuestion = By.xpath("//div[text()='Family Members']");
	
	public By preferenceSecondQuestion = By.xpath("//p[contains(text(), 'food allergies')]");
	
	public By preferenceThirdQuestion = By.xpath("//p[contains(text(), 'diet')]");
	
	public By preferenceFourthQuestion = By.xpath("//p[contains(text(), 'cuisine')]");
	
	public By preferenceFifthQuestion = By.xpath("//p[contains(text(), 'difficulty')]");
	
	public By selectPreferenceOptions(String option) {
		
		By preferenceOption = By.id(option);
		return preferenceOption;
		
	}

}
