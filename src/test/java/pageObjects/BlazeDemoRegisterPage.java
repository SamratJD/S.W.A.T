package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlazeDemoRegisterPage {

	public WebDriver driver;

	public BlazeDemoRegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public By Name_Textbox = By.id("name");
	public By Company_Texbox = By.id("company");
	public By Email_Textbox = By.id("email");
	public By Password_Textbox = By.id("password");
	public By ConfirmPassword_Textbox = By.id("password-confirm");
	public By Register_Button = By.xpath("//button[contains(text(),'Register')]");
	public By Login_Button = By.xpath("//*[text()='Login']");

}
