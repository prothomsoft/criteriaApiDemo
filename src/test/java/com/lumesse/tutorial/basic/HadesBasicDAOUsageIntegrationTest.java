package com.lumesse.tutorial.basic;

import com.lumesse.tutorial.dao.SimpleEntityDAO;

public class HadesBasicDAOUsageIntegrationTest extends BasicDAOUsageIntegrationTest {

    @Override
    protected SimpleEntityDAO getDAO() {
        return getDao(SimpleEntityDAO.class);
    }

}
