/**
 * 
 * class  PlaceBets
 * 
 * Main step definition class
 */
package step_definitions;

import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import modules.CustomAssertions;
import modules.CustomAssertionsMobile;
import modules.LoginAndSubmitBet;
import modules.LoginAndSubmitBetMobile;
import pageobjects.AutomationHomePage;
import pageobjects.MobileHomePage;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@RunWith(Cucumber.class)
public class PlaceBets {

	public WebDriver driver;
	private String betAmount; // This Variable is used for passing data between
								// steps

	/*
	 * Commented code below can be used for extending and scaling this framework
	 * for Excel Parametrization using DataHelper class public static
	 * List<HashMap<String, String>> datamap = null; public
	 * List<HashMap<String,String>> datamap = DataHelper.data();
	 */
	public PlaceBets() {
		driver = Hooks.driver;

		/*
		 * Commented code below can be used for extending and scaling this
		 * framework for Excel Parametrization using DataHelper class datamap =
		 * new ArrayList<HashMap<String, String>>(); // HashMap<String, String>
		 * sampleData = new HashMap<String, String>(); //
		 * sampleData.put("sport", "Football"); // sampleData.put("bet",
		 * "0.05"); // System.out.println("Current data" + sampleData);
		 * datamap.add(sampleData);
		 */
	}

	@When("^I open williamhill website to place a bet \"([^\"]*)\" for (.+)$") // Reading cucumber parametrized betAmount and Sport
	public void i_open_williamhill_website_to_place_a_bet_something_for(String betAmount, String sport)
			throws Throwable {
		driver.get("http://sports.williamhill.com/sr-admin-set-white-list-cookie.html");

		this.betAmount = betAmount; // Stores betAmount from Cucumber as a parameter so that can be used by other steps

		if (Hooks.browserSelected.equalsIgnoreCase("chrome-mobile")) {
			PageFactory.initElements(driver, MobileHomePage.class);
			LoginAndSubmitBetMobile.execute(driver, sport, betAmount); 	// Calling Login and Place Bet for Mobile
		} else {
			PageFactory.initElements(driver, AutomationHomePage.class);
			LoginAndSubmitBet.execute(driver, sport, betAmount); // Calling Login and Place Bet
		}
	}

	@Then("^Check retun value on bet slip$")
	public void validate_a_bet_is_placed() throws Throwable {
		if (Hooks.browserSelected.equalsIgnoreCase("chrome-mobile")) {
			PageFactory.initElements(driver, AutomationHomePage.class);

			CustomAssertionsMobile.checkReturnValue(driver); // Assertion
		} else {
			PageFactory.initElements(driver, MobileHomePage.class);

			CustomAssertions.checkReturnValue(driver); // Assertion
		}
	}

	@And("^Check Total stake on bet slip$")
	public void check_total_stake_on_bet_slip() throws Throwable {
		if (Hooks.browserSelected.equalsIgnoreCase("chrome-mobile"))
			CustomAssertionsMobile.checkStackValue(driver, betAmount); // Assertion
		else
			CustomAssertions.checkStackValue(driver, betAmount); // Assertion
	}

	@And("^User balance amount is reduced by bet amount$")
	public void user_balance_amount_is_reduced_by_bet_amount() throws Throwable {
		if (Hooks.browserSelected.equalsIgnoreCase("chrome-mobile"))
			CustomAssertionsMobile.checkBalance(driver, betAmount); // Assertion
		else
			CustomAssertions.checkBalance(driver, betAmount); // Assertion
	}

}
