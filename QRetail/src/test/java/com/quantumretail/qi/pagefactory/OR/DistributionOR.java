package com.quantumretail.qi.pagefactory.OR;

/**
 * Created by Bhagya on 17-07-2017.
 */
public interface DistributionOR {
    // DC
    String DISTRIBUTION_DC = "li[id='dc-tab'] > a";
    String DISTRIBUTION_FUTURE_DC = "li[id='future-dc-tab'] > a";
    String DC_METRICS_TABLE = "table[id='dc-metrics-table'] tbody tr";
    String DC_FUTURE_METRICS_TABLE = "//*[@id='future-dc-inner-table']/table/tbody/tr/td";
    String DC_NEXT_PAGE_BUTTON_ID = "//table[@id=\"future-dc-table\"]/thead/tr/th/div/input[@id='next-page-button']";
    String DC_TABLE_HEADERS = "//*[@id='dc-metrics-table']/thead/tr[3]/th";
    String DC_FUTURE_HEADERS = ".//*[@class='future_dc_table synchronized_table']/thead/tr[1]/th";
    String DC_TABLE_FOOTER = "//*[@id='dc-metrics-table']/tfoot/tr/td";
    String AGGEREGATED_DC_METRIC = "//*[@id='dc-metrics-table']/tfoot/tr/td";
    String SAVE_DISTRIBUTION_DC_TABLE = "dcSave";
    String ALL_PUSH_QUANTTTY_DC = "allPushUnits";
    String TOTAL_STORE_STOCK = ".//*[@id='dc-metrics-table']/thead/tr[2]/th";
    String DC_FUTURE_METRICS_COUNT = "table[id='future-dc-table'] thead tr th div span";
    String DC_FUTURE_RECEIPTS_PO = "//*[@class='future_dc_table synchronized_table']/thead/tr[2]/th";
    String DC_FUTURE_RECEIPTS_PO_FOR_STYLE = "//*[@class='future_dc_table synchronized_table']/thead/tr[3]/th";

    //Store
    String DISTRIBUTION_TAB = "#distribution-setup-title";
    // String DISTRIBUTION_TAB="//a[text()='Distribution']";
    String STORE_FILTER_LOCATION = "distribution-filter-location-button";
    String STORE_FILTER_TEXT_BOX = "table[id='store-metrics-table'] div[class='top-buttons'] input[class='inline-number-input']";
    String FILTER_LOCATION_WINDOW = "distribution-location-title";
    String SEARCHED_PRODUCT = "//*[@id='context-table']/tbody/tr/td";
    String CLEAR_SEARCH = "input[value='Clear Search']";
    String RESET_PRODUCT_SEARCH = ".popup-header>input[value='Reset']";
    String STORE_LIST_VIEW_BUTTON = "#store-list-button";
    String DISTRIBUTION_LOCATION_GRAPH = "#distribution-location-graph";

    String STORE_METRICS_TABLE = "table[id='store-metrics-table'] tbody tr";
    String STORE_METRICS_COUNT = "table[id='store-metrics-table'] thead tr th div span";

    String AGGEREGATED_METRIC = "//*[@id='store-metrics-table']/tfoot/tr/td";
    String SELECTED_LOCATION = "tfoot tr td:nth-child(1)";
    String AGGREGATE_CURRENT_NEED = "tfoot tr td:nth-child(2)";
    String AGGEREGATE_RDQ = "tfoot tr td:nth-child(3)";
    String AGGEREGATE_PUSH_QTY = "tfoot tr td:nth-child(4)";
    String AGGEREGATE_OH_QTY = "tfoot tr td:nth-child(6)";
    String AGGEREGATE_FORECASTED_INV = "tfoot tr td:nth-child(7)";
    String AGGEREGATE_EOH = "tfoot tr td:nth-child(8)";
    String AGGEREGATE_IN_TRANSIT = "tfoot tr td:nth-child(9)";
    String AGGEREGATE_MIN_STOCK = "tfoot tr td:nth-child(10)";
    String AGGEREGATE_MAX_STOCK = "tfoot tr td:nth-child(12)";
    String AGGEREGATE_AVG_WEEKLY_SALES = "tfoot tr td:nth-child(13)";
    String NEXT_PAGE_BUTTON_ID = "next-page-button";
    Integer defaultTimeoutSeconds = 30;
    String QPAGE = "//*[@id='header-title']/td";
    //   String TABLE_HEADERS="[id='store-metrics-table']  thead tr:nth-child(3) th";
    String DISTRIBUTION_TABLE_HEADERS = "//*[@id='store-metrics-table']/thead/tr[3]/th";
    String DISTRIBUTION_TABLE_FOOTER = "//*[@id='store-metrics-table']/tfoot/tr/td";
    String SAVE_DISTRIBUTION_TABLE = "storeListSave";
    String SAVE_SUCESS_MESSAGE = "message-span";
}
