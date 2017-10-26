package com.quantumretail.qi.pagefactory;

import com.quantumretail.selenium.GenericKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.lang.reflect.GenericArrayType;
import java.util.List;

/**
 * Created by Bhagya on 19-07-2017.
 */
public class ForecastFactory {

    @FindBy(how = How.ID, using = "metric-list-button")
    public WebElement btnListView;
    //public WebElement btnListView= GenericKeywords.getDriver().findElement(By.id("metric-list-button"));

    @FindBy(how = How.ID, using = "metric-chart-button")
    public WebElement btnGraphView;

    @FindBy(how = How.CSS, using = "div[class='top-buttons'] input[value='Save']")
    public WebElement btnSave;

    @FindBy(how = How.CSS, using = "div[class='top-buttons'] input[value='Save']")
    public WebElement msgSaveSuccess;

    @FindBy(how = How.ID, using = "//div[@id='location-forecast']/div[4]//input[@value='Cancel']")
    public WebElement btnCancel;

    @FindBy(how = How.XPATH, using = "//div[@id='location-forecast']/div[4]//input[@value='Export']")
    public WebElement btnExport;

    @FindBy(how = How.XPATH, using = "//div[@id='location-forecast']/div[4]//input[@id='metric-recalculation']")
    public WebElement btnRecalculate;

    @FindBy(how = How.XPATH, using = "//*[@id='metric-inner-table']/table/tbody/tr[*]/td[*]/input")
    public WebElement tableData;

    @FindBy(how = How.XPATH, using = "//input[@value='Select Product']")
    public WebElement btnSelectProduct;

    public WebElement getForecastListViewDataElement(String rowName, String weekDate){
        /*List<WebElement> rows = GenericKeywords.getDriver().findElements(By.xpath("/*//*[@id='metric-label-table']/tbody/tr/td"));
        int rowIndex=0;
        int temp=1;
        for (WebElement row: rows) {
            System.out.println("row:"+row.getText()+"end");
            if(row.getText().trim().equalsIgnoreCase(rowName.trim())){
                rowIndex=temp;
                break;
            }else{
                temp++;l
            }
        }*/
        String cellXpath="//div[@id='metric-inner-table']/table/tbody/tr["+getForecastTableRowIndex(rowName)+"]/td[@date='"+weekDate+"']";
        System.out.println("Text Xpath: "+ cellXpath);
        WebElement element = GenericKeywords.getDriver().findElement(By.xpath(cellXpath));
        return element;
    }
    private int getForecastTableRowIndex(String rowName){
        String[] rowNameSplit= rowName.split(">");
        int rowIndex=0;

        switch (rowNameSplit[0].trim().toUpperCase()) {
            case "FORECASTS": {
                switch(rowNameSplit[1].trim().toUpperCase()) {
                    case "CUSTOMER FORECAST":
                        rowIndex = 2;
                        break;
                    case "DEMAND":
                        rowIndex = 3;
                        break;
                    default:
                        //TO DO
                        //throw new Exception("Row name is not present in forecast table " + rowNameSplit[1]);
                }
            }
            break;
            case "SALES ANALYSIS": {
                switch(rowNameSplit[1].trim().toUpperCase()) {
                    case "SALES":
                        rowIndex = 5;
                        break;
                    case "DEMAND":
                        rowIndex = 6;
                        break;
                    case "SERVICE LEVEL":
                        rowIndex = 7;
                        break;
                    case "SALES LY":
                        rowIndex = 8;
                        break;
                    case "YOY RATIO":
                        rowIndex = 9;
                        break;
                    default:
                        //TO DO
                        //throw new Exception("Row name is not present in forecast table " + rowNameSplit[1]);
                }
            }
            break;
            case "Q FORECAST COMPONENTS": {
                switch(rowNameSplit[1].trim().toUpperCase()) {
                    case "SKU-STORES":
                        rowIndex = 11;
                        break;
                    case "BASE FORECAST":
                        rowIndex = 12;
                        break;
                    case "SEASONAL INDEX":
                        rowIndex = 13;
                        break;
                    case "LIFE CYCLE INDEX":
                        rowIndex = 14;
                        break;
                    case "Q FORECAST":
                        rowIndex = 15;
                        break;
                    default:
                        //throw new Exception("Row name is not present in forecast table " + rowNameSplit[1]);
                }
            }
            break;
            case "CUSTOMER FORECAST COMPONENTS": {
                switch(rowNameSplit[1].trim().toUpperCase()) {
                    case "Q FORECAST":
                        rowIndex = 17;
                        break;
                    case "EVENT INDEX":
                        rowIndex = 18;
                        break;
                    case "USER ADJUSTMENT INDEX":
                        rowIndex = 19;
                        break;
                    case "CUSTOMER FORECAST":
                        rowIndex = 20;
                        break;
                    default:
                        //throw new Exception("Row name is not present in forecast table " + rowNameSplit[1]);
                }
            }
            break;
            default:
                //TO DO
                //throw new Exception("Row name is not present in forecast table " + rowNameSplit[0]);
        }
        return rowIndex;
    }
}
