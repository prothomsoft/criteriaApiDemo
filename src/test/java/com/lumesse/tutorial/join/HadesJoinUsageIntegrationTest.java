package com.lumesse.tutorial.join;

import com.lumesse.tutorial.dao.EmployeeDAO;

public class HadesJoinUsageIntegrationTest extends JoinUsageIntegrationTest {

    @Override
    protected EmployeeDAO getDao() {
        return getDao(EmployeeDAO.class);
    }

}
