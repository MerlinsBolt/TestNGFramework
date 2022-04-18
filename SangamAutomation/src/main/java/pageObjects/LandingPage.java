package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	public WebDriver driver;
	//giving the driver a life to ensure below things work
	public LandingPage(WebDriver driver){
		this.driver=driver;
	}
	
	
	
	private By emailadressTextbox=By.id("email");
	public WebElement getEmail() {
		return driver.findElement(emailadressTextbox);
	}

	private By passwdTextbox= By.id("pass");
	public WebElement getPasswd() {
		return driver.findElement(passwdTextbox);
	}
	
	private By loginButton= By.xpath("//button[@name='login']");
	public WebElement loginbutton() {
		return driver.findElement(loginButton);
	}
			
}
