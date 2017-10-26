package com.quantumretail.qi.keywords;

import com.quantumretail.selenium.GenericKeywords;
import com.quantumretail.qi.pagefactory.PrimaryTabsFactory;
import com.quantumretail.qi.pagefactory.QuantumScreens;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Bhagya on 14-06-2017.
 */
public class PrimaryTabs extends GenericKeywords{
    private static PrimaryTabsFactory primaryTabs;

    public void selectHqScreen(){
        selectTab(QuantumScreens.getEnum("HqFactory"));
    }

    public void selectProductSetupScreen(){
        selectTab(QuantumScreens.getEnum("Product Setup"));
    }

    public void selectProductReviewScreen(){
        selectTab(QuantumScreens.getEnum("Product Review"));
    }

    public void selectDistributionScreen(){
        selectTab(QuantumScreens.getEnum("Distribution"));
    }

    public void selectForecastScreen(){
        selectTab(QuantumScreens.getEnum("Forecast"));
    }

    public void selectOrderPlanScreen(){
        selectTab(QuantumScreens.getEnum("Order Plan"));
    }

    public void selectEventsScreen(){
        selectTab(QuantumScreens.getEnum("Events"));
    }

    public void selectAdministrationScreen(){
        selectTab(QuantumScreens.getEnum("Administration"));
    }

    public void selectUsersScreen(){
        selectTab(QuantumScreens.getEnum("Users"));
    }

    public void selectAlertTolerancesScreen(){
        selectTab(QuantumScreens.getEnum("Alert Tolerances"));
    }

    public void selectTab(QuantumScreens tab) {
        primaryTabs.init();
        //clickOn(primaryTabs.tabsMap.get(tab));
        clickWithJS(primaryTabs.tabsMap.get(tab));
    }

    public int tabCount(QuantumScreens tab) {
        primaryTabs.init();
        try {
            primaryTabs.tabsMap.get(tab).findElements(By.xpath("."));
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public boolean isTabVisible(QuantumScreens tab) {
        List<String> uiTabs = primaryTabs.getVisiblePrimaryTabs();
        if (!uiTabs.contains(tab.name())) {
            return false;
        }
        return true;
    }

    public boolean isPrimaryTabVisible(List<String> tabNames) {
        List<String> uiTabs = primaryTabs.getVisiblePrimaryTabs();
        if (tabNames.size() != uiTabs.size()) {
            return false;
        }

        for (String name : tabNames) {
            if (!uiTabs.contains(name)) {
                return false;
            }
        }
        return true;
    }

    public void init() {
        primaryTabs = PageFactory.initElements(GenericKeywords.getDriver(), PrimaryTabsFactory.class);
    }
}
