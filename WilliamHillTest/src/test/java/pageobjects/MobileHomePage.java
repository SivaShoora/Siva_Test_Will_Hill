/*
 * 
 * class AutomationHomePage
 * 
 * Page Object Class, containing elements on website
 * 
 * Used Xpath, CSS and ID locators primarily.
 */

package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MobileHomePage extends BaseClass {

	public MobileHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='accountTabButton']/span[2]/span")
	public static WebElement loginURL;

	@FindBy(how = How.ID, using = "loginUsernameInput")
	public static WebElement loginId;

	@FindBy(how = How.ID, using = "loginPasswordInput")
	public static WebElement password;

	@FindBy(how = How.XPATH, using = "//*[@id='loginButton']/span")
	public static WebElement loginButton;

	@FindBy(how = How.ID, using = "nav-football")
	public static WebElement betOnSportFootball;

	@FindBy(how = How.ID, using = "nav-tennis")
	public static WebElement betOnSportTennis;

	@FindBy(how = How.ID, using = "betslip-btn-toolbar")
	public static WebElement betSlipButtonToolbar;
	
	@FindBy(how = How.XPATH, using = "//*[@class='event']//ul/li/a//span[2]")
	public static WebElement event;

	@FindBy(how = How.XPATH, using = "//*[@class='betbutton__odds']")
	public static WebElement betButton;

	@FindBy(how = How.XPATH, using = "//*[@class='betslip-selection__input-container ng-untouched ng-valid ng-dirty ng-valid-parse']//input[@type='text']")
	public static WebElement betAmount;

	@FindBy(how = How.XPATH, using = "//*[contains(@id,'stake-input')]")
	public static WebElement stakeInput;


	@FindBy(how = How.ID, using = "numberpad")
	public static WebElement numberPad;

	@FindBy(how = How.CSS, using = "#numberpad > div:nth-child(4) > button.btn.btn__hollow.btn__hollow--bottom-left.grid-col--has-flexi-truncate")
	public static WebElement numberPadButton1;
	
	@FindBy(how = How.CSS, using = "#numberpad > div:nth-child(4) > button:nth-child(2)")
	public static WebElement numberPadButton2;
	
	@FindBy(how = How.CSS, using = "#numberpad > div:nth-child(2) > button:nth-child(2)")
	public static WebElement numberPadButton3;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='total-to-return-price']")
	public static WebElement expectedReturnPrice;

	@FindBy(how = How.ID, using = "place-bet-button")
	public static WebElement placeBet;

	@FindBy(how = How.XPATH, using = "//*[@id='bet-receipt']/header/h2/div/a")
	public static WebElement betReceipt;

	@FindBy(how = How.XPATH, using = "//*[@id='betslip-footer']/div[2]/div[2]/div[1]")
	public static WebElement totalStakeLabel;

	@FindBy(how = How.XPATH, using = "//*[@id='total-stake-price']")
	public static WebElement totalStakeValue;

	@FindBy(how = How.XPATH, using = "//*[@id='betslip-footer']/div[2]/div[1]/div[1]")
	public static WebElement totalReturnLabel;

	@FindBy(how = How.XPATH, using = "//*[@id='total-to-return-price']")
	public static WebElement totalReturnValue;

	@FindBy(how = How.ID, using = "balanceLink")
	public static WebElement balanceLink;

}
