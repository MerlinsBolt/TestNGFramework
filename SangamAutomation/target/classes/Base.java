package resources.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	public WebDriver driver;
	public Properties prop;
	
	@SuppressWarnings("deprecation")
	public WebDriver intializeDriver() throws IOException {
		
		prop= new Properties();
		FileInputStream fs= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\copy\\data.properties");
	prop.load(fs);

	String browsername=prop.getProperty("browser");
	if(browsername.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\copy\\chromedriver.exe");
		ChromeOptions ch= new ChromeOptions();
		ch.addArguments("disable-notifications");// disables the notfication prompts
		ch.addArguments("disable-infobars");// disables the hightlight bar "Chrome is controlled by automated test"
		ch.addArguments("disable-geolocation");// disables websietes from getting our real location
		driver= new ChromeDriver(ch); 
		
	}
	else if(browsername.equals("Firefox")) {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\copy\\geckodriver.exe");
		driver= new FirefoxDriver();
	}
	else {
		System.out.println("there is some error with browser or system properties set");
	}
	
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	return driver;
	}

	public String getSnapShot(String TestcaseName, WebDriver driver) throws IOException {
		
		File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destiationpath= System.getProperty("user.dir")+"/reports/"+TestcaseName+".png";
		System.out.println("1");
		FileUtils.copyFile(source, new File(destiationpath));
		return destiationpath;
	}
	
}
