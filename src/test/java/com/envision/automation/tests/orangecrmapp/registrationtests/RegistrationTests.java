package com.envision.automation.tests.orangecrmapp.registrationtests;

import com.envision.application.orangecrm.pages.HomePage;
import com.envision.application.orangecrm.pages.LoginPage;
import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import com.envision.automation.core.TestAssert;
import com.envision.automation.tests.orangecrmapp.BaseTest;
import com.envision.automation.utils.MySqlDbUtils;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class RegistrationTests extends BaseTest {

    @Test
    public void fetchEmployeeData() throws Exception {
        List<List<String>> employeeData =sqlDbUtils.fetchEmployeeData(rs);
        System.out.println(employeeData);

        ///registrations steps with selenium methods....
    }

}
