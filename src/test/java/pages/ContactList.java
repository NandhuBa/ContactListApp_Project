package pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import base.ProjectSpecificationMethods;

public class ContactList extends ProjectSpecificationMethods

{
	SoftAssert softAssrt;
   public ContactList(WebDriver driver)
   {
	   this.driver = driver;
   }
   
   public AddContact AddNewCntact()
   {
	   driver.findElement(By.id("add-contact")).click();
	   return new AddContact(driver);
   }
   
   public ContactDetails ClickOnCOntact() throws InterruptedException
   {
	  List<WebElement> rows = driver.findElements(By.xpath("(//table[@class='contactTable'])[1]//tr"));
	  
	  WebElement FirstRow = rows.get(1);
	  Actions actions = new Actions(driver);
      actions.moveToElement(FirstRow).click().perform();
	  
          Thread.sleep(2000);
          String currentUrl = driver.getCurrentUrl();
          if (currentUrl.contains("contactDetails")) {
              System.out.println("Successfully navigated to Contact Details page!");
          } else {
              System.out.println("Navigation to Contact Details page failed.");
          }
          return new ContactDetails(driver);
   }
   
   public LoginPage Logout() throws InterruptedException
   {
	   SoftAssert softAssrt = new SoftAssert();
	   System.out.println("LOGOUT - FEATURE");
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//button[@id='logout']"));
	   softAssrt.assertTrue(driver.getCurrentUrl().contains("https://thinking-tester-contact-list.herokuapp.com"));
	   System.out.println("Successfully Redirecting to Login_Page after Logout");
	   
	   System.out.println();
	   System.out.println("CHECKING NAVIGATION");
	   driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/contactList");
	   Thread.sleep(2000);
	   softAssrt.assertFalse(driver.getCurrentUrl().contains("contact-list"), "TestCase Failed - User navigates directly to “contact-list” without logging in");
	   System.out.println("TestCase Failed");
	   
	   System.out.println();
	   System.out.println("REFRESH - FEATURE");
	   driver.navigate().refresh();
	   Thread.sleep(2000);
	   softAssrt.assertTrue(driver.getCurrentUrl().contains("contact-list"));
	   System.out.println("Contact List Page remains Successfully after refresh");
	   softAssrt.assertAll();
	   
	   return new LoginPage(driver);
   }
   
   
   public void backFeature() throws InterruptedException
   {
	 SoftAssert softAssrt = new SoftAssert();
	  driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/contactList");
	  System.out.println();
	  System.out.println("CHECKING - BACK FEATURE");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//button[@id='logout']")).click();
	  driver.navigate().back();
	  softAssrt.assertFalse(driver.getCurrentUrl().contains("contact-list"), "TestCase Failed - User can go back to contact-list page after Logout ");
	  System.out.println("TestCase Failed");
	  softAssrt.assertAll();
   }
   
} 



