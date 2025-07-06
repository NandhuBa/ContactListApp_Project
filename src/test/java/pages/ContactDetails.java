package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ProjectSpecificationMethods;

public class ContactDetails extends ProjectSpecificationMethods

{

	public ContactDetails (WebDriver driver)
	{
		this.driver = driver;
	}
	
	public ContactDetails EditContact() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[@id='edit-contact']")).click();
		WebElement emailclick = driver.findElement(By.cssSelector("input[id='email'][contenteditable='true']"));
		emailclick.click();
		Thread.sleep(3000);
		emailclick.clear();
        emailclick.sendKeys("abcd123@gmail.com");
		driver.findElement(By.cssSelector("button[id='submit'][form='edit-contact']")).click();
		
		WebElement emailField = driver.findElement(By.xpath("//p[label[contains(text(), 'Email')]]/span"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[label[contains(text(), 'Email')]]/span")));
		String Email  = emailField.getText();
		if(Email.equalsIgnoreCase("abcd123@gmail.com"))
		{
			System.out.println("Updated Email address is :" + Email);
		}
		else
		{
			System.out.println("Contact details are still not yet updated in the List");
		}
		return this;
		
	}
	
	public ContactDetails Cancel_On_edit() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[@id='edit-contact']")).click();
		WebElement firstNameField = driver.findElement(By.xpath("//input[@id='firstName']"));
		firstNameField.click();
		firstNameField.clear();		
		
		Thread.sleep(2000);
		firstNameField.sendKeys("efg456@gmail.com");
		driver.findElement(By.xpath("//button[text()='Cancel']")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("contactDetails"));
		System.out.println("Contact Details Page displayed");
		
		WebElement emailField = driver.findElement(By.xpath("//p[label[contains(text(), 'Email')]]/span"));
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[label[contains(text(), 'Email')]]/span")));
		String Email  = emailField.getText();
		if(Email.equalsIgnoreCase("abcd123@gmail.com"))
		{
			System.out.println("Changes are not updated. So the old Email is" + " " + Email);
		}
		else
		{
			System.out.println("Changes are updated");
		}
		
		
		return this;
	}
	
	public ContactDetails edit_LastName() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[@id='edit-contact']")).click();
		WebElement lastNameField = driver.findElement(By.xpath("//input[@id='lastName']"));
		lastNameField.click();
		Thread.sleep(2000);
		lastNameField.clear();
	
		driver.findElement(By.cssSelector("button[id='submit'][form='edit-contact']")).click();
		
		WebElement errorMsg = driver.findElement(By.tagName("span"));
		Thread.sleep(2000);
		String actualText1 = errorMsg.getText();
		System.out.println(actualText1);
		return this;
	}
	
	public ContactDetails delete_Contact() throws InterruptedException
	{
		System.out.println("\nDELETE CONTACT FEATURE");
		
		driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/contactList");
		List<WebElement> Contacts = driver.findElements(By.xpath("(//table[@class='contactTable'])[1]//tr"));
		int Contacts_BefDelete = Contacts.size();
		
		System.out.println("No: of Contacts added initially -->" + " " + Contacts_BefDelete);
		 WebElement FirsContacts = Contacts.get(1);
		  Actions actions = new Actions(driver);
	      actions.moveToElement(FirsContacts).click().perform();
		
		driver.findElement(By.xpath("//button[text()='Delete Contact']")).click();
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait2.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		String alertText = alert.getText();
		System.out.println("Alert says:" + " " + alertText);
		alert.accept();
		Assert.assertTrue(driver.getCurrentUrl().contains("contact-list"));
		System.out.println("Alert Accepted for Contact Deletion");
		
		List<WebElement> Contacts1 = driver.findElements(By.xpath("(//table[@class='contactTable'])[1]//tr"));
		int Contacts_AfterDelete = Contacts1.size();
		System.out.println("No: of Contacts after deletion -->" + " " + Contacts_AfterDelete);
		
		if(Contacts_BefDelete>Contacts_AfterDelete)
		{
			System.out.println();
			System.out.println("Contact removed from the list Successfully");
		}
		else
		{
			System.out.println("Contact has not been removed");
		}

		return this;
		
	}
	
	public ContactList ReturnToContact()
	{
		driver.findElement(By.xpath("//button[id='return']")).click();
		return new ContactList(driver);
	}
	
}
