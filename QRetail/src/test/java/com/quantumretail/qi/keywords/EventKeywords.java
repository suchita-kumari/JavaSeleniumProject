package com.quantumretail.qi.keywords;

import com.quantumretail.qi.pagefactory.EventFactory;
import com.quantumretail.qi.pagefactory.ForecastFactory;
import com.quantumretail.selenium.GenericKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Suchita K on 8/3/2017.
 */
public class EventKeywords extends GenericKeywords {

    public static EventFactory  eventFactory;

    final public void clickAddEvent() {

        JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
        executor.executeScript("arguments[0].click();",eventFactory.addEvent);


    }

    final public void selectProductOverride()
    {
        JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
        executor.executeScript("arguments[0].click();",eventFactory.addProductOverride);

    }

    final public void selectLocationOverride()
    {
        JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
        executor.executeScript("arguments[0].click();",eventFactory.addLocationOverride);

    }

    final public void applyOverride()
    {
        JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
        executor.executeScript("arguments[0].click();",eventFactory.applyOverride);

    }

    final public void selectStartDateCalendar() {

        JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
        executor.executeScript("arguments[0].click();",eventFactory.startDate);


    }

    final public void selectEndDateCalendar() {

        JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
        executor.executeScript("arguments[0].click();",eventFactory.endDate);


    }

    public void selectEventStartDate(String startDate) throws Exception {
        selectCalendar(startDate);
        String dateEntered = GenericKeywords.getDriver().findElement(By.id("event-start-date")).getAttribute("value");
        Assert.assertEquals(dateEntered, startDate,"Date value entered doesn't equal date selected");
    }

    public void selectEventEndDate(String endate) throws Exception {
        selectCalendar(endate);
        String dateEntered = GenericKeywords.getDriver().findElement(By.id("event-end-date")).getAttribute("value");
        Assert.assertEquals(dateEntered,endate,"Date value entered doesn't equal date selected");
    }
    public static void selectCalendar(String date) throws ParseException {

        String[] DateElements = date.split("/");
        String month = DateElements[0];
        String day = DateElements[1];
        String year = DateElements[2];
        CommonFunctions commonFunctions = new CommonFunctions();
        //handle year first
        int crcYear = Integer.parseInt(commonFunctions.getCRCYear());
        int dateYear = Integer.parseInt(year);

        if (dateYear < crcYear) {
            for (int j = dateYear; j < crcYear; j++) {
                commonFunctions.selectCalendarPreviousYear();
            }
        } else if (dateYear > crcYear) {
            for (int j = crcYear; j < dateYear; j++) {
                commonFunctions.selectCalendarNextYear();
            }
        }

        int dateMonth = Integer.parseInt(month);
        String displayedMonth =commonFunctions.getCalendarDisplayedMonth();
        int displayedMonthNumber = getMonthAsNumber(displayedMonth);
        if (dateMonth < displayedMonthNumber) {
            for (int j = dateMonth; j < displayedMonthNumber; j++) {
                selectCalendarPreviousMonth();
            }

        } else if (dateMonth > displayedMonthNumber) {
            for (int j = displayedMonthNumber; j < dateMonth; j++) {
                selectCalendarNextMonth();
            }
        }
        selectCalendarDate(Integer.parseInt(day) + "");

    }

    public static int getMonthAsNumber(String month) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("MMM").parse(month));
        int monthInt = cal.get(Calendar.MONTH) + 1;
        return monthInt;
    }

    private static void selectCalendarPreviousMonth() {
        new CommonFunctions().getCalendarPreviousMonthElement().click();

    }

    private static void selectCalendarNextMonth() {
        new CommonFunctions().getCalendarNextMonthElement().click();
    }

    public static void selectCalendarDate(String day) {
       new CommonFunctions().getCalendarDateElement(day).click();
    }
    final public void clickSave() {
        eventFactory.eventSave.click();
        try {
            checkSuccessMessage();

        } catch (Exception e) {
        }
    }

    final public void clickSaveAndBack() {

        eventFactory.eventSaveAndBack.click();
        try {
            checkSuccessMessage();

        } catch (Exception e) {
        }
    }

    public void checkSuccessMessage() {
        waitForElement(eventFactory.msgSaveSuccess);
        eventFactory.msgSaveSuccess.isDisplayed();
    }


    public void waitForEventScreenToLoad(){
        waitForElement(EventFactory.addEvent,5000);
    }


    public void selectRequiredProductFromEvent(String productFullHierarchy) throws InterruptedException {

        String products[] = productFullHierarchy.split(";");
        int i=1;
        for (String level:products) {
            WebElement productElement=GenericKeywords.getDriver().findElement(By.xpath("//table[@id='product-graph-search-table']/following::a[text()=\"" + level + "\"][1]/ancestor::li[1]"));
            if(i!=products.length) {
                while (productElement.getAttribute("class").contains("jstree-closed"))
                {
                    JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
                    executor.executeScript("arguments[0].click();", productElement.findElement(By.tagName("ins")));
                 //   productElement.findElement(By.tagName("ins")).click();
                }
            }
            else {
                System.out.println("pi_id"+productElement.findElement(By.tagName("a")).getText());
                JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
                executor.executeScript("arguments[0].click();", productElement.findElement(By.xpath("./a/ins")));

                //productElement.findElement(By.tagName("a")).click();
            }
            i++;

        }
    }

    public void selectProductFromOverride(String productFullHierarchy) throws InterruptedException {

        String products[] = productFullHierarchy.split(";");
        int i=1;
        for (String level:products) {
            WebElement productElement=GenericKeywords.getDriver().findElement(By.xpath("//table[@id='override-graph-search-table']/following::a[text()=\"" + level + "\"][1]/ancestor::li[1]"));
            if(i!=products.length) {
                while (productElement.getAttribute("class").contains("jstree-closed"))
                {
                    JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
                    executor.executeScript("arguments[0].click();", productElement.findElement(By.tagName("ins")));
                    //   productElement.findElement(By.tagName("ins")).click();
                }
            }
            else {
                System.out.println("pi_id"+productElement.findElement(By.tagName("a")).getText());
                JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
                executor.executeScript("arguments[0].click();", productElement.findElement(By.xpath("./a/ins")));

                //productElement.findElement(By.tagName("a")).click();
            }
            i++;

        }
    }


    public void selectRequiredLocationFromEvent(String LocationFullHierarchy) throws InterruptedException {

        String locations[] = LocationFullHierarchy.split(";");
        int i=1;
        for (String level:locations) {
            WebElement locationElement=GenericKeywords.getDriver().findElement(By.xpath("//table[@id='product-graph-search-table']/following::a[text()=\"" + level + "\"][2]/ancestor::li[1]"));
            if(i!=locations.length) {
                while (locationElement.getAttribute("class").contains("jstree-closed"))
                {
                    JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
                    executor.executeScript("arguments[0].click();", locationElement.findElement(By.tagName("ins")));

                    //locationElement.findElement(By.tagName("ins")).click();
                }
            }
            else {
                System.out.println("loc_id"+locationElement.findElement(By.tagName("a")).getText());
                JavascriptExecutor executor = (JavascriptExecutor) GenericKeywords.getDriver();
                executor.executeScript("arguments[0].click();", locationElement.findElement(By.xpath("./a/ins")));

               // locationElement.findElement(By.tagName("a")).click();
            }
            i++;

        }
    }

    public static void enterDetailUpliftPercents(String upliftPercent) {
        List<WebElement> upliftPercents = EventFactory.upliftPercents;
        for (WebElement element : upliftPercents) {
            String value = element.getAttribute("value");
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(upliftPercent);
        }
    }

    public static void enterUpliftPercentsToOverride(String upliftPercent) {
        List<WebElement> upliftPercents = EventFactory.upliftOverridePercents;
        for (WebElement element : upliftPercents) {
            String value = element.getAttribute("value");
            element.click();
            element.sendKeys(Keys.DELETE);
            element.sendKeys(upliftPercent);
        }
    }

    public void init() {
        eventFactory = PageFactory.initElements(GenericKeywords.getDriver(), EventFactory.class);
    }
}
