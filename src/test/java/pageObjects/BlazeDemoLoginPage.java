package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlazeDemoLoginPage {
	public WebDriver driver;

	public BlazeDemoLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public By EmailAddress_Textbox = By.id("email");
	public By Password_Textbox = By.id("password");
	public By Login_Button = By.xpath("//*[@type='submit']");
}
