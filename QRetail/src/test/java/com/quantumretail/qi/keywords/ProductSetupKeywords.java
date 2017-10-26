package com.quantumretail.qi.keywords;

import com.quantumretail.selenium.GenericKeywords;
import com.quantumretail.qi.pagefactory.ProductSetupFactory;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Bhagya on 14-06-2017.
 */
public class ProductSetupKeywords extends GenericKeywords{
    private static ProductSetupFactory productSetupPage;

    public void init() {
        productSetupPage = PageFactory.initElements(GenericKeywords.getDriver(), ProductSetupFactory.class);
    }
}