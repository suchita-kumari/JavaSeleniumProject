package com.quantumretail.qi.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by Bhagya on 20-06-2017.
 */
public class HqFactory {
    @FindBy(how = How.ID, using = "hq-diary_wrapper")
    public WebElement hqDiaryWrapper;

    @FindBy(how = How.ID, using = "hq-diary")
    public WebElement hqDiary;

    @FindBy(how = How.ID, using = "q-diary-graph")
    public WebElement qDiaryGraph;

    @FindBy(how = How.ID, using = "diary-graph")
    public WebElement diaryGraph;

    @FindBy(how = How.ID, using = "menu-filter")
    public WebElement menuFilter;

    @FindBy(how = How.ID, using = "filter-summary")
    public WebElement filterSummary;

    public WebElement elementHQDiaryTbody() {
        return hqDiary.findElement(By.xpath(".//tbody"));
    }

    public List<WebElement> elementHQDiaryTRows() {
        return hqDiary.findElements(By.xpath(".//tr"));
    }
    /**
     * @param rowIndex    - index of the diary entry row starting with the very top entry = 1 and so forth
     * @param columnIndex - index of diary entry column - Item Description = 1; Location Description = 2; Diary Entry = 3;
     * @return
     */
    public WebElement elementDiaryEntryCell(int rowIndex, int columnIndex) {
        if (columnIndex != 3) {
            return elementHQDiaryTbody().findElement(By.xpath(".//tr[" + rowIndex + "]/td[" + columnIndex + "]/input"));
        } else {
            return elementHQDiaryTbody().findElement(By.xpath(".//tr[" + rowIndex + "]/td[" + columnIndex + "]"));
        }
    }
}
