package com.quantumretail.utilities;

import com.quantumretail.selenium.GenericKeywords;

/**
 * Created by Suchita on 31-05-2017.
 */
public class TestBase {
    public void setup() {
        PropertyLoader propertyLoader =new PropertyLoader();
        GenericKeywords genericKeywords =new GenericKeywords();
        genericKeywords.openBrowser();
        genericKeywords.openApplicationUrl(propertyLoader.getAppUrl());

        GenerateTestConfig generateConfig =new GenerateTestConfig();
        //generateConfig.createTestaCaseConfig();
    }
}
