package com.urbanladder.Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.urbanladder.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	ReadConfig config=new ReadConfig();
	public String baseUrl=config.getApplicationUrl();
	public static WebDriver driver;
	public WebDriverWait wait;
	public Actions act;
	

	@Parameters("browser") 
	@BeforeClass
	public void setUp(@Optional("chrome") String browsr)
	{


		switch(browsr.toLowerCase())
		{
		case "chrome": 
			WebDriverManager.chromedriver().setup();

			ChromeOptions options=new ChromeOptions();

			HashMap<String, Integer> contentSettings=new HashMap<String, Integer>();
			contentSettings.put("notifications", 2);

			HashMap<String, Object> profile=new HashMap<String, Object>();
			profile.put("managed_default_content_settings", contentSettings);

			HashMap<String, Object> prefs=new HashMap<String, Object>();
			prefs.put("profile", profile);

			options.setExperimentalOption("prefs", prefs);

			driver=new ChromeDriver(options);

			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.setProfile(new FirefoxProfile());
			fOptions.addPreference("dom.webnotifications.enabled", false);
			driver=new FirefoxDriver(fOptions);

			break;
		default :
			System.err.println("Invalid Browser Name : ");
		}



		wait = new WebDriverWait(driver,Duration.ofMillis(3000));
		act=new Actions(driver);

	}

	public static String takesScreenshot(String testName) throws IOException
	{        
		
	
		LocalDateTime myLocalDateTimeObj=LocalDateTime.now();
		DateTimeFormatter dateTimeFormatterObj=DateTimeFormatter.ofPattern("ddMMyyyHHmmss");
		String cuDateAndTime= myLocalDateTimeObj.format(dateTimeFormatterObj);
    
		String filepath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+cuDateAndTime+".png";		
		File fType=(File)((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);		
		FileUtils.copyFile(fType, new File(filepath));
		return filepath;
		
	}


	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	

}
