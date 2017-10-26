package com.quantumretail.qi.keywords;

import com.gargoylesoftware.htmlunit.javascript.background.GAEJavaScriptExecutor;
import com.quantumretail.selenium.GenericKeywords;
import com.quantumretail.utilities.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Suchita on 14-06-2017.
 */
public class CommonFunctions {

    public void initializePageFactory() {
        LoginKeywords loginKeywords = new LoginKeywords();
        loginKeywords.init();
        PrimaryTabs primaryTabsKeywords= new PrimaryTabs();
        primaryTabsKeywords.init();
        ProductSetupKeywords ps = new ProductSetupKeywords();
        ps.init();
        ProductSearch productSearch = new ProductSearch();
        ps.init();;
        ForecastKeywords forecastKeywords=new ForecastKeywords();
        forecastKeywords.init();
        EventKeywords eventKeywords = new EventKeywords();
        eventKeywords.init();
    }

    public static WebElement getElementLastCalendar() {
        List<WebElement> calendars = new ArrayList<WebElement>();

        int count = 1;
        while (count <= 10) {
            /*Debug*/System.out.println("COUNT: "+count+" >>> getElementLastCalendar()");
            count++;

            try {
                calendars = GenericKeywords.getDriver().findElements(By.xpath("./html/body/div[7]"));
                break;
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }

        WebElement element = null;
        for (int i = calendars.size(); i > 0; i--) {
            if (calendars.get(i-1).getAttribute("class").equals("calendar")) {
                element = calendars.get(i-1);
                break;
            }
        }

        Assert.assertTrue(element != null,"FAILURE: element IS NULL (COULD NOT FIND CLASS 'calendar')");
        return element;

    }

    public static String getCalendarDisplayedMonth(){
        String currentMonthYear = getCalendarCurrentMonthYear();
        return currentMonthYear.split(",")[0].trim();
    }

    public static String getCalendarDisplayedYear() {
        String currentMonthYear = getCalendarCurrentMonthYear();
        return currentMonthYear.split(",")[1].trim();

    }
    static void selectCalendarNextYear() {
        getCalendarNextYearElement().click();
    }

    static void selectCalendarPreviousYear() {
        getCalendarPreviousYearElement().click();
    }

    public static WebElement getCalendarPreviousYearElement(){
        return getElementLastCalendar().findElement(By.xpath(".//tr[2]/td[1]/div"));
    }

    public static WebElement getCalendarNextYearElement(){
        return getElementLastCalendar().findElement(By.xpath(".//tr[2]/td[5]/div"));
    }

    public static String getCalendarCurrentMonthYear() {
        return getElementLastCalendar().findElement(By.xpath("./table/thead/tr[1]/td[2]")).getText();
    }

    public static WebElement getCalendarPreviousMonthElement(){
        return getElementLastCalendar().findElement(By.xpath(".//tr[2]/td[2]/div"));
    }

    public static WebElement getCalendarNextMonthElement(){
        return getElementLastCalendar().findElement(By.xpath(".//tr[2]/td[4]/div"));
    }

    public static WebElement getCalendarDateElement(String day){
        return getElementLastCalendar().findElement(By.xpath(".//td[text()='" + day + "']"));
    }

    final public static Date getCRC()  throws ParseException {


        String query = "select CURRENT_RUN_CYCLE from qr_system_config";

        String crc = getQueryResult(query);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(crc);

        return date;
    }

    public  static String getQueryResult(String query) {
        String result = "";
        Connection conn = null;
        PropertyLoader propertyLoader = new PropertyLoader();
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(propertyLoader.getDbConnectionUrl(), propertyLoader.getDbUserName(), propertyLoader.getDbPassword());
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            ps.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return result;
    }

    final public static String getCRCYear() {

        String crcYear = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        Calendar cal = Calendar.getInstance();

        try {

            cal.setTime(getCRC());
            crcYear = sdf.format(cal.getTime());

        }
        catch (ParseException e) {

            System.out.print("Exception occurs during Parsing Date." + e.getStackTrace());
        }

        return crcYear;

    }
}
