package com.lumesse.tutorial.dao;

import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Param;
import org.synyx.hades.dao.Query;

import com.lumesse.tutorial.domain.Department;

public interface DepartmentDAO extends GenericDao<Department, Long> {

    @Query(value = "from Department dpt " +
            "where (select count(emp) from Employee emp where emp.department = dpt) >= :numOfEmpl")
    List<Department> findDepartmentsWithNumOfEmployees(@Param("numOfEmpl") Long minNumOfEmployees);
}
