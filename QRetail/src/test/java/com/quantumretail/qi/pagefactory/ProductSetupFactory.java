package com.quantumretail.qi.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by Bhagya on 14-06-2017.
 */
public class ProductSetupFactory {
    @FindBy(how = How.ID, using = "product-setup-title")
    public WebElement productSetupTab;
}
