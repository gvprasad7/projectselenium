package com.amazon.bangalore.projectselenium.listeners;

import java.io.File; 

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.amazon.bangalore.projectselenium.testutil.BaseSeleniumTest;


public class Screen extends TestListenerAdapter{
	public static String NewFileNamePath;
	static ITestResult result;
	public static Logger logger = Logger.getLogger(Screen.class);
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";	
	
	
	//Take screen shot only for failed test case
	@Override
	public void onTestFailure(ITestResult testResult){
		System.setProperty(ESCAPE_PROPERTY, "false");
		String testMethodAndTestClass = testResult.getMethod().getMethodName();
		try {			
			// DateFormat dateFormat = new SimpleDateFormat("EEE-d-MMM-yyyy-HH-mm-ss-z");
			// Date date = new Date();
            
            logger.info("Screenshot captured for Test : " + testMethodAndTestClass );

            File file = new File("");
            String folName = file.getAbsolutePath() + "/target/surefire-reports/html/screenshots/";        
            // String fileName = dateFormat.format(date)+ "-" +testMethodAndTestClass+".png"; 
            String fileName = testMethodAndTestClass+".png";
    
            String filePath = folName + fileName;                  
            logger.info("File Name: " + filePath);
            File scrShotFile = ((TakesScreenshot)BaseSeleniumTest.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrShotFile, new File(filePath));
            Reporter.setCurrentTestResult(testResult);
            
            if(BaseSeleniumTest.properties.getProperty("modeOfExecution").equalsIgnoreCase("CI")){
            	// for CI execution       
            	// logger.info("CI mode");
            	String screenPath = "<a href=\"screenshots/"+fileName+"\"><img src= \"screenshots/"+fileName+"\" height='50' width='50'/></a>";
            	Reporter.log(screenPath);
              
             }
            else {
            	// for local execution 
            	logger.info("Local mode");
            	Reporter.log("<a href= \""+ filePath + "\" ><img src= \"" + filePath + "\" height='50' width='50'/> </a>");
            }

                logger.info("FAILED : Capturing of failure screenshot successfully for "+ testMethodAndTestClass +" method");
				} 
            
			catch (Exception e) {
			new RuntimeException(e.getMessage());
			logger.info("Oooops!!! encountered somme error while capturing screenshot");
			logger.info("< FAILED: "+ result.getName() +"> didn't execute successfully");
			}
			}		
	
	    @Override
	    public void onStart(ITestContext tc) {
	    	logger.info(tc.getName()+ "--Test Feature/SubFeature Execution Started");
	    }
	    
	    @Override
	    public void onFinish(ITestContext tc) {
	    	logger.info(tc.getName()+ "--Test Feature/SubFeature Execution Ended\n");
	    }
	
	    @Override
	    public void onTestSuccess(ITestResult tr) {
	    	logger.info(tr.getName()+ "--Test method success\n");
	    }
	    
	    @Override
	    public void onTestStart(ITestResult tr) {
	    	logger.info(tr.getName()+ "--Test method started");
	    }
}