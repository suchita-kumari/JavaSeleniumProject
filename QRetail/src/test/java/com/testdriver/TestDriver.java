package com.testdriver;

import com.quantumretail.selenium.GenericKeywords;
import com.quantumretail.qi.keywords.CommonFunctions;
import com.quantumretail.utilities.TestBase;
import org.testng.TestNG;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhagya on 07-06-2017.
 */
public class TestDriver {
    public static void main(String[] args) {
        try {
            TestNG testng = new TestNG();
            List<String> suites = new ArrayList<String>();
            suites.add(System.getProperty("user.dir") + File.separator + "testng.xml");
            testng.setOutputDirectory(System.getProperty("user.dir") + File.separator + "TestResults");
            testng.setTestSuites(suites);
            testng.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            GenericKeywords.getDriver().quit();
        }
    }
}
