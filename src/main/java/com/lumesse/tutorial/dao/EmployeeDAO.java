package com.lumesse.tutorial.dao;

import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Param;
import org.synyx.hades.dao.Query;

import com.lumesse.tutorial.domain.Employee;

public interface EmployeeDAO extends GenericDao<Employee, Long> {

    @Query(value = "from Employee where address.city = :city")
    List<Employee> findByCity(@Param("city") String city);

    @Query(value = "select emp from Employee emp " +
            "join emp.department dpt " +
            "join dpt.office office " +
            "where office.name = :officeName")
    List<Employee> findByOffice(@Param("officeName") String officeName);

    @Query(value = "select emp from Employee emp " +
            "join fetch emp.tasks task")
    List<Employee> getEmployeesAndTasksWithInnerJoin();

    @Query(value = "select distinct emp from Employee emp " +
            "left join fetch emp.tasks task")
    List<Employee> getEmployeesAndTasksWithLeftJoin();

}
