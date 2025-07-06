package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ProjectSpecificationMethods;

public class AddUserPage extends ProjectSpecificationMethods
{

	public AddUserPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public AddUserPage enterFirstname(String Firstname)
	{
		driver.findElement(By.id("firstName")).sendKeys(Firstname);
		return this;
	}
	
	public AddUserPage enterLastname(String Lastname)
	{
		driver.findElement(By.id("lastName")).sendKeys(Lastname);
		return this;
	}
	
	public AddUserPage enterEmail(String Email)
	{
		driver.findElement(By.id("email")).sendKeys(Email);
		return this;
	}
	
	public AddUserPage enterPassword(String Password)
	{
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Password);
		return this;
	}
	
	public AddUserPage clickSubmit()
	{
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		return this;
	}
	
	
	public void ValidateSignUp(String ExpectedMessage) throws InterruptedException
	{
		  if(ExpectedMessage.equals("ValidEmailValidPassword")) 
    	  {
    		  Assert.assertTrue(driver.getCurrentUrl().contains("contact-list"));
    		  System.out.println("SignUp Successfull");
	      }
	     else if (ExpectedMessage.equals("EmailAddressAlreadyInUse") || ExpectedMessage.equals("EmailInvalidPassRequired") || ExpectedMessage.equals("LastnameRequired")|| ExpectedMessage.equals("EmailInvalidFormat")|| ExpectedMessage.equals("PasswordRequired")) 
	      {
	    	 Thread.sleep(2000);
	    	 WebElement error = driver.findElement(By.tagName("span"));
	    	 String actualText = error.getText();
	         System.out.println("Login Failed with Message:" + " " + actualText);
	      }
	    
	}
	
}
