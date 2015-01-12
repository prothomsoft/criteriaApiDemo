package com.lumesse.tutorial.subquery;

import com.lumesse.tutorial.dao.DepartmentDAO;
import com.lumesse.tutorial.dao.criteria.impl.CriteriaDepartmentDAOImpl;

public class CriteriaSubqueryUsageIntegrationTest extends SubqueryUsageIntegrationTest {

    @Override
    protected DepartmentDAO getDao() {
        return new CriteriaDepartmentDAOImpl(em);
    }

}
