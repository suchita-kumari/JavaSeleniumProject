package com.quantumretail.qi.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Suchita K on 8/3/2017.
 */
public class EventFactory {


    @FindBy(how = How.ID, using ="event-add-button")
    public static WebElement addEvent;

    @FindBy(how = How.ID, using = "event-save-and-back-button")
    public static WebElement eventSaveAndBack;

    @FindBy(how = How.ID, using = "event-save-button")
    public static WebElement eventSave;

    @FindBy(how = How.CSS, using = "div[class='top-buttons'] input[value='Save']")
    public WebElement msgSaveSuccess;

    @FindBy(how = How.XPATH, using = "//*[@id='product-tree']/thead/tr/th/div/input")
    public static WebElement addProductOverride;

    @FindBy(how = How.XPATH, using = ".//*[@id='location-tree']/thead/tr/th/div/input")
    public static WebElement addLocationOverride;

    @FindBy(how = How.XPATH, using = "//*[@id='event-override-window']/div[2]/div[4]/input[3]")
    public static WebElement applyOverride;

    @FindBy(how = How.XPATH, using = "//*[@id='event-add-window']/div[2]/select")
    public static WebElement eventTypeElement;

    @FindBy(how = How.ID, using = "event-end-date")
    public WebElement endDate;

    @FindBy(how = How.ID, using = "event-start-date")
    public WebElement startDate;

    @FindBy(how = How.ID, using = "event-name")
    public WebElement eventName;

    @FindBy(how = How.CLASS_NAME, using = "event-uplift-percent")
    public static List<WebElement> upliftPercents;

    @FindBy(how = How.XPATH, using = "//*[@id='override_uplift_table']/div[2]/descendant::input[contains(@class,'event-uplift-percent')]")
    public static List<WebElement> upliftOverridePercents;

    public static  Select getEventTypeSelector(){
        return new Select(eventTypeElement);
    }

    public static void selectEventType(String eventType){
          getEventTypeSelector().selectByVisibleText(eventType);
    }

    public WebElement getEventNameField()
    {
        return eventName;
    }

    public void setEventNameField(String EventName)
    {
        getEventNameField().clear();
        getEventNameField().sendKeys(EventName);
    }
}
