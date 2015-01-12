package com.lumesse.tutorial.dao.criteria.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.lumesse.tutorial.dao.EmployeeDAO;
import com.lumesse.tutorial.dao.impl.GenericDaoAdapter;
import com.lumesse.tutorial.domain.Address_;
import com.lumesse.tutorial.domain.Department_;
import com.lumesse.tutorial.domain.Employee;
import com.lumesse.tutorial.domain.Employee_;
import com.lumesse.tutorial.domain.Office_;

public class CriteriaEmployeeDAOImpl extends GenericDaoAdapter<Employee>
        implements EmployeeDAO {

    private final EntityManager em;

    public CriteriaEmployeeDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findByCity(String city) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);

        ParameterExpression<String> cityParam = cb.parameter(String.class, "city");
        query.where(cb.equal(root.get(Employee_.address).get(Address_.city), cityParam));
        return em.createQuery(query).setParameter(cityParam.getName(), city).getResultList();
    }

    @Override
    public List<Employee> findByOffice(String officeName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);

        ParameterExpression<String> nameParam = cb.parameter(String.class, "name");
        //query.where(cb.equal(root.get(Employee_.department).get(Department_.office).get(Office_.name), nameParam));
        query.where(cb.equal(root.join(Employee_.department).join(Department_.office).get(Office_.name), nameParam));
        return em.createQuery(query).setParameter(nameParam.getName(), officeName).getResultList();
    }

    @Override
    public List<Employee> getEmployeesAndTasksWithInnerJoin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        //root.join(Employee_.tasks);
        root.fetch(Employee_.tasks);
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Employee> getEmployeesAndTasksWithLeftJoin() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        query.distinct(true);
        Root<Employee> root = query.from(Employee.class);
        root.fetch(Employee_.tasks, JoinType.LEFT);
        return em.createQuery(query).getResultList();
    }

}
