package com.lumesse.tutorial.join;

import com.lumesse.tutorial.dao.EmployeeDAO;
import com.lumesse.tutorial.dao.criteria.impl.CriteriaEmployeeDAOImpl;

public class CriteriaJoinUsageIntegrationTest extends JoinUsageIntegrationTest {

    @Override
    protected EmployeeDAO getDao() {
        return new CriteriaEmployeeDAOImpl(em);
    }

}
