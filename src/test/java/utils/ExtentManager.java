package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private static ExtentReports extent;

	public ExtentManager() {
		// TODO Auto-generated constructor stub
	}

	public static ExtentReports getInstance() {
		
		if( extent == null ) {
			ExtentSparkReporter reporter = new ExtentSparkReporter("test-output//Extent-Report.html");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		
		return extent;
		
	}
}
