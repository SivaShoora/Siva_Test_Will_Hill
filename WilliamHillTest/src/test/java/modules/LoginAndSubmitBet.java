/**
 * 
 * class CustomLoginAndSubmitBet
 * 
 * Logs into website and places a bet
 * 
 */

package modules;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import helpers.Log;
import pageobjects.AutomationHomePage;

public class LoginAndSubmitBet {
	static String expRetPrice; // These strings are passed to other member class for Assertion								// 
	static double balanceBeforeBet;

	@SuppressWarnings("deprecation")
	public static void execute(WebDriver driver, String sport, String betAmount) throws Exception {
		// Method to execute the bet step

		FluentWait<WebDriver> wait = new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class)
				.ignoring(WebDriverException.class);
		/*
		 * FluentWaitWebDriver implementation to wait for elements to appear.
		 * Ignoring StaleElement Exception as Selenium thinks that element has
		 * staled from DOM while it'still loading Ignoring WebDriver Exception,
		 * which intemittenly return exception with unknown reason because
		 * element to be selected has few overlays before it loads up
		 * completely.
		 */

		/*
		 * Performing actions on elements by reading them from page object class
		 */
		AutomationHomePage.loginURL.click();
		Log.info("Click to open login frame");

		AutomationHomePage.loginId.sendKeys("WHATA_FOG2");
		Log.info("Enter User Login");

		AutomationHomePage.password.sendKeys("F0gUaTtest");
		Log.info("Enter Password");

		AutomationHomePage.loginButton.click();
		Log.info("Submit Login Information");

		/*
		 * In future, elements of each sport should be added in Page object
		 * class and to be included in below if else statement paradigm Reason
		 * for this is FindBy annotation of page objects excepts compile time
		 * constants only, which means we should not parametrize the locator
		 * string. More details at
		 * http://stackoverflow.com/questions/21263367/webdriver-pageobject-
		 * findby-how-to-specify-xpath-with-dynamic-value
		 */
		if (sport.equalsIgnoreCase("Football")) {
			wait.until(ExpectedConditions.elementToBeClickable(AutomationHomePage.betOnSportFootball));
			AutomationHomePage.betOnSportFootball.click();
			Log.info("Click on sport name label for betting");
		}

		else if (sport.equalsIgnoreCase("Tennis")) {
			wait.until(ExpectedConditions.elementToBeClickable(AutomationHomePage.betOnSportTennis));
			AutomationHomePage.betOnSportTennis.click();
			Log.info("Click on sport name label for betting");
		}

		wait.until(ExpectedConditions.elementToBeClickable(AutomationHomePage.event));
		AutomationHomePage.event.click();
		Log.info("Click on event");

		wait.until(ExpectedConditions.elementToBeClickable(AutomationHomePage.betButton));
		Actions actions = new Actions(driver);
		Log.info("Click on Bet Button");
		actions.pause(5000);			//Method pause is depricated for now, however as per Issue#7424 (Open as of today) it is set to come back.
		actions.moveToElement(AutomationHomePage.betButton).click().perform(); // Using Mouse operation click to click on bet button
		

		wait.until(ExpectedConditions.elementToBeClickable(AutomationHomePage.betAmount));
		AutomationHomePage.betAmount.sendKeys(betAmount);
		Log.info("Enter Bet Amount");

		wait.until(ExpectedConditions.elementToBeClickable(AutomationHomePage.expectedReturnPrice));
		expRetPrice = AutomationHomePage.expectedReturnPrice.getText();
		Log.info("Store Expected Return Price before submitting Bet");

		balanceBeforeBet = Double.parseDouble(AutomationHomePage.balanceLink.getText().substring(1));
		Log.info("Check Balance before placing bet");
		// Storing balance before bet to be used for assertion in sibling class
		AutomationHomePage.placeBet.click();
		Log.info("Submit Bet");

		wait.until(ExpectedConditions.elementToBeClickable(AutomationHomePage.betReceipt));
		AutomationHomePage.betReceipt.click();
		Log.info("Expand bet receipt");

		Reporter.log("Bet Placed Successfully");
		
		/*
		 * There are lot of explicit waits, approximately for all elements. Reason being the nature of website. DOM wants us to wait for correct identification
		 * for seamless execution, waits are necessity here
		 */
	}

}
