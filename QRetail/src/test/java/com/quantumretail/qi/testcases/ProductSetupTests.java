package com.quantumretail.qi.testcases;

import com.quantumretail.qi.keywords.*;
import com.quantumretail.selenium.GenericKeywords;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Bhagya on 14-06-2017.
 */
public class ProductSetupTests extends ProductSetupKeywords {
    LoginKeywords loginKeywords;
    ProductSearch productSearch;
    PrimaryTabs primaryTabsFunctions;
    DiaryEntryKeywords diary;
    HqKeywords hqKeywords;

    @BeforeTest
    public void set() {
        loginKeywords = new LoginKeywords();
        setup();
        CommonFunctions cm = new CommonFunctions();
        cm.initializePageFactory();
        loginKeywords.loginQ();
        productSearch = new ProductSearch();
        productSearch.init();
        diary = new DiaryEntryKeywords();
        diary.init();
        primaryTabsFunctions = new PrimaryTabs();
        primaryTabsFunctions.init();
        hqKeywords =new HqKeywords();
        hqKeywords.init();
    }

    @Test
    public void TC_PS_0001() throws Throwable {
        int intialCount= hqKeywords.getCountOfDiaries();
        System.out.println("intial"+intialCount);
        primaryTabsFunctions.selectProductSetupScreen();
        productSearch.selectProduct("12805205: SHORT SLEEVE: BLUE: SMALL");
        diary.addNewManualDiaryEntry("new data Entry");
        primaryTabsFunctions.selectHqScreen();
        hqKeywords.waitForDiaryDataGrid();
        Thread.sleep(1000);
        hqKeywords.verifyExistingDiary(null, null, "","","new data Entry");
        int countAfterAdding= hqKeywords.getCountOfDiaries();
        System.out.println("intialCount: "+intialCount +", countAfterAdding:"+countAfterAdding);
    }

    @AfterTest
    public void testEnd() {
        GenericKeywords.getDriver().quit();
    }
}
