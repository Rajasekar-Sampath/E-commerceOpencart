package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Logout {
	
	public WebDriver driver;
	
	public Login_Logout(WebDriver rdriver) {
		
		driver = rdriver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//span[@class='caret']")
	@CacheLookup
	WebElement click;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	@CacheLookup
	WebElement loginclk;
	
	@FindBy(name = "email")
	@CacheLookup
	WebElement txtemail;
	
	@FindBy(name = "password")
	@CacheLookup
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	@CacheLookup
	WebElement clklogin;
	
	@FindBy(xpath = "//span[@class='caret']")
	@CacheLookup
	WebElement Click;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	@CacheLookup
	WebElement logoutclk;
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	@CacheLookup
	WebElement continueclk;
	
	
	public void Click() {
		click.click();
	}
	
	public void Loginclk () {
		
		loginclk.click();
	}
	
	public void setemail (String Email) {
		txtemail.clear();
		txtemail.sendKeys(Email);
	}
	
	public void setpassword (String pwd) {
		txtpassword.clear();
		txtpassword.sendKeys(pwd);
	}
	
	public void ClkLogin() {
		
		clklogin.click();
	}
	
	public void loginsuccess() {
		
		if(driver.getPageSource().contains("My Account")) {
			
			
			System.out.println("login success");
			
		}
		
		
	}
	
	public void click() {
		
		Click.click();
	}
	
	public void logout() {
		
		logoutclk.click();
		
	}
	
	public void logoutconi() {
		
		continueclk.click();
	}
	
	public void logoutpage() {
		
		if(driver.getPageSource().contains("Your Store")) {
			
			System.out.println("Logout is success");
		}
	}
	
	
	
}
