package com.lumesse.tutorial.basic;

import com.lumesse.tutorial.dao.SimpleEntityDAO;
import com.lumesse.tutorial.dao.criteria.impl.CriteriaSimpleEntityDAOImpl;

public class CriteriaBasicDAOUsageIntegrationTest extends BasicDAOUsageIntegrationTest {

    @Override
    protected SimpleEntityDAO getDAO() {
        return new CriteriaSimpleEntityDAOImpl(em);
    }

}
