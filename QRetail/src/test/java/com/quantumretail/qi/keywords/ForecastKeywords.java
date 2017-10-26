package com.quantumretail.qi.keywords;

import com.quantumretail.qi.pagefactory.ForecastFactory;
import com.quantumretail.selenium.GenericKeywords;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Bhagya on 21-07-2017.
 */
public class ForecastKeywords extends GenericKeywords{
    public static ForecastFactory forecastFactory;

    final public void clickSave() {
        forecastFactory.btnSave.click();
        try {
            checkSuccessMessage();

        } catch (Exception e) {
        }
    }

    public void waitForForecastTableDataToLoad(){
        waitForElement(forecastFactory.tableData);
    }

    public void checkSuccessMessage() {
        waitForElement(forecastFactory.msgSaveSuccess);
        forecastFactory.msgSaveSuccess.isDisplayed();
    }

    public void init() {
        forecastFactory = PageFactory.initElements(GenericKeywords.getDriver(), ForecastFactory.class);
    }
}
