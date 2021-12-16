package steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import pageObjectModel.Login_Logout;








public class TestClass extends BaseClass {
	
	
	
	@BeforeTest
	public void extentsetup () {
		
		HtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\test-output\\MyReporter.html");
		
		HtmlReporter.config().setDocumentTitle("Automation Test");
		HtmlReporter.config().setReportName("Functional Test");
		HtmlReporter.config().setTheme(Theme.DARK);
		
		
		extent = new ExtentReports();
		
		extent.attachReporter(HtmlReporter);
		
		
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS" , "Windows10");
		extent.setSystemInfo("Tester Name" , "Rajasekar");
		extent.setSystemInfo("browser" , "Chrome");
		
	}
	
	
	@AfterTest
	public void ReportEnd () {
		
		extent.flush();
	}
	
	
	
	@BeforeMethod
	public void setup () throws IOException, InterruptedException {
		
		logger = Logger.getLogger("OpenCart_Project");
		PropertyConfigurator.configure("log4j.properties");
		
		configProp = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		String br = configProp.getProperty("browser");
		
		
        if(br.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
			driver= new ChromeDriver();
		}
		else if(br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("Webdriver.firefox.driver",configProp.getProperty("firefox"));	
			 driver=new FirefoxDriver();
			 
			 Thread.sleep(2000);
			
		}
        logger.info("==========LAUNCHING BROWSER=============");
        
        driver.get("https://demo.opencart.com");
        driver.manage().window().maximize();
        
        logger.info("==========LAUNCHING APPLICATION=============");
        
	}
	
	@Test(priority=0)
	public void verifylogin() throws InterruptedException {
		
        test = extent.createTest("verifylogin");
        
       
		LgLt = new Login_Logout(driver); 
		
		LgLt.Click();
		LgLt.Loginclk();
		
		logger.info("---------------PROVIDING LOGIN DETAILS------------");
		LgLt.setemail("rajadhanush70@gmail.com");
		LgLt.setpassword("8086kiss");
		LgLt.ClkLogin();
		
		Thread.sleep(2000);
		
		LgLt.loginsuccess();
		logger.info("---------------LOGIN SUCCESS------------");
	}
	
	@Test(priority=1)
	public void verifylogout() throws InterruptedException {
		
		test = extent.createTest("verifylogout");
		
		LgLt = new Login_Logout(driver);
		LgLt.Click();
		LgLt.Loginclk();
		
		logger.info("==================PROVIDING LOGIN DETAILS=================");
		LgLt.setemail("rajadhanush70@gmail.com");
		LgLt.setpassword("8086kiss");
		LgLt.ClkLogin();
		
		Thread.sleep(2000);
		LgLt.loginsuccess();
		
		logger.info("===================LOGIN SUCCESS====================");
		LgLt.click();
		LgLt.logout();
		Thread.sleep(2000);
		LgLt.logoutconi();
		LgLt.logoutpage();
		
		Thread.sleep(2000);
		
		logger.info("===================LOGOUT SUCCESS====================");
	}
	
       @Test(priority=2)	
       public void verifyInvalidlogin() {
		
        test = extent.createTest("verifyInvalidlogin");
        
       
		LgLt = new Login_Logout(driver); 
		
		LgLt.Click();
		LgLt.Loginclk();
		
		logger.info("---------------PROVIDING INVALID LOGIN DETAILS------------");
		LgLt.setemail("rajadhanush80@gmail.com"); //negative
		LgLt.setpassword("8086kiss");
		LgLt.ClkLogin();
		
		
		LgLt.loginsuccess();
		logger.info("---------------LOGIN UNSUCCESS------------");
	}
	
	@AfterMethod()
	public void teardown (ITestResult result) throws IOException, InterruptedException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			
	test.log(Status.FAIL,"This Test Case failed and name is :" +result.getName());
	
	logger.info("=================Test Case Failed====================");
	test.log(Status.FAIL,"This Test Case failed and throwable name is :" +result.getThrowable());
		
	String screenshotPath = TestClass.getScreenshot(driver, result.getName());
	
	test.addScreenCaptureFromPath(screenshotPath);
	
	
	
		}else if(result.getStatus()==ITestResult.SKIP) {
			
	test.log(Status.SKIP, "This Test Case Skipped and name is :" +result.getName());		
			
	logger.info("=================Test Case Skipped====================");
			
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			
			test.log(Status.PASS,"This Test Case Success and name is :" +result.getName());
			logger.info("=================Test Case Success====================");
		}
		
		Thread.sleep(2000);
		driver.quit();
	}
	
	

}
