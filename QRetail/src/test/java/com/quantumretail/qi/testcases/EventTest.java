package com.quantumretail.qi.testcases;

import com.quantumretail.qi.keywords.*;
import com.quantumretail.qi.pagefactory.EventFactory;
import com.quantumretail.selenium.GenericKeywords;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



/**
 * Created by Suchita  on 8/3/2017.
 */
public class EventTest extends EventKeywords {

        LoginKeywords loginKeywords;
        ProductSearch productSearch;
        PrimaryTabs primaryTabsFunctions;
        CommonFunctions commonFunction;

    @BeforeMethod
    public void testCaseSetup() throws Exception {
        setup();
        commonFunction = new CommonFunctions();
        commonFunction.initializePageFactory();
        loginKeywords = new LoginKeywords();
        loginKeywords.loginQ();
        EventFactory eventFactory =new EventFactory();
        primaryTabsFunctions = new PrimaryTabs();
        primaryTabsFunctions.init();
    }

    @Test
    public void TC_DE_001() throws Exception {
        Thread.sleep(5000);
        primaryTabsFunctions.selectEventsScreen();
        waitForPageLoad();
        // waitForEventScreenToLoad();
        clickAddEvent();
        eventFactory.selectEventType("Demand");
        eventFactory.setEventNameField("Scenario_DE1");
        // Select Event start date
        selectStartDateCalendar();
        selectEventStartDate("2/5/17");
        Thread.sleep(100);
        // Select Event end date
        selectEndDateCalendar();
        selectEventEndDate("2/20/17");
        selectRequiredProductFromEvent("18-Champs;18-07-CHILDRENS FOOTWEAR;18-07-100-SLIDES;18-07-28558-0-04-HYDRO 6 (P) BLK/WHT/GRY;18-07-28558-0-04-010:HYDRO 6 (P) BLK/WHT/GRY");
        selectRequiredLocationFromEvent("18-Champs");
        enterDetailUpliftPercents("100");
        selectProductOverride();
        selectProductFromOverride("18-Champs;18-07-CHILDRENS FOOTWEAR;18-07-100-SLIDES;18-07-28558-0-04-HYDRO 6 (P) BLK/WHT/GRY;18-07-28558-0-04-010:HYDRO 6 (P) BLK/WHT/GRY");
        enterUpliftPercentsToOverride("200");
        applyOverride();
        selectLocationOverride();
        applyOverride();


        }

    @AfterMethod
    public void teardown() throws InterruptedException {
        System.out.println("Browser Exit");
        GenericKeywords.getDriver().quit();
    }
}
