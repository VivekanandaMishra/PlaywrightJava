package tests;

import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.LocatorOptions;

import base.BaseTest;

public class DynamicTableHandling extends BaseTest {

	public DynamicTableHandling() {
		// TODO Auto-generated constructor stub
	}

	@Test
	private void selectCheckBox() {
		// TODO Auto-generated method stub
		page.navigate("https://datatables.net/examples/select/checkbox/checkbox.html");
		Locator row = page.locator("table#example tbody tr");
		row.locator(":scope", new LocatorOptions().setHasText("Bradley Greer"))
				.locator(".dt-select-checkbox").click();
		row.locator(":scope").allInnerTexts().forEach(txt -> System.out.println(txt));
	}

}
