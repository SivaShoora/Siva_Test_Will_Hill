/**
 * 
 * class Log
 * 
 * Class supporting logging
 * 
 */

package helpers;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.Logger;

public class Log {
	final static Logger Log = Logger.getLogger(Log.class);
	public static void main(String[] args) {
//		Logger Log = Logger.getLogger(Log.class);
		String log4jConfigFile = "log4j.xml";
		DOMConfigurator.configure(log4jConfigFile);
		Log.info("FirstLine");
	}

	// 
	public static void startTestCase(String sTestCaseName) {
		Log.info("Started Test case");
	}

	public static void endTestCase(String sTestCaseName) {
		Log.info("Ended Test Case");
	}

	public static void info(String message) {
		Log.info(message);
	}
}