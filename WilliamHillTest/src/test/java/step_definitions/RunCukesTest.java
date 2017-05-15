/**
 * 
 * class RunCukesTest
 * 
 * To execute the test, using cucmber plugins for reporting and formatting 
 * 
 */

package step_definitions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		plugin = {"pretty", "html:target/cucumber-html-report"},
		tags = {}
		)
public class RunCukesTest{
	
}