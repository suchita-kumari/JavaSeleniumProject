package com.quantumretail.qi.keywords;

import com.quantumretail.selenium.GenericKeywords;
import com.quantumretail.qi.pagefactory.DiaryEntryPopupFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Suchita on 20-06-2017.
 */
public class DiaryEntryKeywords extends GenericKeywords {
    private static DiaryEntryPopupFactory diaryEntryPopup;

    public void addNewManualDiaryEntry(String manualEntry){
        clickOnDiaryButton();
        enterDiaryEntry(manualEntry);
        clickAddDiaryEntryButton();
        clickOn(diaryEntryPopup.btnClose);
    }

    public void verifyDiaryIsPresentInGrid(String date, String category, String itemDescription, String locationDescription, String diaryEntry){

    }

    public void isDiaryPresentInGrid(String date, String category, String itemDescription, String locationDescription, String diaryEntry){

    }

    final void clickOnDiaryButton(){
        clickOn(diaryEntryPopup.btnDiary);
    }
    /**
     * Returns the diary entry at specified index
     *
     * @param atIndex : Index to get Diary entry value
     * @return : String
     */
    final public String getDiaryEntry(int atIndex) {

        return diaryEntryPopup.lblDiaryEntry.get(atIndex).getText();
    }

    /**
     * Enters the specified values into diary
     *
     * @param value : data to enter
     */
    final public void enterDiaryEntry(String value) {
        diaryEntryPopup.txtManualEntry.sendKeys(value);
    }

    /**
     * Click on Add Diary Entry
     */
    final public void clickAddDiaryEntryButton() {
        clickOn(diaryEntryPopup.btnDiaryAdd);
    }

    /**
     * Select the specified Diary entry from Diary PopUp
     *
     * @param value : entry value to be selected
     */
    final public void selectDiaryEntry(String value) {

        final Select diarySelect = new Select(diaryEntryPopup.ddlDiaryEntriy);
        diarySelect.selectByValue(value);
    }

    /**
     * Select the Diary entry of specified index from Diary PopUp
     *
     * @param indexValue : entry index to be selected
     */
    final public void selectDiaryEntry(int indexValue) {

        final Select diarySelect = new Select(diaryEntryPopup.ddlDiaryEntriy);
        diarySelect.selectByIndex(indexValue);
    }

    /**
     * Returns the selected Diary entry on Diary popup
     *
     * @return : String
     */
    final public String getSelectedDiaryEntry() {

        final Select diarySelect = new Select(diaryEntryPopup.ddlDiaryEntriy);
        return diarySelect.getFirstSelectedOption().getText();
    }

    final public void clickDiaryEntry(int atIndex) {

        clickOn(diaryEntryPopup.lblDiaryEntry.get(atIndex));

    }

    final public Boolean isDiaryEntryEditable() {

        boolean editable = false;

        diaryEntryPopup.lblDiaryEntry.get(0).sendKeys(Keys.RETURN);
        diaryEntryPopup.lblDiaryEntry.get(0).sendKeys(" ");

        if (diaryEntryPopup.lblDiaryEntry.get(0).getText().trim().isEmpty())
            editable = true;

        return editable;
    }

    public void init() {
        diaryEntryPopup = PageFactory.initElements(GenericKeywords.getDriver(), DiaryEntryPopupFactory.class);
    }
}
