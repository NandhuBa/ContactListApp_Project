package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import base.ProjectSpecificationMethods;

public class AddContact extends ProjectSpecificationMethods
{
	
	public AddContact(WebDriver driver)
	{
		this.driver=driver;
		//PageFactory.initElements(driver,this);
	}
	
	public AddContact enterFirstname(String Firstname) 
	{
		driver.findElement(By.id("firstName")).sendKeys(Firstname);
		return this;
	}
	
	public AddContact enterLastname(String Lastname) 
	{
		driver.findElement(By.id("lastName")).sendKeys(Lastname);
		return this;
	}
	
	public AddContact enterDOB(String DOB) 
	{
		driver.findElement(By.id("birthdate")).sendKeys(DOB);
		return this;
	}
	
	public AddContact enterEmail(String email) 
	{
		driver.findElement(By.id("email")).sendKeys(email);
		return this;
	}
	public AddContact enterPhone(String phone) 
	{
		driver.findElement(By.id("phone")).sendKeys(phone);
		return this;
	}
	public AddContact enterStreet1(String street1) 
	{
		driver.findElement(By.id("street1")).sendKeys(street1);
		return this;
	}
	public AddContact enterStreet2(String street2) 
	{
		driver.findElement(By.id("street2")).sendKeys(street2);
		return this;
	}
	public AddContact enterCity(String city) 
	{
		driver.findElement(By.id("city")).sendKeys(city);
		return this;	
	}
	public AddContact enterState(String stateProvince) 
	{
		driver.findElement(By.id("stateProvince")).sendKeys(stateProvince);
		return this;
	}
	public AddContact enterPostal(String postalCode) 
	{
		driver.findElement(By.id("postalCode")).sendKeys(postalCode);
		return this;
	}
	public AddContact enterCountry(String country) 
	{
		driver.findElement(By.id("country")).sendKeys(country);
		return this;
	}

	
	public ContactList clickSubmit() 
	{
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		return new ContactList(driver);
	}
	
	public void ValidateAddContact(String Status) throws Exception
	{
		if(Status.equals("Valid Credential")) 
  	  {
  		  Assert.assertTrue(driver.getCurrentUrl().contains("contact-list"));
  		  System.out.println("Valid credential - added successfully");
  		 
  		 //Inspect table
  		List<WebElement> rows = driver.findElements(By.xpath("(//table[@class='contactTable'])[1]//tr"));
  		List<WebElement> columns = driver.findElements(By.xpath("(//table[@class='contactTable'])[1]//th"));
  		
  		int row_size = rows.size();
  		
  		
  		if(row_size>1)
  		{
  			System.out.println("Verified : New Contact added into the Table");
  			driver.findElement(By.id("add-contact")).click();
  			 Assert.assertTrue(driver.getCurrentUrl().contains("addContact"));
  	  		  System.out.println("Returning to Add_Contact Page");
  	  		  
  			WebElement inputField = driver.findElement(By.id("firstName"));
  			String Txt = inputField.getText();
  			System.out.println(Txt);
  			if (Txt.isBlank()) 
  			{
  		        System.out.println("Verified - Fields are cleared for new Entry");
  		    } else {
  		        System.out.println("Fields are not cleared for new Entry");
  		    }
  		}
  		else
  		{
  			System.out.println("Contact has not added into the Table");
  		}
	      }
			
		
	     else if (Status.equals("FirstNameAndLastName") || Status.equals("PhoneNumber Invalid")) 
	      {
	    	 try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 WebElement error = driver.findElement(By.tagName("span"));
	    	 String actualText = error.getText();
	         System.out.println(actualText);
	      }
	     else if (Status.equals("Duplicate credential"))
	     {
	    	 Assert.assertTrue(driver.getCurrentUrl().contains("contact-list"));
	  		  System.out.println("Adding Duplicate contact - added successfully");
	     }
		
	}
	
	 public AddContact UI_Location() throws InterruptedException
	 {	 
		softAssrt = new SoftAssert();
		 driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/contactList");
		 driver.findElement(By.id("add-contact")).click();
		 WebElement Fn = driver.findElement(By.id("firstName"));
		 int FnXl = Fn.getLocation().getX();
		 int FnYl = Fn.getLocation().getY();
		 //System.out.println("Location of FirstName is" + " " + FnXl + " " + FnYl);
				 
		 WebElement Ln = driver.findElement(By.id("lastName"));
		 int LnXl = Ln.getLocation().getX();
		 int LnYl = Ln.getLocation().getY();
		// System.out.println("Location of Lastname is" + " " + LnXl + " " + LnYl);
		 
		 WebElement DOB = driver.findElement(By.id("birthdate"));
		 int DOBXL = DOB.getLocation().getX();
		 int DOBYL = DOB.getLocation().getY();
		// System.out.println("Location of DOB is" + " " + DOBXL + " " + DOBYL);
		 
		 WebElement Email = driver.findElement(By.id("email"));
		 int EmailXL = Email.getLocation().getX();
		 int EmailYL = Email.getLocation().getY();
		 //System.out.println("Location of Email is" + " " + EmailXL + " " + EmailYL);
		 
		 WebElement Phone = driver.findElement(By.id("phone"));
		 int PhoneXL = Phone.getLocation().getX();
		 int PhoneYL = Phone.getLocation().getY();
		 //System.out.println("Location of Phone is" + " " + PhoneXL + " " + PhoneYL);
		 
		 WebElement Street1 = driver.findElement(By.id("street1"));
		 int Street1XL = Street1.getLocation().getX();
		 int Street1YL = Street1.getLocation().getY();
		// System.out.println("Location of Street_Address1 is" + " " + Street1XL + " " + Street1YL);
		 
		 WebElement Street2 = driver.findElement(By.id("street2"));
		 int Street2XL = Street2.getLocation().getX();
		 int Street2YL = Street2.getLocation().getY();
		 //System.out.println("Location of Street_Address2 is" + " " + Street2XL + " " + Street2YL);
		 
		 WebElement city = driver.findElement(By.id("city"));
		 int cityXL = city.getLocation().getX();
		 int cityYL = city.getLocation().getY();
		 //System.out.println("Location of City is" + " " + cityXL + " " + cityXL);
		 
		 WebElement PostC = driver.findElement(By.id("postalCode"));
		 int PostCXL = PostC.getLocation().getX();
		 int PostCYL = PostC.getLocation().getY();
		// System.out.println("Location of Postal code is" + " " + PostCXL + " " + PostCYL);
		 
		 System.out.println();
		 System.out.println("CHECKING ALIGNMENT");
		 softAssrt.assertTrue((Math.abs(FnYl - LnYl)<10 && Math.abs(FnXl - LnXl)<10 && Math.abs(DOBXL - EmailXL)<10 && Math.abs(EmailXL - Street1XL)<10 && Math.abs(Street1XL - Street2XL)<10 ), "First name and Last Name are misaligned");
		 System.out.println("Testcase Failed");
		 softAssrt.assertAll();
		 return this;
	 }
	 
	 public void testToastMessage() throws InterruptedException
	 {
		softAssrt = new SoftAssert();
		driver.findElement(By.id("add-contact")).click();
		driver.findElement(By.id("firstName")).sendKeys("anitha");
		driver.findElement(By.id("lastName")).sendKeys("ravi");
		driver.findElement(By.id("email")).sendKeys("vgh896@gmail.com"); 
		driver.findElement(By.id("phone")).sendKeys("1234689745");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		 
		WebElement toast = driver.findElement(By.tagName("p"));
		String ToastTxt = toast.getText();
		Thread.sleep(2000);
		 System.out.println();
		System.out.println("TOAST-MESSAGE FEATURE");
		softAssrt.assertTrue(ToastTxt.contains("success"),"No success/Toast Message Shown");
		System.out.println("Testcase Failed");
		softAssrt.assertAll();
	 }
	  
	 
	 public void Verify_MaxChar()
	 {
		 softAssrt = new SoftAssert();
		 driver.findElement(By.id("add-contact")).click();
		 String Longtext = "A".repeat(301);
		 WebElement Frst = driver.findElement(By.id("firstName"));
		 Frst.sendKeys(Longtext);
		 
		 String value = Frst.getAttribute("value");
		 System.out.println("CHECKING MAX-CHAR - FEATURE");
		 softAssrt.assertTrue(value.length()<=300, "Field accepted more than 300 characters");
		 System.out.println("Field did not accept more than 300 characters"); 
		 softAssrt.assertAll(); 
	 }
	 
	 public void emoji_verification() throws InterruptedException
	 {
		softAssrt = new SoftAssert();
		 System.out.println();
		 System.out.println("VERIFYING EMOJI - FEATURE");
		 driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/contactList");
		 driver.findElement(By.id("add-contact")).click();
		 driver.findElement(By.id("firstName")).sendKeys("Emojiname");
		 driver.findElement(By.id("lastName")).sendKeys("chennai");
		 WebElement Emojadd = driver.findElement(By.id("street1"));
		 Emojadd.sendKeys("⭐⭐⭐⭐⭐❤️");
		 driver.findElement(By.xpath("//button[text()='Submit']")).click();
		 
		 WebElement Rrow = driver.findElement(By.xpath("//table//tr[td[contains(text(),'Emojiname')]]"));
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tr[td[contains(text(),'Emojiname')]]")));
		 String rowText = Rrow.getText(); 	 
		 softAssrt.assertTrue(rowText.contains("⭐⭐⭐⭐⭐❤️"), "Address Field does not accept/show Emoji's");
		 System.out.println("System accepts and displays added Emoji's correctly");
		 softAssrt.assertAll();
	 }
}

