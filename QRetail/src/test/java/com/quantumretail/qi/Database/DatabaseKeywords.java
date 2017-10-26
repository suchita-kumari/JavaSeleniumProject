package com.quantumretail.qi.Database;

import com.quantumretail.utilities.PropertyLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Suchita  on 13-07-2017.
 */
public class DatabaseKeywords {

    public String getQueryResult(String query) {
        String result = "";
        Connection conn = null;
        PropertyLoader propertyLoader = new PropertyLoader();
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(propertyLoader.getDbConnectionUrl(), propertyLoader.getDbUserName(), propertyLoader.getDbPassword());
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString(1);
            }
            rs.close();
            ps.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return result;
    }

    public HashMap<String, String> getQueryResultInMap(String query) {
        String result = "";
        Connection conn = null;
        String key;
        String value;
        PropertyLoader propertyLoader = new PropertyLoader();
        HashMap<String, String> rowData= new HashMap<>();
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(propertyLoader.getDbConnectionUrl(), propertyLoader.getDbUserName(), propertyLoader.getDbPassword());
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();

            for (int i = 0; i < columns; i++) {
                rs = ps.executeQuery();
                key = rsmd.getColumnName(i);
                value= rs.getString(key);
                while (rs.next()) {
                    rowData.put(key, value);
                }
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return rowData;
    }

    public HashMap<String, ArrayList<String>> getQueryResultAsHash(String query) {

        PropertyLoader propertyLoader = new PropertyLoader();
        String db=propertyLoader.getDbConnectionUrl();
        String result = "";
        Connection conn = null;
        final HashMap<String, ArrayList<String>> tableData = new HashMap<String, ArrayList<String>>();
        String key;
        try {

            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(propertyLoader.getDbConnectionUrl(), propertyLoader.getDbUserName(), propertyLoader.getDbPassword());
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();

            for (int i = 0; i < columns; i++) {
                rs = ps.executeQuery();
                ArrayList valueList = new ArrayList<String>();
                key = "column" + (i + 1);
                int rowCount = 1;
                while (rs.next()) {
                    if (rowCount == 1) {
                        valueList.clear();
                    }
                    valueList.add(rs.getString(i + 1));
                    rowCount++;
                }
                tableData.put(key, valueList);
            }
            rs.close();
            ps.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return tableData;
    }

    public HashMap<Integer, ArrayList<HashMap<String, String>>> getAllRows(String query) {

        PropertyLoader propertyLoader = new PropertyLoader();
        String db=propertyLoader.getDbConnectionUrl();
        String result = "";
        Connection conn = null;
        final HashMap<Integer, ArrayList<HashMap<String, String>>> tableData = new HashMap<Integer, ArrayList<HashMap<String, String>>>();

        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            conn = DriverManager.getConnection(propertyLoader.getDbConnectionUrl(), propertyLoader.getDbUserName(), propertyLoader.getDbPassword());
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList rows = new ArrayList<HashMap<String,String>>();
            int rowCount = 1;
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();
            for (int i = 1; i < columns; i++) {

                HashMap<String, String> rowData= new HashMap<String, String>();

                String key = rsmd.getColumnName(i);
                String value = rs.getString(key);
                rowData.put(key, value);
                rows.add(rowData);
                }
                tableData.put(rowCount, rows);
                rowCount++;
            }
            rs.close();
            ps.close();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return tableData;
    }

    public static void main(String[] args) {
        String qry="select product_id, location_id, EIB_ON_HAND_QTY from QR_PRODUCT_LOCATION where product_id='20109116' and location_id like '%S211%'";
        DatabaseKeywords dk=new DatabaseKeywords();

        dk.getQueryResult(qry);

        /*HashMap<String, String> data = dk.getQueryResultInMap(qry);
        System.out.println(data);
        for (String k: data.keySet()) {
            System.out.println(k);
        }*/
/*
        HashMap<Integer, ArrayList<HashMap<String,String>>> tb= dk.getAllRows(qry);

        for (int row: tb.keySet()) {

            ArrayList<HashMap<String,String>> rowData= tb.get(row);
            for (HashMap<String,String> r :rowData) {
                for (String keyd: r.keySet()) {
                    System.out.print(keyd+" = "+r.get(keyd));
                    System.out.println(", ");
                }
            }
            System.out.println("next "+row);
        }

        HashMap<String, ArrayList<String>> data= dk.getQueryResultAsHash(qry);

        for (String key: data.keySet()) {
            System.out.println(key+" --> "+data.get(key));
        }*/

    }

}
