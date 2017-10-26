package com.quantumretail.qi.testcases;

import com.quantumretail.qi.keywords.*;
import com.quantumretail.qi.pagefactory.ForecastFactory;
import com.quantumretail.selenium.GenericKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bhagya on 20-07-2017.
 */
public class ForecastTests extends ForecastKeywords {
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

        productSearch = new ProductSearch();
        productSearch.init();
        primaryTabsFunctions = new PrimaryTabs();
        primaryTabsFunctions.init();
    }


    @Test
    public void TC_Forecast_001() throws Exception {
        Thread.sleep(5000);
        primaryTabsFunctions.selectForecastScreen();
        productSearch.resetSearch();
        //productSearch.selectProduct("12805205: SHORT SLEEVE: BLUE: SMALL");
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: BLUE");
        forecastFactory.btnListView.click();

        Map<String, Double> forecastValuesAtStyleColor = new HashMap<>();
        forecastValuesAtStyleColor = getForecastValues();
        double before_baseforecast_sc = forecastValuesAtStyleColor.get("baseforecast");
        double before_customerforecast_sc = forecastValuesAtStyleColor.get("customerforecast");
        double before_qforecast_sc = forecastValuesAtStyleColor.get("qforecast");
        double before_qforecastcustomer_sc = forecastValuesAtStyleColor.get("qforecastcustomer");
        double before_customerforecastcustomer_sc = forecastValuesAtStyleColor.get("customerforecastcustomer");

        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: BLUE;12805205: SHORT SLEEVE: BLUE: SMALL");

        WebElement baseForecast = forecastFactory.getForecastListViewDataElement("Q Forecast Components>Base Forecast", "1/11/09").findElement(By.tagName("input"));
        double baseForecastValue = Double.parseDouble(baseForecast.getAttribute("value").replace(",", ""));
        System.out.println("Text Value: " + baseForecastValue);

        Map<String, Double> before_forecastValues = new HashMap<>();
        before_forecastValues = getForecastValues();
        double before_baseforecast = before_forecastValues.get("baseforecast");
        double before_customerforecast = before_forecastValues.get("customerforecast");
        double before_qforecast = before_forecastValues.get("qforecast");
        double before_qforecastcustomer = before_forecastValues.get("qforecastcustomer");
        double before_customerforecastcustomer = before_forecastValues.get("customerforecastcustomer");

        baseForecast.clear();
        baseForecast.sendKeys(baseForecastValue * 2 + "");
        //forecastFactory.btnSave.click();
        //clickWithJS(forecastFactory.btnSave);
        clickSave();
        waitForForecastTableDataToLoad();
        Thread.sleep(5000);
        Map<String, Double> after_forecastValues = new HashMap<>();
        after_forecastValues = getForecastValues();

        double after_baseforecast = after_forecastValues.get("baseforecast");
        double after_customerforecast = after_forecastValues.get("customerforecast");
        double after_qforecast = after_forecastValues.get("qforecast");
        double after_qforecastcustomer = after_forecastValues.get("qforecastcustomer");
        double after_customerforecastcustomer = after_forecastValues.get("customerforecastcustomer");

        System.out.println("before: " + before_baseforecast + ", after: " + after_baseforecast);
        System.out.println("before: " + before_customerforecast + ", after: " + after_customerforecast);
        System.out.println("before: " + before_qforecast + ", after: " + after_qforecast);
        System.out.println("before: " + before_qforecastcustomer + ", after: " + after_baseforecast);
        System.out.println("before: " + before_customerforecastcustomer + ", after: " + after_customerforecastcustomer);

        Assert.assertEquals((long) after_baseforecast, (long) (before_baseforecast * 2), "baseforecast");
        Assert.assertEquals((long) after_customerforecast, (long) (before_customerforecast * 2), "customerforecast");
        Assert.assertEquals((long) after_qforecast, (long) (before_qforecast * 2), "qforecast");
        Assert.assertEquals((long) after_qforecastcustomer, (long) (before_qforecastcustomer * 2), "qforecastcustomer");
        Assert.assertEquals((long) after_customerforecastcustomer, (long) (before_customerforecastcustomer * 2), "customerforecastcustomer");

        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: BLUE");
        forecastFactory.btnListView.click();

        Thread.sleep(5000);

        Map<String, Double> after_forecastValuesAtStyleColor = new HashMap<>();
        after_forecastValuesAtStyleColor = getForecastValues();
        double after_baseforecast_sc = after_forecastValuesAtStyleColor.get("baseforecast");
        double after_customerforecast_sc = after_forecastValuesAtStyleColor.get("customerforecast");
        double after_qforecast_sc = after_forecastValuesAtStyleColor.get("qforecast");
        double after_qforecastcustomer_sc = after_forecastValuesAtStyleColor.get("qforecastcustomer");
        double after_customerforecastcustomer_sc = after_forecastValuesAtStyleColor.get("customerforecastcustomer");

        Assert.assertEquals((long) (after_baseforecast_sc), (long) (before_baseforecast_sc + (after_baseforecast - before_baseforecast)), "baseforecast");
        Assert.assertEquals((long) (after_customerforecast_sc), (long) (before_customerforecast_sc + (after_customerforecast - before_customerforecast)), "customerforecast");
        Assert.assertEquals((long) (after_qforecast_sc), (long) (before_qforecast_sc + (after_qforecast - before_qforecast)), "qforecast");
        Assert.assertEquals((long) (after_qforecastcustomer_sc), (long) (before_qforecastcustomer_sc + (after_qforecastcustomer - before_qforecastcustomer)), "qforecastcustomer");
        Assert.assertEquals((long) (after_customerforecastcustomer_sc), (long) (before_customerforecastcustomer_sc + (after_customerforecastcustomer - before_customerforecastcustomer)), "customerforecastcustomer");
    }

    @Test
    public void TC_Forecast_002() throws Exception {
        Thread.sleep(1000);
        primaryTabsFunctions.selectForecastScreen();
        productSearch.resetSearch();
        //productSearch.selectProduct("12805205: SHORT SLEEVE: BLUE: SMALL");
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: GREEN;12806205: SHORT SLEEVE: GREEN: SMALL");
        forecastFactory.btnListView.click();

        Map<String, Double> forecastValuesAtStyleColor = new HashMap<>();
        forecastValuesAtStyleColor = getForecastValues();
        double before_baseforecast_sc = forecastValuesAtStyleColor.get("baseforecast");
        double before_customerforecast_sc = forecastValuesAtStyleColor.get("customerforecast");
        double before_qforecast_sc = forecastValuesAtStyleColor.get("qforecast");
        double before_qforecastcustomer_sc = forecastValuesAtStyleColor.get("qforecastcustomer");
        double before_customerforecastcustomer_sc = forecastValuesAtStyleColor.get("customerforecastcustomer");

        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: GREEN");

        WebElement baseForecast = forecastFactory.getForecastListViewDataElement("Q Forecast Components>Base Forecast", "1/11/09").findElement(By.tagName("input"));
        double baseForecastValue = Double.parseDouble(baseForecast.getAttribute("value").replace(",", ""));
        System.out.println("Text Value: " + baseForecastValue);

        Map<String, Double> before_forecastValues = new HashMap<>();
        before_forecastValues = getForecastValues();
        double before_baseforecast = before_forecastValues.get("baseforecast");
        double before_customerforecast = before_forecastValues.get("customerforecast");
        double before_qforecast = before_forecastValues.get("qforecast");
        double before_qforecastcustomer = before_forecastValues.get("qforecastcustomer");
        double before_customerforecastcustomer = before_forecastValues.get("customerforecastcustomer");

        baseForecast.clear();
        baseForecast.sendKeys(baseForecastValue * 3 + "");
        //forecastFactory.btnSave.click();
        //clickWithJS(forecastFactory.btnSave);
        clickSave();
        waitForForecastTableDataToLoad();
        Thread.sleep(5000);
        Map<String, Double> after_forecastValues = new HashMap<>();
        after_forecastValues = getForecastValues();

        double after_baseforecast = after_forecastValues.get("baseforecast");
        double after_customerforecast = after_forecastValues.get("customerforecast");
        double after_qforecast = after_forecastValues.get("qforecast");
        double after_qforecastcustomer = after_forecastValues.get("qforecastcustomer");
        double after_customerforecastcustomer = after_forecastValues.get("customerforecastcustomer");

        System.out.println("before: " + before_baseforecast + ", after: " + after_baseforecast);
        System.out.println("before: " + before_customerforecast + ", after: " + after_customerforecast);
        System.out.println("before: " + before_qforecast + ", after: " + after_qforecast);
        System.out.println("before: " + before_qforecastcustomer + ", after: " + after_baseforecast);
        System.out.println("before: " + before_customerforecastcustomer + ", after: " + after_customerforecastcustomer);

        Assert.assertEquals((long) after_baseforecast, (long) (before_baseforecast * 3), "baseforecast");
        Assert.assertEquals((long) after_customerforecast, (long) (before_customerforecast * 3), "customerforecast");
        Assert.assertEquals((long) after_qforecast, (long) (before_qforecast * 3), "qforecast");
        Assert.assertEquals((long) after_qforecastcustomer, (long) (before_qforecastcustomer * 3), "qforecastcustomer");
        Assert.assertEquals((long) after_customerforecastcustomer, (long) (before_customerforecastcustomer * 3), "customerforecastcustomer");

        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: GREEN;12806205: SHORT SLEEVE: GREEN: SMALL");
        forecastFactory.btnListView.click();

        Thread.sleep(5000);

        Map<String, Double> after_forecastValuesAtStyleColor = new HashMap<>();
        after_forecastValuesAtStyleColor = getForecastValues();
        double after_baseforecast_sc = after_forecastValuesAtStyleColor.get("baseforecast");
        double after_customerforecast_sc = after_forecastValuesAtStyleColor.get("customerforecast");
        double after_qforecast_sc = after_forecastValuesAtStyleColor.get("qforecast");
        double after_qforecastcustomer_sc = after_forecastValuesAtStyleColor.get("qforecastcustomer");
        double after_customerforecastcustomer_sc = after_forecastValuesAtStyleColor.get("customerforecastcustomer");

        Assert.assertEquals((long) (after_baseforecast_sc), (long) (before_baseforecast_sc * 3), "baseforecast");
        Assert.assertEquals((long) (after_customerforecast_sc), (long) (before_customerforecast_sc * 3), "customerforecast");
        Assert.assertEquals((long) (after_qforecast_sc), (long) (before_qforecast_sc * 3), "qforecast");
        Assert.assertEquals((long) (after_qforecastcustomer_sc), (long) (before_qforecastcustomer_sc * 3), "qforecastcustomer");
        Assert.assertEquals((long) (after_customerforecastcustomer_sc), (long) (before_customerforecastcustomer_sc * 3), "customerforecastcustomer");
    }


    @Test
    public void TC_Forecast_003() throws Exception {
        Thread.sleep(10000);
        primaryTabsFunctions.selectForecastScreen();
        productSearch.resetSearch();
        // Select Style level Product and capture customer forecast
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE");
        forecastFactory.btnListView.click();

        Map<String, Double> forecastValuesAtStyleLevel = new HashMap<>();
        forecastValuesAtStyleLevel = getForecastValues();
        double before_baseforecast_style = forecastValuesAtStyleLevel.get("baseforecast");
        double before_customerforecast_style = forecastValuesAtStyleLevel.get("customerforecast");
        double before_qforecast_style = forecastValuesAtStyleLevel.get("qforecast");
        double before_qforecastcustomer_style = forecastValuesAtStyleLevel.get("qforecastcustomer");
        double before_customerforecastcustomer_style = forecastValuesAtStyleLevel.get("customerforecastcustomer");
        double before_userAdjustmentIndex_style = forecastValuesAtStyleLevel.get("userAdjustmentIndex");
        forecastFactory.btnSelectProduct.click();

        //productSearch.selectProduct("12805205: SHORT SLEEVE: BLUE: SMALL");
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: RED");
        forecastFactory.btnListView.click();

        Map<String, Double> forecastValuesAtStyleColor = new HashMap<>();
        forecastValuesAtStyleColor = getForecastValues();
        double before_baseforecast_sc = forecastValuesAtStyleColor.get("baseforecast");
        double before_customerforecast_sc = forecastValuesAtStyleColor.get("customerforecast");
        double before_qforecast_sc = forecastValuesAtStyleColor.get("qforecast");
        double before_qforecastcustomer_sc = forecastValuesAtStyleColor.get("qforecastcustomer");
        double before_customerforecastcustomer_sc = forecastValuesAtStyleColor.get("customerforecastcustomer");
        double before_userAdjustmentIndex_sc = forecastValuesAtStyleColor.get("userAdjustmentIndex");

        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: RED;12807205: SHORT SLEEVE: RED: SMALL");

        WebElement customerForecast = forecastFactory.getForecastListViewDataElement("Forecasts>Customer Forecast", "1/11/09").findElement(By.tagName("input"));
        double customerForecastValue = Double.parseDouble(customerForecast.getAttribute("value").replace(",", ""));
        System.out.println("Text Value: " + customerForecastValue);

        Map<String, Double> before_forecastValues = new HashMap<>();
        before_forecastValues = getForecastValues();
        double before_baseforecast = before_forecastValues.get("baseforecast");
        double before_customerforecast = before_forecastValues.get("customerforecast");
        double before_qforecast = before_forecastValues.get("qforecast");
        double before_qforecastcustomer = before_forecastValues.get("qforecastcustomer");
        double before_customerforecastcustomer = before_forecastValues.get("customerforecastcustomer");
        double before_userAdjustmentIndex = before_forecastValues.get("userAdjustmentIndex");

        customerForecast.clear();
        customerForecast.sendKeys(customerForecastValue * 2 + "");
        //forecastFactory.btnSave.click();
        //clickWithJS(forecastFactory.btnSave);
        clickSave();
        waitForForecastTableDataToLoad();
        Thread.sleep(5000);
        Map<String, Double> after_forecastValues = new HashMap<>();
        after_forecastValues = getForecastValues();

        double after_baseforecast = after_forecastValues.get("baseforecast");
        double after_customerforecast = after_forecastValues.get("customerforecast");
        double after_qforecast = after_forecastValues.get("qforecast");
        double after_qforecastcustomer = after_forecastValues.get("qforecastcustomer");
        double after_customerforecastcustomer = after_forecastValues.get("customerforecastcustomer");
        double after_userAdjustmentIndex = after_forecastValues.get("userAdjustmentIndex");

        System.out.println("before: " + before_baseforecast + ", after: " + after_baseforecast);
        System.out.println("before: " + before_customerforecast + ", after: " + after_customerforecast);
        System.out.println("before: " + before_qforecast + ", after: " + after_qforecast);
        System.out.println("before: " + before_qforecastcustomer + ", after: " + after_baseforecast);
        System.out.println("before: " + before_customerforecastcustomer + ", after: " + after_customerforecastcustomer);
        System.out.println("before: " + before_userAdjustmentIndex + ", after: " + after_userAdjustmentIndex);

        Assert.assertEquals( after_baseforecast, (before_baseforecast), "baseforecast");
        Assert.assertEquals( after_customerforecast, (before_customerforecast * 2), "customerforecast");
        Assert.assertEquals( after_qforecast,  (before_qforecast), "qforecast");
        Assert.assertEquals( after_qforecastcustomer, (before_qforecastcustomer), "qforecastcustomer");
        Assert.assertEquals( after_customerforecastcustomer, (before_customerforecastcustomer * 2), "customerforecastcustomer");
        Assert.assertEquals( after_userAdjustmentIndex, (before_userAdjustmentIndex * 2), "userAdjustmentIndex");

        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: RED");
        forecastFactory.btnListView.click();

        Thread.sleep(5000);

        Map<String, Double> after_forecastValuesAtStyleColor = new HashMap<>();
        after_forecastValuesAtStyleColor = getForecastValues();
        double after_baseforecast_sc = after_forecastValuesAtStyleColor.get("baseforecast");
        double after_customerforecast_sc = after_forecastValuesAtStyleColor.get("customerforecast");
        double after_qforecast_sc = after_forecastValuesAtStyleColor.get("qforecast");
        double after_qforecastcustomer_sc = after_forecastValuesAtStyleColor.get("qforecastcustomer");
        double after_customerforecastcustomer_sc = after_forecastValuesAtStyleColor.get("customerforecastcustomer");
        double after_userAdjustmentIndex_sc = after_forecastValuesAtStyleColor.get("userAdjustmentIndex");

        Assert.assertEquals( Math.round(after_baseforecast_sc),Math.round(before_baseforecast_sc + (after_baseforecast - before_baseforecast)), "baseforecast");
        Assert.assertEquals(Math.round(after_customerforecast_sc),Math.round(before_customerforecast_sc + (after_customerforecast - before_customerforecast)), "customerforecast");
        Assert.assertEquals(Math.round(after_qforecast_sc),Math.round(before_qforecast_sc + (after_qforecast - before_qforecast)), "qforecast");
        Assert.assertEquals(Math.round(after_qforecastcustomer_sc), Math.round(before_qforecastcustomer_sc + (after_qforecastcustomer - before_qforecastcustomer)), "qforecastcustomer");
        Assert.assertEquals(Math.round(after_customerforecastcustomer_sc),Math.round(before_customerforecastcustomer_sc + (after_customerforecastcustomer - before_customerforecastcustomer)), "customerforecastcustomer");
        Assert.assertEquals( Math.round(after_userAdjustmentIndex_sc),  Math.round(before_userAdjustmentIndex_sc + (after_userAdjustmentIndex_sc - before_userAdjustmentIndex_sc)), "userAdjustmentIndex");

        // Select Stylevel product and verify changes made at sku reflected at Style level
        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE");
        forecastFactory.btnListView.click();

        Thread.sleep(5000);

        Map<String, Double> after_forecastValuesAtStyleLevel = new HashMap<>();
        after_forecastValuesAtStyleLevel = getForecastValues();
        double after_baseforecast_style = after_forecastValuesAtStyleLevel.get("baseforecast");
        double after_customerforecast_style = after_forecastValuesAtStyleLevel.get("customerforecast");
        double after_qforecast_style = after_forecastValuesAtStyleLevel.get("qforecast");
        double after_qforecastcustomer_style = after_forecastValuesAtStyleLevel.get("qforecastcustomer");
        double after_customerforecastcustomer_style = after_forecastValuesAtStyleLevel.get("customerforecastcustomer");
        double after_userAdjustmentIndex_style = after_forecastValuesAtStyleLevel.get("userAdjustmentIndex");

        Assert.assertEquals(Math.round(after_baseforecast_style), Math.round(before_baseforecast_style + (after_baseforecast - before_baseforecast)), "baseforecast");
        Assert.assertEquals(Math.round(after_customerforecast_style),Math.round(before_customerforecast_style + (after_customerforecast - before_customerforecast)), "customerforecast");
        Assert.assertEquals( Math.round(after_qforecast_style),  Math.round(before_qforecast_style + (after_qforecast - before_qforecast)), "qforecast");
        Assert.assertEquals(Math.round(after_qforecastcustomer_style), Math.round(before_qforecastcustomer_style + (after_qforecastcustomer - before_qforecastcustomer)), "qforecastcustomer");
        Assert.assertEquals(Math.round(after_customerforecastcustomer_style), Math.round(before_customerforecastcustomer_style + (after_customerforecastcustomer - before_customerforecastcustomer)), "customerforecastcustomer");
        Assert.assertEquals( Math.round(after_userAdjustmentIndex_style),  Math.round(before_userAdjustmentIndex_style + (after_userAdjustmentIndex_sc - before_userAdjustmentIndex_sc)), "userAdjustmentIndex");


    }



   // Top down approach Customer forecast
    @Test
    public void TC_Forecast_004() throws Exception {
        Thread.sleep(1000);
        primaryTabsFunctions.selectForecastScreen();
        productSearch.resetSearch();
        //productSearch.selectProduct("12805205: SHORT SLEEVE: BLUE: SMALL");
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: BLUE;12805205: SHORT SLEEVE: BLUE: SMALL");
        forecastFactory.btnListView.click();

        Map<String, Double> forecastValuesAtSkuLevel = new HashMap<>();
        forecastValuesAtSkuLevel = getForecastValues();
        double before_baseforecast_sku = forecastValuesAtSkuLevel.get("baseforecast");
        double before_customerforecast_sku = forecastValuesAtSkuLevel.get("customerforecast");
        double before_qforecast_sku = forecastValuesAtSkuLevel.get("qforecast");
        double before_qforecastcustomer_sku = forecastValuesAtSkuLevel.get("qforecastcustomer");
        double before_customerforecastcustomer_sku = forecastValuesAtSkuLevel.get("customerforecastcustomer");
        double before_userAdjustmentIndex_sku = forecastValuesAtSkuLevel.get("userAdjustmentIndex");
        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: BLUE");
        forecastFactory.btnListView.click();

        Map<String, Double> forecastValuesAtStyleColor = new HashMap<>();
        forecastValuesAtStyleColor = getForecastValues();
        double before_baseforecast_sc = forecastValuesAtStyleColor.get("baseforecast");
        double before_customerforecast_sc = forecastValuesAtStyleColor.get("customerforecast");
        double before_qforecast_sc = forecastValuesAtStyleColor.get("qforecast");
        double before_qforecastcustomer_sc = forecastValuesAtStyleColor.get("qforecastcustomer");
        double before_customerforecastcustomer_sc = forecastValuesAtStyleColor.get("customerforecastcustomer");
        double before_userAdjustmentIndex_sc = forecastValuesAtStyleColor.get("userAdjustmentIndex");

        forecastFactory.btnSelectProduct.click();
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE");

        WebElement customerForecast = forecastFactory.getForecastListViewDataElement("Forecasts>Customer Forecast", "1/11/09").findElement(By.tagName("input"));
        double customerForecastValue = Double.parseDouble(customerForecast.getAttribute("value").replace(",", ""));
        System.out.println("Text Value: " + customerForecastValue);

        Map<String, Double> before_forecastValues_Style = new HashMap<>();
        before_forecastValues_Style = getForecastValues();
        double before_baseforecast_style = before_forecastValues_Style.get("baseforecast");
        double before_customerforecast_style = before_forecastValues_Style.get("customerforecast");
        double before_qforecast_style = before_forecastValues_Style.get("qforecast");
        double before_qforecastcustomer_style = before_forecastValues_Style.get("qforecastcustomer");
        double before_customerforecastcustomer_style = before_forecastValues_Style.get("customerforecastcustomer");
        double before_userAdjustmentIndex_style = before_forecastValues_Style.get("userAdjustmentIndex");

        customerForecast.clear();
        customerForecast.sendKeys(customerForecastValue * 3 + "");
        //forecastFactory.btnSave.click();
        //clickWithJS(forecastFactory.btnSave);
        clickSave();
        waitForForecastTableDataToLoad();
        Thread.sleep(5000);
        Map<String, Double> after_forecastValues_Style = new HashMap<>();
        after_forecastValues_Style = getForecastValues();

        double after_baseforecast_style =  after_forecastValues_Style.get("baseforecast");
        double after_customerforecast_style =  after_forecastValues_Style.get("customerforecast");
        double after_qforecast_style =  after_forecastValues_Style.get("qforecast");
        double after_qforecastcustomer_style =  after_forecastValues_Style.get("qforecastcustomer");
        double after_customerforecastcustomer_style =  after_forecastValues_Style.get("customerforecastcustomer");
        double after_userAdjustmentIndex_style =  after_forecastValues_Style.get("userAdjustmentIndex");

        System.out.println("before: " + before_baseforecast_style + ", after: " + after_baseforecast_style);
        System.out.println("before: " + before_customerforecast_style + ", after: " + after_customerforecast_style);
        System.out.println("before: " + before_qforecast_style + ", after: " + after_qforecast_style);
        System.out.println("before: " + before_qforecastcustomer_style + ", after: " + after_baseforecast_style);
        System.out.println("before: " + before_customerforecastcustomer_style + ", after: " + after_customerforecastcustomer_style);
        System.out.println("before: " + before_userAdjustmentIndex_style + ", after: " + after_userAdjustmentIndex_style);

        Assert.assertEquals ( Math.round(after_baseforecast_style),   Math.round(before_baseforecast_style), "baseforecast");
        Assert.assertEquals( Math.round(after_customerforecast_style), Math.round(before_customerforecast_style * 3), "customerforecast");
        Assert.assertEquals( Math.round(after_qforecast_style),   Math.round(before_qforecast_style), "qforecast");
        Assert.assertEquals(  Math.round(after_qforecastcustomer_style),  Math.round(before_qforecastcustomer_style), "qforecastcustomer");
        Assert.assertEquals(  Math.round(after_customerforecastcustomer_style),  Math.round(before_customerforecastcustomer_style * 3), "customerforecastcustomer");
        Assert.assertEquals(Math.round(after_userAdjustmentIndex_style),   Math.round(before_userAdjustmentIndex_style * 3), "userAdjustmentIndex");

        clickOn(forecastFactory.btnSelectProduct);
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: BLUE");
        forecastFactory.btnListView.click();

        Thread.sleep(5000);

        Map<String, Double> after_forecastValuesAtStyleColor = new HashMap<>();
        after_forecastValuesAtStyleColor = getForecastValues();
        double after_baseforecast_sc = after_forecastValuesAtStyleColor.get("baseforecast");
        double after_customerforecast_sc = after_forecastValuesAtStyleColor.get("customerforecast");
        double after_qforecast_sc = after_forecastValuesAtStyleColor.get("qforecast");
        double after_qforecastcustomer_sc = after_forecastValuesAtStyleColor.get("qforecastcustomer");
        double after_customerforecastcustomer_sc = after_forecastValuesAtStyleColor.get("customerforecastcustomer");
        double after_userAdjustmentIndex_sc = after_forecastValuesAtStyleColor.get("userAdjustmentIndex");

        Assert.assertEquals(Math.round(after_baseforecast_sc),  Math.round(before_baseforecast_sc), "baseforecast");
        Assert.assertEquals( Math.round(after_customerforecast_sc),   Math.round(before_customerforecast_sc * 3), "customerforecast");
        Assert.assertEquals(  Math.round(after_qforecast_sc),  Math.round(before_qforecast_sc), "qforecast");
        Assert.assertEquals(  Math.round(after_qforecastcustomer_sc),  Math.round(before_qforecastcustomer_sc), "qforecastcustomer");
        Assert.assertEquals(  Math.round(after_customerforecastcustomer_sc),  Math.round(before_customerforecastcustomer_sc * 3), "customerforecastcustomer");
        Assert.assertEquals( Math.round( after_userAdjustmentIndex_sc),  Math.round(before_userAdjustmentIndex_sc * 3), "userAdjustmentIndex");

        clickOn(forecastFactory.btnSelectProduct);
        productSearch.selectRequiredProduct("1: MEN'S ACTIVE;3: SHIRTS;7: RALPH LAUREN;12: JERSEY;805: SHORT SLEEVE;805: SHORT SLEEVE: BLUE");
        forecastFactory.btnListView.click();

        Thread.sleep(5000);

        Map<String, Double> after_forecastValuesAtSku = new HashMap<>();
        after_forecastValuesAtSku = getForecastValues();
        double after_baseforecast_sku =  after_forecastValuesAtSku.get("baseforecast");
        double after_customerforecast_sku =  after_forecastValuesAtSku.get("customerforecast");
        double after_qforecast_sku =  after_forecastValuesAtSku.get("qforecast");
        double after_qforecastcustomer_sku =  after_forecastValuesAtSku.get("qforecastcustomer");
        double after_customerforecastcustomer_sku =  after_forecastValuesAtSku.get("customerforecastcustomer");
        double after_userAdjustmentIndex_sku =  after_forecastValuesAtSku.get("userAdjustmentIndex");

        Assert.assertEquals(  Math.round(after_baseforecast_sku),   Math.round(before_baseforecast_sku), "baseforecast");
        Assert.assertEquals(  Math.round(after_customerforecast_sku),   Math.round(before_customerforecast_sku * 3), "customerforecast");
        Assert.assertEquals(  Math.round(after_qforecast_sku),   Math.round(before_qforecast_sku), "qforecast");
        Assert.assertEquals(  Math.round(after_qforecastcustomer_sku),   Math.round(before_qforecastcustomer_sku), "qforecastcustomer");
        Assert.assertEquals( Math.round(after_customerforecastcustomer_sku),   Math.round(before_customerforecastcustomer_sku * 3), "customerforecastcustomer");
        Assert.assertEquals( Math.round (after_userAdjustmentIndex_sku),   Math.round(before_userAdjustmentIndex_sku * 3), "userAdjustmentIndex");

    }


    public Map<String, Double> getForecastValues() {
        WebElement baseForecast = forecastFactory.getForecastListViewDataElement("Q Forecast Components>Base Forecast", "1/11/09").findElement(By.tagName("input"));
        double baseForecastValue = Double.parseDouble(baseForecast.getAttribute("value").replace(",", ""));

        WebElement customerForecast = forecastFactory.getForecastListViewDataElement("Forecasts>Customer Forecast", "1/11/09").findElement(By.tagName("input"));
        double customerForecastValue = Double.parseDouble(customerForecast.getAttribute("value").replace(",", ""));

        WebElement qForecastInQComp = forecastFactory.getForecastListViewDataElement("Q Forecast Components>Q Forecast", "1/11/09");
        double qForecastInQCompValue = Double.parseDouble(qForecastInQComp.getText().replace(",", ""));

        WebElement qForecastInCustComp = forecastFactory.getForecastListViewDataElement("Customer Forecast Components>Q Forecast", "1/11/09");
        double qForecastInCustCompValue = Double.parseDouble(qForecastInCustComp.getText().replace(",", ""));

        WebElement custForecastInCustComp = forecastFactory.getForecastListViewDataElement("Customer Forecast Components>Customer Forecast", "1/11/09");
        double custForecastInCustCompValue = Double.parseDouble(custForecastInCustComp.getText().replace(",", ""));

        WebElement userAdjInd = forecastFactory.getForecastListViewDataElement("Customer Forecast Components>User Adjustment Index", "1/11/09");
        double userAdjIndValue = Double.parseDouble(userAdjInd.getText().replace(",", ""));

        Map<String, Double> values = new HashMap<>();
        values.put("baseforecast", baseForecastValue);
        values.put("customerforecast", customerForecastValue);
        values.put("qforecast", qForecastInQCompValue);
        values.put("qforecastcustomer", qForecastInCustCompValue);
        values.put("customerforecastcustomer", custForecastInCustCompValue);
        values.put("userAdjustmentIndex", userAdjIndValue);
        return values;
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        System.out.println("Browser Exit");
        GenericKeywords.getDriver().quit();
    }
}
