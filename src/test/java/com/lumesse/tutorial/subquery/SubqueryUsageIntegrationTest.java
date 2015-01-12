package com.lumesse.tutorial.subquery;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

import java.util.List;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.lumesse.tutorial.BaseIntegrationTestCase;
import com.lumesse.tutorial.dao.DepartmentDAO;
import com.lumesse.tutorial.domain.Department;

@RunWith(JUnitParamsRunner.class)
public abstract class SubqueryUsageIntegrationTest extends BaseIntegrationTestCase {

    private DepartmentDAO departmentDAO;

    @Override
    public void setUp() {
        super.setUp();

        this.departmentDAO = getDao();
    }

    @Test
    @Parameters(method = "getParametersForShouldFindDepartmentsWithMinNumberOfEmployees")
    public void shouldFindDepartmentsWithMinNumberOfEmployees(Long minNumberOfEmployees, int expectedNumOfDepartments) {
        // when
        List<Department> departments = departmentDAO.findDepartmentsWithNumOfEmployees(minNumberOfEmployees);

        // then
        assertEquals(expectedNumOfDepartments, departments.size());
    }

    @SuppressWarnings("unused")
    private Object[] getParametersForShouldFindDepartmentsWithMinNumberOfEmployees() {
        return $($(0L, 6), $(-5L, 6), $(6L, 1), $(10L, 0), $(1L, 2));
    }

    protected abstract DepartmentDAO getDao();
}
