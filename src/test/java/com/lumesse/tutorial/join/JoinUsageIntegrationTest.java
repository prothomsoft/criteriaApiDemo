package com.lumesse.tutorial.join;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.lumesse.tutorial.BaseIntegrationTestCase;
import com.lumesse.tutorial.dao.EmployeeDAO;
import com.lumesse.tutorial.domain.Employee;

public abstract class JoinUsageIntegrationTest extends
        BaseIntegrationTestCase {

    private EmployeeDAO employeeDAO;

    @Override
    public void setUp() {
        super.setUp();

        this.employeeDAO = getDao();
    }

    @Test
    public void shouldFindEntityByEmbededField() {
        // given
        String city = "Dziki";

        // when
        List<Employee> employees = employeeDAO.findByCity(city);

        // then
        assertEquals(1, employees.size());
        assertEquals("Sebastian Pisarski", employees.get(0).getName());
    }

    @Test
    public void shouldFindEmployeesByOfficeName() {
        // given
        String officeName = "Cracow";

        // when
        List<Employee> employees = employeeDAO.findByOffice(officeName);

        // then
        assertEquals(8, employees.size());
    }

    @Test
    public void shouldFindEmployeesAndTasksWithInnerJoin() {
        // when
        List<Employee> employees = employeeDAO.getEmployeesAndTasksWithInnerJoin();

        // then
        assertEquals(7, employees.size());
        checkIfTasksAreFetched(employees);
    }

    @Test
    public void shouldFindEmployeesAndTasksWithLeftJoin() {
        // when
        List<Employee> employees = employeeDAO.getEmployeesAndTasksWithLeftJoin();

        // then
        assertEquals(8, employees.size());
        checkIfTasksAreFetched(employees);
    }

    private void checkIfTasksAreFetched(List<Employee> employees) {
        for (Employee emp : employees) {
            em.detach(emp);

            if (CollectionUtils.isNotEmpty(emp.getTasks())) {
                emp.getTasks().size();
                return;
            }
        }

        fail("There is no employee with any task");
    }

    protected abstract EmployeeDAO getDao();
}
