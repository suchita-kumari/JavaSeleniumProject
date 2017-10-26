package com.quantumretail.qi.keywords;

import com.quantumretail.selenium.GenericKeywords;
import com.quantumretail.qi.pagefactory.ProductSearchFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bhagya on 15-06-2017.
 */
public class ProductSearch extends GenericKeywords{
    private static ProductSearchFactory productSearch;

    public void selectProduct(String productText)throws Throwable{
        enterProductToSearch(productText);
        selectProductFromSearchResult(productText);
    }

    public void resetSearch(){
        clickWithJS(productSearch.reset);
        productSearch.reset.click();
    }

    public void enterProductToSearch(String product) throws Throwable {
        int countA = 1;
        String character;
        int countB = 1;
        while (countA <= 10) {
            countA++;

            performKeysAction(productSearch.elementContextSearchField(), "click, right arrow, backspace * 50");


            for (int i = 0; i < product.length(); i++) {
                character = product.substring(i, i + 1);
                if (character.equals(" ")) {
                    performKeysAction(productSearch.elementContextSearchField(), "space");
                } else
                    performKeysAction(productSearch.elementContextSearchField(), "enter text: " + character);
            }
            try {
                while (!productSearch.elementContextSearchField().getAttribute("value").equals(product) && countB <= 40) {
                    countB++;
                }
                break;
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
    }

    public static void selectProductFromSearchResult(String product) throws Throwable {
        List<WebElement> elementsContextTableResultRow;
        int index;
        boolean productFound = false;

        int count = 1;
        while (count <= 30) {
            count++;
            try {
                elementsContextTableResultRow = productSearch.elementsContextTableResultRow();

                String elementContextTableResultRowData;
                for (WebElement element : elementsContextTableResultRow) {
                    index = elementsContextTableResultRow.indexOf(element) + 1;
                    elementContextTableResultRowData = productSearch.elementContextTableResultRowData(index).getText();
                    if (elementContextTableResultRowData.contains(product)/*elementContextTableResultRowData.equals(product)*/) { //using contains instead of equal, so only require partial match for cases where gherkin passes in only the SKU number and not entire product string. '12805205' vs '12805205: SHORT SLEEVE: BLUE: SMALL'
                        productSearch.elementContextTableResultRowData(index).click();
                        productFound = true;
                        break;
                    }
                }

                if (productFound) {
                    break;
                } else continue;

            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
    }

    public void selectRequiredProduct(String productFullHierarchy) throws InterruptedException {

        String products[] = productFullHierarchy.split(";");
        int i=1;
        for (String level:products) {
            WebElement productElement=GenericKeywords.getDriver().findElement(By.xpath("//a[text()=\"" + level + "\"]/ancestor::li[1]"));
            if(i!=products.length) {
                while (productElement.getAttribute("class").contains("jstree-closed")) {
                    productElement.findElement(By.tagName("ins")).click();
                }
            }else {
                System.out.println("p_id"+productElement.findElement(By.tagName("a")).getText());
                productElement.findElement(By.tagName("a")).click();
            }
            i++;
           /* boolean isClosed=true;
                String productStatus=productElement.getAttribute("class");
                if (productStatus.contains("jstree-open") || productStatus.contains("jstree-leaf")) {
                    isClosed =false;
                } else {
                    if(i!=products.length) {
                        productElement.findElement(By.tagName("ins")).click();
                    }else{
                        System.out.println("p_id"+productElement.findElement(By.tagName("a")).getText());
                        productElement.findElement(By.tagName("a")).click();
                    }
                }
            }*/

            //String PRODUCT_SEARCH_PATH_EXPAND = "//a[text()=\"" + level + "\"]/ancestor::li[1]/ins";
        }
    }

    public void init() {
        productSearch = PageFactory.initElements(GenericKeywords.getDriver(), ProductSearchFactory.class);
    }
}