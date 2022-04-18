package TestNgProject.SangamAutomation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.copy.Base;
import resources.copy.ExtentReportsNG;



public class Listeners extends Base implements ITestListener{
	ExtentReports extent=ExtentReportsNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	test= extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);// used to make your reporting thread safe
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("On test success is being called");
		extentTest.get().log(Status.PASS, "Test Passed");
//		WebDriver driver=null;
//		try {
//		driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//	} catch (Exception e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	
//		// getting the method name which is under execution and then passing the same to our screen shot method so a snap with same name is created.
//		String TestMethodName=result.getMethod().getMethodName();
//	try {
//		getSnapShot(TestMethodName, driver);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		extentTest.get().fail(result.getThrowable());
		// First try catch is to get the driver instance of Testcase for which the test failed else it wont be able to take Snap for correct test case
		WebDriver driver = null;
		try {
		driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
		// getting the method name which is under execution and then passing the same to our screen shot method so a snap with same name is created.
		String TestMethodName=result.getMethod().getMethodName();
	try {
		extentTest.get().addScreenCaptureFromPath(getSnapShot(TestMethodName, driver), TestMethodName);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}

}
