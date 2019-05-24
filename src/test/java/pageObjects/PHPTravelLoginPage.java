package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PHPTravelLoginPage {

	public WebDriver driver;

	public PHPTravelLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public By Email_Textbox = By.xpath("//*[@name='email']");
	public By Password_Textbox = By.xpath("//*[@name='password']");
	public By Login_Button = By.xpath("//*[@type='submit']");

}
