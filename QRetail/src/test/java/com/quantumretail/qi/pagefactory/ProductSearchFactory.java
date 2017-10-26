package com.quantumretail.qi.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by Bhagya on 15-06-2017.
 */
public class ProductSearchFactory {

    @FindBy(how = How.XPATH, using = "//*[@id='context-search-window']/div[2]/input[3]")
    public WebElement reset;

    public WebElement returnElement(String elementName) {
        int count = 1;
        while (count <= 200) {
            count++;
            try {
                return getElement(elementName);
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
        return getElement(elementName);
    }

    public WebElement returnElement(String elementName, int index) {
        int count = 1;
        while (count <= 200) {
            count++;
            try {
                return getElement(elementName, index);
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }

        return getElement(elementName, index);
    }

    public List<WebElement> returnElements(String elementName) {
        int count = 1;
        while (count <= 200) {
            count++;
            try {
                return getElements(elementName);
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }

        return getElements(elementName);
    }

    public List<WebElement> returnElements(String elementName, int index) {
        int count = 1;
        while (count <= 200) {
            count++;
            try {
                return getElements(elementName, index);
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }

        return getElements(elementName);
    }

    final String CONTEXT_SEARCH_FIELD = "context-search-field";
    @FindBy(how = How.ID, using = CONTEXT_SEARCH_FIELD)
    public WebElement contextSearchPartial;

    final String CONTEXT_TABLE_DIV = "context-table-div";
    @FindBy(how = How.ID, using = CONTEXT_TABLE_DIV)
    public WebElement contextTableDiv;

    final String CONTEXT_TABLE_WRAPPER = "context-table_wrapper";
    @FindBy(how = How.ID, using = CONTEXT_TABLE_WRAPPER)
    public WebElement contextTableWrapper;

    final String CONTEXT_TABLE = "context-table";
    @FindBy(how = How.ID, using = CONTEXT_TABLE)
    public WebElement contextTable;

    final String CONTEXT_TABLE_RESULT_ROW = "Context Table Result Row";//no id just xpaths

    final String CONTEXT_TABLE_RESULT_ROW_DATA = "Context Table Result Row Data";//no id just xpaths

    public WebElement getElement(String elementName) {
        WebElement element = null;

        if (elementName.equals(CONTEXT_SEARCH_FIELD)) {
            element = contextSearchPartial.findElement(By.xpath("."));
        }
        if (elementName.equals(CONTEXT_TABLE_DIV)) {
            element = contextTableDiv.findElement(By.xpath("."));
        }
        if (elementName.equals(CONTEXT_TABLE_WRAPPER)) {
            element = contextTableWrapper.findElement(By.xpath("."));
        }
        return element;
    }

    public WebElement getElement(String elementName, int index) {
        WebElement element = null;

        if (elementName.equals(CONTEXT_TABLE_RESULT_ROW_DATA)) {
            element = contextTable.findElement(By.xpath("./tbody/tr[" + index + "]/td"));
        }
        return element;
    }


    public WebElement elementContextSearchField() {
        return returnElement(CONTEXT_SEARCH_FIELD);
    }

    public WebElement elementContextTableDiv() {
        return returnElement(CONTEXT_TABLE_DIV);
    }

    public WebElement elementContextTableWrapper() {
        return returnElement(CONTEXT_TABLE_WRAPPER);
    }

    public WebElement elementContextTableResultRowData(int index) {
        return returnElement(CONTEXT_TABLE_RESULT_ROW_DATA, index);
    }

    public List<WebElement> getElements(String elementName) {
        List<WebElement> elements = null;

        if (elementName.equals(CONTEXT_TABLE_RESULT_ROW)) {
            elements = contextTable.findElements(By.xpath("./tbody/tr"));
        }
        return elements;
    }

    public List<WebElement> getElements(String elementName, int index) {
        List<WebElement> elements = null;
        return elements;
    }

    public List<WebElement> elementsContextTableResultRow() {
        return returnElements(CONTEXT_TABLE_RESULT_ROW);
    }

}
