package com.quantumretail.qi.pagefactory;

import com.quantumretail.selenium.GenericKeywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.*;

public class PrimaryTabsFactory {
    public static final List<String> PRIMARY_TAB_NAMES = new ArrayList<String>(
            Arrays.asList("HqFactory", "Product Setup", "Product Review", "Forecast", "Order Plan", "Distribution", "Events", "Administration"));
    public Map<QuantumScreens, WebElement> tabsMap = new HashMap<QuantumScreens, WebElement>();

    @FindBy(how = How.ID, using = "product-hq-title")
    public WebElement hqTab;

    @FindBy(how = How.ID, using = "product-setup-title")
    public WebElement productSetupTab;

    @FindBy(how = How.ID, using = "product-review-title")
    public WebElement productReviewTab;

    @FindBy(how = How.ID, using = "distribution-setup-title")
    public WebElement distribution;

    @FindBy(how = How.ID, using = "forecast-setup-title")
    public WebElement forecastTab;

    @FindBy(how = How.ID, using = "orderplan-setup-title")
    public WebElement orderPlanTab;

    @FindBy(how = How.ID, using = "event-setup-title")
    public WebElement eventsTab;

    @FindBy(how = How.ID, using = "admin-title")
    public WebElement adminTab;

    @FindBy(how = How.ID, using = "user-title")
    public WebElement userTab;

    @FindBy(how = How.ID, using = "seasonal-override-title")
    public WebElement seasonalTab;

    @FindBy(how = How.ID, using = "tolerance-admin-title")
    public WebElement toleranceTab;

    public List<String> getVisiblePrimaryTabs(){
        List<String> tabs = new ArrayList<String>();
        List<WebElement> tabElements = GenericKeywords.getDriver().findElements(By.name("tab-element"));
        for (WebElement element : tabElements){
            String tabName = element.getText();
            if (PRIMARY_TAB_NAMES.contains(tabName)){
                tabs.add(element.getText());
            }
        }
        return tabs;
    }

    public void init(){
        if (tabsMap.isEmpty()){
            tabsMap.put(QuantumScreens.HQ, hqTab);
            tabsMap.put(QuantumScreens.ProductSetup, productSetupTab);
            tabsMap.put(QuantumScreens.ProductReview, productReviewTab);
            tabsMap.put(QuantumScreens.Distribution, distribution);
            tabsMap.put(QuantumScreens.Forecast, forecastTab);
            tabsMap.put(QuantumScreens.OrderPlan, orderPlanTab);
            tabsMap.put(QuantumScreens.Events, eventsTab);
            tabsMap.put(QuantumScreens.Administration, adminTab);
            tabsMap.put(QuantumScreens.Users, userTab);
            tabsMap.put(QuantumScreens.SeasonalOverrides, seasonalTab);
            tabsMap.put(QuantumScreens.AlertTolerances, toleranceTab);
        }
    }

    public int orderPlanTabCount(){
        List<WebElement> element;
        element = orderPlanTab.findElements(By.xpath("."));
        return element.size();
    }
}
