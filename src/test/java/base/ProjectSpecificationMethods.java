package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.utility;

public class ProjectSpecificationMethods extends utility
{
	@BeforeSuite
	public void createReport()
	{
		ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\Admin\\eclipse-workspace\\ContactListApp_Project\\src\\test\\resources\\testOutput\\ContactListApp_Report.html");
		reporter.config().setReportName("Contact List App Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeClass
	public void testDetails()
	{
		test = extent.createTest(testName, testDescription);
		test.assignAuthor(testAuthor);
		
	}
	
	@Parameters({"browser","url"})
	@BeforeMethod
	public void browserLaunchAndUrlLoad(String browser, String url) 
	{
		launchBrowserAndLoadUrl(browser,url);
		//softAssrt = new SoftAssert();
	}
	
    @AfterMethod
	public void closeBrowser()
    {
		//browserClose();
		driver.quit();
	}
    
    @DataProvider(name = "readData")
    public String[][] dataRead() throws Exception
    {
    	return readTestData(sheetName);
    }
   
    @AfterSuite
    public void closeReport()
    {
    	extent.flush();
    }
}
