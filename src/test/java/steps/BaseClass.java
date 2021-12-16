package steps;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.utils.FileUtil;

import pageObjectModel.Login_Logout;

public class BaseClass {
	
	public WebDriver driver;
	public Login_Logout LgLt;
    public static Logger logger;
    public Properties configProp;
    public ExtentHtmlReporter HtmlReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
public static String getScreenshot (WebDriver driver, String screenshotName) throws IOException {
		
		String dateName = new SimpleDateFormat("yyyMMddhhmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		
		
	File source = ts.getScreenshotAs(OutputType.FILE);
	
	String destination = System.getProperty("user.dir") +"/Screenshots/" + screenshotName + dateName + ".png";
			
	File Finaldestination = new File(destination);
	
	FileUtils.copyFile(source, Finaldestination);
	
	return destination;
		
		
	}
   
}
