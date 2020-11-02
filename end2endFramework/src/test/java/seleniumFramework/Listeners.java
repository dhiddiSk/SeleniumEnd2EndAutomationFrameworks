package seleniumFramework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Configurations;

public class Listeners extends Configurations implements ITestListener {
	
	ExtentReports extentObject = Configurations.getReportObject();
	ExtentTest test;
	
	//This is helpful in case of parallel execution
	ThreadLocal<ExtentTest>  extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		String testname = result.getMethod().getMethodName();
		
		test = extentObject.createTest(testname);
		extentTest.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test got succeeded");
		

	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().fail(result.getThrowable());
		
		WebDriver driver = null;
		String methodName = result.getMethod().getMethodName();
		
		//System.out.println("The test failed is "+methodName);

		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception E) {
			// TODO Auto-generated catch block

			E.printStackTrace();
		}

		try {
			
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(methodName, driver), result.getMethod().getMethodName());	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.SKIP, "Test is skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extentObject.flush();
		

	}

}
