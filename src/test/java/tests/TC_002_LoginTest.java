package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.LoginPage;

public class TC_002_LoginTest extends ProjectSpecificationMethods
{
	

	@BeforeTest
	public void DataCon() throws Exception
	{
		readPropFile();
		sheetName="LoginData";
		testName="Login Test";
		testDescription="Testing the Login functionality of the application with valid and invalid data";
		testAuthor="Nandhini";
	}
	
	@Test (dataProvider = "readData")
   public void LoginTest (String emailId, String pass, String status) throws Exception
   {
		LoginPage obj = new LoginPage(driver);
		obj.enterEmail(emailId);
		obj.enterPassword(pass);
		obj.clickSubmit();
		obj.ValidateLogin(status); 	
   }
	
	
	@Test
	public void passwordFieldMask()
	{
		WebElement ps = driver.findElement(By.id("password"));
		String PassFieldType = ps.getAttribute("type");
		
		if(PassFieldType.equalsIgnoreCase("password"))
		{
			System.out.println("Password is Masked");
		}
		else
		{
			System.out.println("Password is not Masked");
		}
		
		
	}

	@AfterTest
	public void DataConClose() throws IOException
	{
		propClose();
	}
	
	
}
