package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ProjectSpecificationMethods;

public class LoginPage extends ProjectSpecificationMethods
{
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Submit']")
	WebElement submit;
	
	@FindBy (id="error")
	WebElement error;
	
      public LoginPage(WebDriver driver)
      {
	   this.driver = driver; 
	   PageFactory.initElements(driver,this); 
      }
      
      public AddUserPage clickSignUp()
      {
    	  driver.findElement(By.id("signup")).click();
    	  return new AddUserPage(driver);
      }
      
      public LoginPage enterEmail(String emailId)
      {
    	  email.sendKeys(emailId);
    	  return this;
      }
      
      public LoginPage enterPassword(String emailId)
      {
    	  password.sendKeys(emailId);
    	  return this;
      }
      
      public ContactList clickSubmit()
      {
    	  submit.click();
    	  return new ContactList(driver);
      }
      
      public void ValidateLogin(String status) throws Exception
      {
    	  if(status.equals("true")) 
    	  {
    		Assert.assertTrue(driver.getCurrentUrl().contains("contact-list"));
    		System.out.println("Successfully landed into Contact List Page");
    	  }
    	  else if(status.equals("false")) 
    	  {
    		Thread.sleep(5000);  
    	    //Assert.assertTrue(error.getText().contains("Incorrect"));
    		WebElement Err = driver.findElement(By.tagName("span"));
    		String ErrText = Err.getText();
    	    System.out.println("Login Failed" + " " + ErrText);
    	  }
      }
}
