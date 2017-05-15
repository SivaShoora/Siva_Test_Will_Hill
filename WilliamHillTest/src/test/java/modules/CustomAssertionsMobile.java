/**
 * 
 * class CustomAssertions
 * 
 * Class for asserting required validations and checks on web under test
 * 
 * Asserting the following:
 	“To return:” value on the bet receipt
	“Total stake:” value on the bet receipt
 	User balance is updated (amount displays 0,05 £ less)
 * 
 */

package modules;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import helpers.Log;
import pageobjects.MobileHomePage;

public class CustomAssertionsMobile {
	
	public static void checkReturnValue(WebDriver driver) throws Exception{	//This method asserts return value
		Assert.assertEquals("To return:",MobileHomePage.totalReturnLabel.getText(),"Assertion for Total return price label failed");
		Log.info("Asserted ToReturn Label" );
		Assert.assertEquals(LoginAndSubmitBetMobile.expRetPrice,MobileHomePage.totalReturnValue.getText(),"Assertion for total return price value failed");
		//Storing expected Return price before submitting bet
		Log.info("Asserted ToReturn Value" );
	}

	public static void checkStackValue(WebDriver driver, String betValue) throws Exception{	//This method asserts stack value
		Assert.assertEquals("Total stake:",MobileHomePage.totalStakeLabel.getText(), "Assertion for Total stake label failed");
		Log.info("Asserted TotalStake Label" );
		Assert.assertEquals(betValue,MobileHomePage.totalStakeValue.getText(),"Assertion for total stake value failed");
		Log.info("Asserted TotalStake Value" );
	}
	
	public static void checkBalance(WebDriver driver, String betAmount) throws Exception{
		double balanceAfterBet = Double.parseDouble(MobileHomePage.balanceLink.getText().substring(1));
		Assert.assertEquals(balanceAfterBet+Double.parseDouble(betAmount), LoginAndSubmitBetMobile.balanceBeforeBet,0.001,"Balance after bet is incorrect");
		//Adding betAmount to balanceAfterBet such that it is equal to balanceBeforeBet in sunny day positive scenario.
		//Stored balanceBeforeBet earlier before submitting bet
		//Delta of 0.001, to ignore values starting third decimal place, as web under test displays balance upto two decimals
		Log.info("Asserted Balance deduction" );
	}
}