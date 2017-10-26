package com.quantumretail.qi.keywords;


import com.quantumretail.qi.pagefactory.HqFactory;
import com.quantumretail.selenium.GenericKeywords;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Bhagya on 20-06-2017.
 */
public class HqKeywords extends GenericKeywords {
    private static HqFactory hqPage;

    public void verifyExistingDiary(Date date, String category, String itemDescription, String locationDescription, String DiaryEntry) {
        boolean isExist = isDiaryExist(date, category, itemDescription, locationDescription, DiaryEntry);
        Assert.assertTrue(isExist);
    }

    public boolean isDiaryExist(Date date, String category, String itemDescription, String locationDescription, String DiaryEntry) {
        boolean isExist =false;
        String diaryDate;
        if (date == null) {
            diaryDate = "*";
        } else {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            diaryDate = dateFormat.format(date);
        }
        if (category == null || category == "") {
            category = "*";
        }
        if (itemDescription == null || itemDescription == "") {
            itemDescription = "*";
        }
        if (locationDescription == null || locationDescription == "") {
            locationDescription = "*";
        }
        if (DiaryEntry == null || DiaryEntry == "") {
            DiaryEntry = "*";
        }
        String diaryEntry = (diaryDate + category + itemDescription + locationDescription+DiaryEntry).replace(" ", "");
        System.out.println("dEntry: "+diaryEntry);
        List<String> diaries = getAllDiaries();
        for (String diary : diaries) {
            System.out.println("dE: "+diary);
            isExist = isTextMatching(diary, diaryEntry);
            if(isExist==true){
                break;
            }
        }
        return isExist;
    }

    public boolean isTextMatching(String text, String pattern) {
        return text.matches(pattern.replace("?", ".?").replace("*", ".*?"));
    }

    public int getCountOfDiaries(){
        return getAllDiaries().size();
    }

    public List<String> getAllDiaries() {
        waitForDiaryDataGrid();
        List<String> diaries = new ArrayList<>();
        List<WebElement> rows = hqPage.elementHQDiaryTRows();
        for (int i = 1; i < rows.size()-1; i++) {
            String rowData = "";
            String itemDes = getElementDiaryEntryCell(i, "item description").getAttribute("value");
            String locDes = getElementDiaryEntryCell(i, "location description").getAttribute("value");
            String diary = getElementDiaryEntryCell(i, "diary entry").getText();
            rowData = itemDes + locDes + diary;
            rowData = rowData.replace(" ", "");
            diaries.add(rowData);
        }
        return diaries;
    }

    public WebElement getElementDiaryEntryCell(int rowIndex, String columnName) {
        int columnIndex = 0;

        if (columnName.equalsIgnoreCase("item description")) {
            columnIndex = 1;
        } else if (columnName.equalsIgnoreCase("location description")) {
            columnIndex = 2;
        } else if (columnName.equalsIgnoreCase("diary entry")) {
            columnIndex = 3;
        } else {
            Assert.assertFalse(true, "INVALID ARGUMENT: '" + columnName + "' IS INVALID ARGUMENT FOR 'columnName'. PLEASE USE: 'item description' || 'location description' || 'diary entry'");
        }

        return hqPage.elementDiaryEntryCell(rowIndex, columnIndex);
    }

    public void waitForDiaryDataGrid(){
        waitForElement(hqPage.elementHQDiaryTbody());
    }

    public void init() {
        hqPage = PageFactory.initElements(GenericKeywords.getDriver(), HqFactory.class);
    }
}
