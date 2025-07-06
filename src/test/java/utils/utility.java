package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;


public class utility 

 {
	public static WebDriver driver; //Remove static while on parallel execution
	public static Properties prop = new Properties();
	public static FileOutputStream Output; 
	public String sheetName;
	public SoftAssert softAssrt;
	public static ExtentReports extent;
	public String testName, testDescription, testAuthor;
	public static ExtentTest test;

	public void launchBrowserAndLoadUrl(String browser, String url)
	{
	if(browser.equalsIgnoreCase("chrome"))
	{
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	}
	else if (browser.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
	driver = new EdgeDriver();
	}
	else
	{
		WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	}
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	}


	public void browserClose() 
	{
	driver.close();
	}

	public static void readPropFile() throws IOException
	{
	String Filepath = "C:\\Users\\Admin\\eclipse-workspace\\ContactListApp_Project\\src\\test\\resources\\data\\testdata.properties";
	try {
	FileInputStream file = new FileInputStream(Filepath);
	prop.load(file);
	file.close();
	}
	catch (IOException e)
	{
	e.printStackTrace();
	}
	Output = new FileOutputStream("C:\\\\Users\\\\Admin\\\\eclipse-workspace\\\\ContactListApp_Project\\\\src\\\\test\\\\resources\\\\data\\\\testdata.properties");
	prop.store(Output, "Msg Validation");
	}


	public static void propClose() throws IOException 
	{
	Output.close();
	}



	public static String[][] readTestData(String sheetName) throws Exception
	{
	XSSFWorkbook book = new XSSFWorkbook("C:\\Users\\Admin\\eclipse-workspace\\ContactListApp_Project\\src\\test\\resources\\data\\TestData.xlsx");
	XSSFSheet Sheet = book.getSheet(sheetName);

	int rowCount = Sheet.getLastRowNum();
	int columnCount = Sheet.getRow(0).getLastCellNum();
	String[][] data = new String[rowCount][columnCount];
	for(int i=1; i<=rowCount; i++) {
	XSSFRow row = Sheet.getRow(i);
	for(int j=0; j<columnCount ; j++) {
	XSSFCell cell = row.getCell(j);
	System.out.print(cell.getStringCellValue()+"|");
	data[i-1][j]=cell.getStringCellValue();
	}
	System.out.println();
	}
	book.close();
	return data;
	}
	
	public static String screenShot(String name) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String path = "C:\\Users\\Admin\\eclipse-workspace\\ContactListApp_Project\\src\\test\\resources\\testOutput\\snaps\\"+name+timestamp+".png";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}
	
	}