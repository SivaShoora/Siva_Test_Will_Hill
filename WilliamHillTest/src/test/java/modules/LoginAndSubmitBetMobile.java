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
import pageobjects.MobileHomePage;

public class LoginAndSubmitBetMobile {
	static String expRetPrice; // These strings are passed to other member class for Assertion								// 
	static double balanceBeforeBet;

	@SuppressWarnings("deprecation")
	public static void execute(WebDriver driver, String sport, String betAmount) throws Exception {
		// Method to execute the bet step
System.out.println("Calling Mobile Module");
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
		MobileHomePage.loginURL.click();
		Log.info("Click to open login frame");

		MobileHomePage.loginId.sendKeys("WHATA_FOG2");
		Log.info("Enter User Login");

		MobileHomePage.password.sendKeys("F0gUaTtest");
		Log.info("Enter Password");

		MobileHomePage.loginButton.click();
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
			wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.betOnSportFootball));
			MobileHomePage.betOnSportFootball.click();
			Log.info("Click on sport name label for betting");
		}

		else if (sport.equalsIgnoreCase("Tennis")) {
			wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.betOnSportTennis));
			MobileHomePage.betOnSportTennis.click();
			Log.info("Click on sport name label for betting");
		}
		
		Thread.sleep(5000);			//Using thread.sleep here, as it seems that there is issue in mobile navigation. 
									//e.g. Tennis URL is sometimes loading up inplay URL for few seconds and then redirecting to actual tennis URL
									//This needs further debugging in code/DOM.

		wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.event));
		MobileHomePage.event.click();
		Log.info("Click on event");

		wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.betButton));
		Actions actions = new Actions(driver);
		Log.info("Click on Bet Button");
		actions.pause(5000);			//Method pause is depricated for now, however as per Issue#7424 (Open as of today) it is set to come back.
		actions.moveToElement(MobileHomePage.betButton).click().perform(); // Using Mouse operation click to click on bet button
		
		wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.betSlipButtonToolbar));
		MobileHomePage.betSlipButtonToolbar.click();

		wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.betAmount));
		MobileHomePage.betAmount.click();
		Log.info("Click on Bet Amount TextBox");
		
		wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.numberPad));
		MobileHomePage.numberPadButton1.click();	//This is not a generic solution to enter betAmount.
		MobileHomePage.numberPadButton2.click();	//Due to existing defect with chromedriver.exe, this cannot be done by any other way except this
		MobileHomePage.numberPadButton3.click();	//Parametrization of bet amount would not work for mobile chromeDriver 
													//Other Option - Porting from chromedriver to other drivers
		
		wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.expectedReturnPrice));
		expRetPrice = MobileHomePage.expectedReturnPrice.getText();
		Log.info("Store Expected Return Price before submitting Bet");		

		balanceBeforeBet = Double.parseDouble(MobileHomePage.balanceLink.getText().substring(1));
		Log.info("Check Balance before placing bet");
		// Storing balance before bet to be used for assertion in sibling class
		MobileHomePage.placeBet.click();
		Log.info("Submit Bet");

		wait.until(ExpectedConditions.elementToBeClickable(MobileHomePage.betReceipt));
		MobileHomePage.betReceipt.click();
		Log.info("Expand bet receipt");

		Reporter.log("Bet Placed Successfully");
		
		/*
		 * There are lot of explicit waits, approximately for all elements. Reason being the nature of website. DOM wants us to wait for correct identification
		 * for seamless execution, waits are necessity here
		 */
	}

}
