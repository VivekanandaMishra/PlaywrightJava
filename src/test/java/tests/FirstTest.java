package tests;

import java.time.Duration;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.microsoft.playwright.assertions.PageAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import base.BaseTest;

//mvn exec:java -Dexec.mainClass="com.microsoft.playwright.CLI" -Dexec.args="codegen https://playwright.dev"

public class FirstTest extends BaseTest{

	public FirstTest() {
		// TODO Auto-generated constructor stub
		PlaywrightAssertions.setDefaultAssertionTimeout(10_000);
	}
	
	@Test
	public void verifyTitle() {
		// TODO Auto-generated method stub
		page.navigate("https://playwright.dev");
		System.out.println("Page Title: " + page.title());
		assertThat(page).hasTitle("Fast and reliable end-to-end testing for modern web apps | Playwright");
		assertThat(page).hasTitle(Pattern.compile(".*Playwright"), new PageAssertions.HasTitleOptions().setTimeout(10_000));
	}

}
