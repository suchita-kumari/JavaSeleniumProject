package com.quantumretail.utilities;

import com.quantumretail.selenium.GenericKeywords;

/**
 * Created by Suchita on 22-06-2017.
 */
public class Logger {

    public void writeToLogFile(String type, String message) {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GenericKeywords.class.getName());

        String t = type.toUpperCase();
        if (t.equalsIgnoreCase("DEBUG")) {
            logger.debug(message);
        } else if (t.equalsIgnoreCase("INFO")) {
            logger.info(message);
        } else if (t.equalsIgnoreCase("WARN")) {
            logger.warn(message);
        } else if (t.equalsIgnoreCase("ERROR")) {
            logger.error(message);
        } else if (t.equalsIgnoreCase("FATAL")) {
            logger.fatal(message);
        } else {
            logger.error("Invalid log Type :" + type
                    + ". Unable to log the message.");
        }
    }
}
