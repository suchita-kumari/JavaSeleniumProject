package com.quantumretail.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class GenerateTestConfig {
    public void createTestaCaseConfig() {
        try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory
                        .newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element suite = doc.createElement("suite");
                doc.appendChild(suite);

                Attr suiteName = doc.createAttribute("name");
                suiteName.setValue("QAutomatiom");
                suite.setAttributeNode(suiteName);


                Element test = doc.createElement("test");
                suite.appendChild(test);

                // set attribute to staff element
                Attr testname = doc.createAttribute("name");
                testname.setValue("SmokeTest");
                test.setAttributeNode(testname);

                // Read Classes as child element using tageName
                Element classes = doc.createElement("classes");
                test.appendChild(classes);
                Element clas = doc.createElement("class");

                Attr clasName = doc.createAttribute("name");
                clasName.setValue("com.testcases.ProductSetup");
                clas.setAttributeNode(clasName);

                Element methods = doc.createElement("methods");

                Map<String, String> tests = getTestCases();
                int noOfTests = tests.size();
                Set<String> tsts = new HashSet<String>();
                tsts = tests.keySet();
                for (String tst : tsts) {
                    if (tests.get(tst).equalsIgnoreCase("yes")) {
                        Element incl = doc.createElement("include");
                        Attr methodName = doc.createAttribute("name");
                        methodName.setValue(tst);
                        incl.setAttributeNode(methodName);
                        methods.appendChild(incl);
                    } else {
                        Element incl = doc.createElement("exclude");
                        Attr methodName = doc.createAttribute("name");
                        methodName.setValue(tst);
                        incl.setAttributeNode(methodName);
                        methods.appendChild(incl);
                    }
                    classes.appendChild(clas);
                    clas.appendChild(methods);
                    test.appendChild(classes);
                }

                 // write the content into xml fele
                TransformerFactory transformerFactory = TransformerFactory
                        .newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(
                        doc);

                StreamResult result = new StreamResult(new File(System
                        .getProperty("user.dir")
                        + "/TestNG.xml"));
                transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static String xmlFile() {
        BufferedReader br = null;
        String XML;
        try {
            br = new BufferedReader(new FileReader(new File(System
                    .getProperty("user.dir")
                    + "\\TestNG.xml")));
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());

            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        XML = sb.toString();
        return XML;
    }

    public static String getMethodName(final int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[ste.length - 1 - depth].getMethodName();
    }

    public Map<String, String> getTestCases() {
        String csvFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testdata.csv";
        String line = "";
        String cvsSplitBy = ",";
        Map<String, String> testCases = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (!line.contains("TestCase_ID,Execution_Flag")) {
                    String[] tcData = line.split(cvsSplitBy);
                    testCases.put(tcData[0], tcData[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCases;
    }


}
