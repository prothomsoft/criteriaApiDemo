package com.lumesse.tutorial;

public abstract class BaseIntegrationTestCase extends com.lumesse.it.base.BaseIntegrationTestCase {

    @Override
    public void setUp() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }
}
