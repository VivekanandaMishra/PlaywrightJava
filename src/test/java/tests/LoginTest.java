package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import base.BaseTest;

public class LoginTest extends BaseTest {
	
	String sessionStoragePath = "src//test//resources//session//state.json";

	public LoginTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void login() {
		// TODO Auto-generated method stub
		page.navigate("https://practice.expandtesting.com/");

		Locator testLoginCard = page.locator(".card").filter(new Locator.FilterOptions()
				.setHas(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Test Login Page"))));

		testLoginCard.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Try it out")).click();

		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("VivekMishra");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Vivek123!");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

		assertThat(page.getByText("Secure Area page for Automation Testing Practice")).isVisible();

		assertThat(page.locator("#username")).hasText("Hi, vivekmishra!");

		context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(sessionStoragePath)));

	}

	@Test
	public void logout() {

		BrowserContext context = browser
				.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(sessionStoragePath)));
		Page page = context.newPage();
		
		removeAd(page);

		page.navigate("https://practice.expandtesting.com/");

		Locator testLoginCard = page.locator(".card").filter(new Locator.FilterOptions()
				.setHas(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Test Login Page"))));

		testLoginCard.getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Try it out")).click();

		assertThat(page.getByText("Secure Area page for Automation Testing Practice")).isVisible();

		assertThat(page.locator("#username")).hasText("Hi, vivekmishra!");

		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Logout")).click();

	}

}
