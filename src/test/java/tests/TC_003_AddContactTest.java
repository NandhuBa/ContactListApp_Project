package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.ProjectSpecificationMethods;
import pages.AddContact;
import pages.ContactDetails;
import pages.ContactList;

public class TC_003_AddContactTest extends ProjectSpecificationMethods 

{
	
@BeforeTest		
public void DataCon() throws Exception
{
readPropFile();
sheetName="AddContData";
testName="AddContact Test";
testDescription="Testing the AddContact functionality of the application with valid and invalid data";
testAuthor="Nandhini";
}



@Test(dataProvider = "readData", priority = 0)
public void AddContactTest (String Firstname, String Lastname, String DOB, String Email, String Phone, String StreetAdd_1, String StreetAdd_2, String City, String State, String PCode, String Country, String Status) throws Exception
{
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
	driver.findElement(By.id("add-contact")).click();

	
	AddContact obj1 = new AddContact(driver);
	obj1.enterFirstname(Firstname);
	obj1.enterLastname(Lastname);
	obj1.enterDOB(DOB);
	obj1.enterEmail(Email);
	obj1.enterPhone(Phone);
	obj1.enterStreet1(StreetAdd_1);
	obj1.enterStreet2(StreetAdd_2);
	obj1.enterCity(City);
	obj1.enterState(State);
	obj1.enterPostal(PCode);
	obj1.enterCountry(Country);
	obj1.clickSubmit();
	obj1.ValidateAddContact(Status);
}

@Test(priority = 1)
public void Edit_Contact() throws InterruptedException
{
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
	driver.get("https://thinking-tester-contact-list.herokuapp.com/contactList");
	ContactList clt = new ContactList(driver);
	clt.ClickOnCOntact();
	ContactDetails cd = new ContactDetails(driver);
	cd.EditContact();
	cd.Cancel_On_edit();
	cd.edit_LastName();
	cd.delete_Contact();
	
}

@Test (priority = 2)
public void Navigtn_Security() throws InterruptedException
{
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
	ContactList clt = new ContactList(driver);
    clt.Logout();  
} 

@Test(priority = 3)
public void backFeature_Test() throws InterruptedException {
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
	ContactList clt = new ContactList(driver);
	clt.backFeature();
}

@Test(priority = 4)
public void UI_Location_Test() throws InterruptedException {
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
    AddContact adc = new AddContact(driver);
    adc.UI_Location();
}

@Test(priority = 5)
public void Toast_Message_Test() throws InterruptedException {
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
    AddContact adc = new AddContact(driver);
    adc.testToastMessage();
}

@Test(priority = 6)
public void Max_Character_Test() {
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
    AddContact adc = new AddContact(driver);
    adc.Verify_MaxChar();
}

@Test(priority = 7)
public void Emoji_Verification_Test() throws InterruptedException {
	WebElement email = driver.findElement(By.id("email"));
	email.sendKeys("nandhini555@gmail.com");
	WebElement Pass = driver.findElement(By.id("password"));
	Pass.sendKeys("Niki@121113");
	driver.findElement(By.xpath("//button[text()='Submit']")).click();
    AddContact adc = new AddContact(driver);
    adc.emoji_verification();
}

@AfterTest
public void DataConClose() throws IOException
{
	propClose();
}

	
}
