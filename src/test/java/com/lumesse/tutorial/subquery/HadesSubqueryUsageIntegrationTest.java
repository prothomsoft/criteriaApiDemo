package com.lumesse.tutorial.subquery;

import com.lumesse.tutorial.dao.DepartmentDAO;

public class HadesSubqueryUsageIntegrationTest extends SubqueryUsageIntegrationTest {

    @Override
    protected DepartmentDAO getDao() {
        return getDao(DepartmentDAO.class);
    }

}
