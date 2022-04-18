package TestNgProject.SangamAutomation;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pageObjects.HomePage;
import pageObjects.LandingPage;
import resources.copy.Base;
import resources.copy.ExcelDataDriving;

public class Sangam_LandingPageTest extends Base{
	public WebDriverWait wait;
	WebDriver driver;
	public static Logger log= LogManager.getLogger(Base.class.getName());
	//Properties prop= new Properties();
	
	@BeforeTest
	public void callUrl() throws IOException {
		driver=intializeDriver();	
		log.info("driver is iniitialized here!");
		driver.get(prop.getProperty("url"));
		log.info("Property value for url is captured");
		driver.manage().window().maximize();
		log.info("maximized the browser sucesfully");
		
	}
	
	@Test(priority = 1, dataProvider = "loginData")
	public void LandingPageValidation(String username,String passwd) throws IOException, InterruptedException {
		LandingPage lp= new LandingPage(driver);
		ExcelDataDriving excel= new ExcelDataDriving();
		String username_excel= excel.getStringData("Login Page Data", 1, 0);
		System.out.println(username_excel);
		lp.getEmail().sendKeys(username_excel);
		//lp.getEmail().sendKeys(username);
		log.info("Capturd the username -> "+username+" from the dataprovider/ Excel sheet");
		wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(lp.getPasswd()));
		
		String passwd_excel=excel.getStringData("Login Page Data", 1, 1);
		lp.getPasswd().sendKeys(passwd_excel);
		//lp.getPasswd().sendKeys(passwd);
		log.info("Capturd the username -> "+passwd+" from the dataprovider");
		lp.loginbutton().click();
		log.info("clicked on the login button");
	}
	
	@DataProvider
	public Object[][] loginData() {
		
		Object [][] obj= new Object [1][2];// In case you wish to use one more value for execution make first parenthesis hold +1
		obj[0][0]="yd276vijay@gmail.com";
		obj[0][1]="BaBaji@thulu";
//		obj[1][0]="yd276vijay@gmail.com";
//		obj[1][1]="BaBaji@thulu";
		
		return obj;
	}
	
	@Test (priority = 2)
	public void profileValidation() throws InterruptedException {	
		HomePage hp= new HomePage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(hp.profileLink()));
		hp.profileLink().click();
		log.info("clicked on the Profile link");
		wait.until(ExpectedConditions.visibilityOf(hp.coverPhotoBUtton()));
		hp.coverPhotoBUtton().click();
		log.info("clicked on the Edit Profile Photo button");
		Thread.sleep(2000); // added just to see if its correctly going in the UI.
		Assert.assertEquals("Vijay", "Yadav"); // use this to fail the test on purpose
		
	}
	
	@AfterTest
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
}
