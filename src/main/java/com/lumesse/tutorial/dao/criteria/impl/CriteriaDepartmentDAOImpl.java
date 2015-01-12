package com.lumesse.tutorial.dao.criteria.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.lumesse.tutorial.dao.DepartmentDAO;
import com.lumesse.tutorial.dao.impl.GenericDaoAdapter;
import com.lumesse.tutorial.domain.Department;
import com.lumesse.tutorial.domain.Employee;
import com.lumesse.tutorial.domain.Employee_;

public class CriteriaDepartmentDAOImpl extends GenericDaoAdapter<Department> implements DepartmentDAO {

    private EntityManager em;

    public CriteriaDepartmentDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Department> findDepartmentsWithNumOfEmployees(Long minNumOfEmployees) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Department> query = cb.createQuery(Department.class);
        Root<Department> dptRoot = query.from(Department.class);

        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Employee> empRoot = subquery.from(Employee.class);

        subquery.select(cb.count(empRoot));
        subquery.where(cb.equal(empRoot.get(Employee_.department), dptRoot));

        query.where(cb.ge(subquery, minNumOfEmployees));
        return em.createQuery(query).getResultList();
    }

}
