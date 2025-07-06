package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.AddUserPage;


public class TC_001_SignUpTest extends ProjectSpecificationMethods

{

	@BeforeTest
	public void DataCon() throws IOException
	{
		readPropFile();
		sheetName="SignUpData";
		testName="SignUp Test";
		testDescription="Testing the SignUp functionality of the application with valid and invalid details";
		testAuthor="Nandhini";
	}
	
	@Test (dataProvider = "readData")
	public void signUpTest(String firstName, String lastName, String emailId, String passwrd, String ExpectedMessage) throws Exception
	{
		driver.findElement(By.id("signup")).click();
		AddUserPage obj = new AddUserPage(driver);
		obj.enterFirstname(firstName);
		obj.enterLastname(lastName);
		obj.enterEmail(emailId);
		obj.enterPassword(passwrd);
		obj.clickSubmit();
		obj.ValidateSignUp(ExpectedMessage);
		//obj.clickSignOut();
	}
	
	@AfterTest
	public void DataConClose() throws IOException
	{
		propClose();
	}
	
}
