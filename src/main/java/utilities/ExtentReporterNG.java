package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports reportGenerator() {
		
		String reportPath = System.getProperty("user.dir") + "/target/Extent_Report/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Meal Planner Automation");
		reporter.config().setDocumentTitle("Smoke Tests");
		reporter.config().setOfflineMode(true);
		reporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Kiran");
		extent.setSystemInfo("Environment", "QA");
		
		return extent;
		
	}

}
