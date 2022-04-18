package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By profileNameLink= By.xpath("//span[contains(text(),'Vijay Yadav')]");
	public WebElement profileLink() {
		return driver.findElement(profileNameLink);
	}
	
	private By editCoverPhotoButton= By.xpath("//span[contains(text(),'Edit cover photo')]");
	public WebElement coverPhotoBUtton() {
		return driver.findElement(editCoverPhotoButton);
	}
}
