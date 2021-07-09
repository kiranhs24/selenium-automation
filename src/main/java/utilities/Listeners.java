package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import pages.BaseClass;

public class Listeners extends BaseClass implements ITestListener {
	
	ExtentReports extent = ExtentReporterNG.reportGenerator();
	ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	/**
	   * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
	   * filled with the references to class, method, start millis and status.
	   *
	   * @param result the partially filled <code>ITestResult</code>
	   * @see ITestResult#STARTED
	   */
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
		if(result.getMethod().getMethodName().contains("existingUser")) {
			
			test.assignCategory("Firefox");
			
		}
		
		else if(result.getMethod().getMethodName().contains("newUser")) {
			
			test.assignCategory("Chrome");
			
		}
		
	}
	
	/**
	 * Invoked each time a test succeeds.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test Passed");
		
	}
	
	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		String email_id;
		String user_id;
		Object testObject = result.getInstance();
		Class className = result.getTestClass().getRealClass();
		try {
			email_id = (String) className.getDeclaredField("created_email").get(testObject);
			user_id = (String) className.getDeclaredField("userId").get(testObject);
			extentTest.get().log(Status.INFO, "Email ID - " +  email_id);
			extentTest.get().log(Status.INFO, "User ID - " +  user_id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		extentTest.get().log(Status.FAIL, result.getThrowable());
		
		try {
		
			driver = (WebDriver) className.getDeclaredField("driver").get(testObject);
			extentTest.get().addScreenCaptureFromBase64String(getScreenshot(driver, result.getMethod().getMethodName()));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
 		
	}
	
	/**
	 * Invoked each time a test is skipped.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		
		test.log(Status.SKIP, "Test Skipped");
		
	}
	
	/**
	 * Invoked each time a method fails but has been annotated with
	 * successPercentage and this failure still keeps it within the success
	 * percentage requested.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 *
	 * @param context The test context
	 */
	@Override
	public void onStart(ITestContext context) {}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 *
	 * @param context The test context
	 */
	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
		
	}

}
