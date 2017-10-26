package com.quantumretail.qi.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.util.List;

/**
 * Created by Bhagya on 20-06-2017.
 */
public class DiaryEntryPopupFactory {

        public DiaryEntryPopupFactory(WebDriver driver) {
            ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 2);
            PageFactory.initElements(finder, this);
        }

        final String DIARY_MANUAL_ENTRY_TXT = "input[id='diary-activity-entry']";
        final String DIARY_ADD_BUTTON = "div[class='popup-header'] input[value='Add']";
        final String DIARY_ENTRY_DDL = "select[id='diary-activity-select']";
        final String DIARY_ENTRY_IN_GRID = "table[id='diary-activity-table'] tbody tr td[class*='popup_entry_col']";
        final String DIARY_BUTTON = "div[class='align-right page_controls'] input[value='Diary']";
        final String CLOSE_BUTTON="close-button";
        @FindBy(how = How.CSS, using = DIARY_ENTRY_DDL)
        public WebElement ddlDiaryEntriy;

        @FindBy(how = How.CSS, using = DIARY_MANUAL_ENTRY_TXT)
        public WebElement txtManualEntry;

        @FindBy(how = How.CSS, using = DIARY_ADD_BUTTON)
        public WebElement btnDiaryAdd;

        @FindBy(how = How.CSS, using = DIARY_ENTRY_IN_GRID)
        public List<WebElement> lblDiaryEntry;

        @FindBy(how = How.CSS, using = DIARY_BUTTON)
        public WebElement btnDiary;

        @FindBy(how = How.CLASS_NAME, using = CLOSE_BUTTON)
        public WebElement btnClose;
}
