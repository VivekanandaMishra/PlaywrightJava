package base;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.ExtentManager;

import com.microsoft.playwright.BrowserType.LaunchOptions;

public class BaseTest {

	protected Playwright playwright;
	protected Browser browser;
	protected Page page;
	protected BrowserContext context;
	
	protected ExtentReports extent;
	protected ExtentTest test;

	public BaseTest() {
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setUp(Method method) {

		playwright = Playwright.create();
		browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false).setSlowMo(100));
		context = browser.newContext();
		page = context.newPage();
		
		removeAd(page);
		
		extent = ExtentManager.getInstance();
		test = extent.createTest(method.getName());

	}

	public void removeAd(Page page) {

		// List of common ad provider domains to block
		List<String> blockedResources = List.of("googlesyndication.com", "adservice.google.com", "doubleclick.net",
				"adsystem.com", "amazon-adsystem.com");

		// Intercept requests and abort if they match ad domains
		page.route("**/*", route -> {
			String url = route.request().url();
			boolean isAd = blockedResources.stream().anyMatch(url::contains);
			if (isAd) {
				route.abort();
			} else {
				route.resume();
			}
		});

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			test.fail(result.getThrowable());
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		} else {
			test.skip("Test Skipped");
		}
		
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();
	}

}
