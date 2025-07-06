package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import base.ProjectSpecificationMethods;

public class Listeners extends ProjectSpecificationMethods implements ITestListener

{
    
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
		
		String path = null;
		try {
		path=screenShot(result.getMethod().getMethodName());
	    }catch (Exception e)
		{
		e.printStackTrace();
		}
	
		test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
    }

	public void onTestFailure(ITestResult result)
	{
		test.fail(result.getThrowable());
		
		String path = null;
		try {
		path=screenShot(result.getMethod().getMethodName());
	    }catch (Exception e)
		{
		e.printStackTrace();
		}
	
		test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
	}
}