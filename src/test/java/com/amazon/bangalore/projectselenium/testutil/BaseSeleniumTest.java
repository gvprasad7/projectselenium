package com.amazon.bangalore.projectselenium.testutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * This is base class for entire test suite
 * 
 * @author gvprasad
 *
 */
public class BaseSeleniumTest {
		
	public static Properties properties = null;
	public  String WINDOWS_IE_DRIVER = "/browserDrivers/IEDriverServer.exe";
	public static String WINDOWS_CHROME_DRIVER = "/browserDrivers/chromedriver.exe";
	public static String WINDOWS_FF_DRIVER = "/browserDrivers/geckodriver.exe";
	public String downloadCSVPath = System.getProperty("user.dir")+"\\src\\test\\resources\\csvlocation\\";
	
	public static Logger logger = Logger.getLogger(BaseSeleniumTest.class);
	
	static HSSFSheet ExcelWSheet;
	static HSSFWorkbook ExcelWBook;
	static HSSFCell Cell;
	
	public static WebDriver driver;    
    private Date dateStart;
	private Date dateEnd;
	static DateFormat dformat = new SimpleDateFormat("EEE_d_MMM_yyyy_HH_mm_ss_z");

	/**
	 * Default wait time for a page to be displayed. 12 seconds. The average
	 * webpage load time is 6 seconds in 2012. Based on your tests, please set
	 * this value. "0" will nullify implicitlyWait and speed up a test.
	 */
    public static final int DEFAULT_WAIT_4_PAGE = 10;

	@BeforeSuite(alwaysRun = true)
	public void initSuite() throws Exception
	{
		DesiredCapabilities dc = new DesiredCapabilities().chrome();
		dc.setPlatform(Platform.WIN10);
		URL url = new URL("http://localhost:4444/wd/hub");
		RemoteWebDriver driver = new RemoteWebDriver(url, dc);
		
		
		startDate();
		deleteScreenDir();
		propertyInit();
		logger.info("Test suite initialisation and execution started successfully at: "+dateStart);
	}
	
	@AfterSuite(alwaysRun = true)
	public void endSuite() throws IOException
	{
		endDate();
	}
	
	/**
	 * selecting desired browser
	 * @throws Exception
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception
	{	
		driverSetup(); 		
	}
	protected void driverSetup() throws Exception {
		String browser=properties.getProperty("testBrowser");
		if(browser.equalsIgnoreCase("firefox"))
		{
			// Please note not tested in firefox as per requirement
			System.setProperty("webdriver.gecko.driver", BaseSeleniumTest.class.getResource(WINDOWS_FF_DRIVER).getFile());
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Started <"+browser+"> browser successfully");
		}
		else if(browser.equalsIgnoreCase("IE"))
		{ 
			// Please note not tested in ie as per requirement
			System.setProperty("webdriver.ie.driver", BaseSeleniumTest.class.getResource(WINDOWS_IE_DRIVER).getFile());
			driver = new InternetExplorerDriver();		
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			logger.info("Started <"+browser+"> browser successfully");	
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", BaseSeleniumTest.class.getResource(WINDOWS_CHROME_DRIVER).getFile());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--ignore-certificate-errors");
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_4_PAGE, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Started <"+browser+"> browser successfully");
		}
		else
		{
			logger.info("<"+ browser +">"+ " is not correct one, please select the correct browser");
			quitDriver();
		}
	}
	
    /**
     * close and quit the webdriver.
     * @throws IOException 
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
    	quitDriver();
    }

	protected void quitDriver() {
    	driver.quit();
	}
	
	/**
	 * provides at what time test suite execution started
	 */
	public void startDate()
	{
		dateStart = new Date();
	}
	
    /**
     * Initialize properties
     * @throws IOException
     */
	public static void propertyInit() throws IOException {
		if (properties != null) {
			return;
		}
		properties = new Properties();
		InputStream fs = null;
		try {
			fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testInputData.properties");
		properties.load(fs);
		PropertyConfigurator.configure(properties);
		} finally {
			fs.close();
    }
	}
    
    /**
     * Delete screen shots folder collected in the previous execution
     * @throws IOException
     */
    public void deleteScreenDir() throws IOException {
    	String screenLoc = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\screenshots\\";
        File dir = new File(screenLoc);
        if (dir.exists()) {
            FileUtils.cleanDirectory(dir);
        }
    }

	/**
	 *  provides at what time test suite execution stopped and how much time taken to execute the entire test suite
	 */
	public void endDate()
	{
		dateEnd = new Date();
		logger.info("Test Suite Execution Ended at: "+dateEnd);
		long diff = dateEnd.getTime() - dateStart.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		logger.info("Total Time taken to execute entire test suite: " +diffHours +" hours, " + diffMinutes +" minutes, "+ diffSeconds +" seconds");	
	}

   public String getTestData(String propertyKeyName) {
       return properties.getProperty(propertyKeyName);
   }

}
