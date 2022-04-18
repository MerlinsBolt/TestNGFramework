package resources.copy;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsNG {
	static com.aventstack.extentreports.ExtentReports reports;
	public static com.aventstack.extentreports.ExtentReports getReportObject() {
		
		String path= System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Autoomation Results");
		reporter.config().setTheme(Theme.DARK);
		
		reports= new com.aventstack.extentreports.ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Vijay Yadav");
		
		return reports;
	}

}
