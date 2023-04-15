package com.envision.automation.core;

import com.envision.automation.utils.FakeDataGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DataProviderUtils {


    //step 1: creating data provider method
    @DataProvider(name = "loginDataProvider")
    public static Object[][] provideMultipleDataSet() {
        Object[][] obj = new Object[][]{{"Admin", "admin123"}, {"Admin123", "admin123"}};
        return obj;
    }

    @DataProvider(name = "fakeLoginDataProvider")
    public static Object[][] fakeLoginDataProvider(Method method) {
        String count =method.getAnnotation(DataProviderArgs.class).value();
        int counter = Integer.parseInt(count);
        Object[][] obj = new Object[counter][2];
        for(int i=0;i<obj.length;i++){
            obj[i][0] = new FakeDataGenerator().getUsername();
            obj[i][1] = new FakeDataGenerator().getPassword();
        }
        return obj;
    }

    @DataProvider(name = "fakeRegistrationDataProvider")
    public static Object[][] fakeRegistrationDataProvider(Method method) {
        String count =method.getAnnotation(DataProviderArgs.class).value();
        int counter = Integer.parseInt(count);
        Object[][] obj = new Object[counter][7];
        for(int i=0;i<obj.length;i++){
            obj[i][0] = new FakeDataGenerator().getUsername();
            obj[i][1] = new FakeDataGenerator().getPassword();
        }
        return obj;
    }



    @DataProvider(name = "jsonDataProvider")
    public static Object[][] fetchTestCaseData(Method method) throws IOException, ParseException {
        String methodName = method.getName();
        String testDataName =method.getAnnotation(DataProviderArgs.class).value().split("=")[0];//loginTestData
        String testDataFields =method.getAnnotation(DataProviderArgs.class).value().split("=")[1]; //username,password

        String loginDataFields = testDataFields;
        String loginDataArr[] = loginDataFields.split(",");
        File jsonFile = new File(System.getProperty("user.dir")
                + File.separator +
                "src/test/resources/testdata/testData-"+System.getProperty("envName")+".json");
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(jsonFile);
        Object obj = parser.parse(fr);
        System.out.println(obj);
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);

        JSONArray jsonArray = (JSONArray) jsonObject.get(testDataName);
        List<List<String>> allRows = new ArrayList<List<String>>();

        for (int i = 0; i < jsonArray.size(); i++) {
            List<String> allColumnsInRow = new ArrayList<String>();
            JSONObject testCaseObj = (JSONObject) jsonArray.get(i);
            for (int j = 0; j < loginDataArr.length; j++) {
                allColumnsInRow.add(testCaseObj.get(loginDataArr[j]).toString());//admin123, admin123, renu
            }
            allRows.add(allColumnsInRow); //[[admin, admin123, tarun sachdeva],[admin123,admin123,renu]]
        }
        System.out.println(allRows);
        Object[][] dataObj = new Object[allRows.size()][allRows.get(0).size()];
        int index = 0;
        for (List<String> list : allRows) {
            for (int i = 0; i < list.size(); i++) {
                dataObj[index][i] = list.get(i);
            }
            index++;
        }
        return dataObj;
    }

    public static void main(String[] args) throws IOException, ParseException {
        String loginDataFields = "username,password,accountName,phoneNumber";
        String loginDataArr[] = loginDataFields.split(",");
        File jsonFile = new File(System.getProperty("user.dir") + File.separator + "src/test/resources/testdata/testData.json");
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(jsonFile);
        Object obj = parser.parse(fr);
        System.out.println(obj);
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println(jsonObject);

        JSONArray jsonArray = (JSONArray) jsonObject.get("testPositiveLoginFlow");
        List<List<String>> allRows = new ArrayList<List<String>>();

        for (int i = 0; i < jsonArray.size(); i++) {
            List<String> allColumnsInRow = new ArrayList<String>();
            JSONObject testCaseObj = (JSONObject) jsonArray.get(i);
            for (int j = 0; j < loginDataArr.length; j++) {
                allColumnsInRow.add(testCaseObj.get(loginDataArr[j]).toString());//admin123, admin123, renu
            }
            allRows.add(allColumnsInRow); //[[admin, admin123, tarun sachdeva],[admin123,admin123,renu]]
        }
        System.out.println(allRows);
        Object[][] dataObj = new Object[allRows.size()][allRows.get(0).size()];

        for (List<String> list : allRows) {
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                dataObj[index][i] = list.get(i);
            }
            index++;
        }


    }

}
