package com.lumesse.tutorial.dao.criteria.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.lumesse.tutorial.dao.SimpleEntityDAO;
import com.lumesse.tutorial.dao.impl.GenericDaoAdapter;
import com.lumesse.tutorial.domain.SimpleEntity;
import com.lumesse.tutorial.domain.SimpleEntity_;
import com.lumesse.tutorial.dto.KeyValueEntry;
import com.lumesse.tutorial.dto.SimpleEntityDto;

public class CriteriaSimpleEntityDAOImpl extends GenericDaoAdapter<SimpleEntity> implements SimpleEntityDAO {

    private final EntityManager em;

    public CriteriaSimpleEntityDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<SimpleEntity> readAll() {
        CriteriaBuilder cb  = em.getCriteriaBuilder();
        CriteriaQuery<SimpleEntity> query = cb.createQuery(SimpleEntity.class);
        query.from(SimpleEntity.class);
        return em.createQuery(query).getResultList();
    }

    @Override
    public SimpleEntity readByPrimaryKey(Long id) {
        CriteriaBuilder cb  = em.getCriteriaBuilder();
        CriteriaQuery<SimpleEntity> query = cb.createQuery(SimpleEntity.class);
        Root<SimpleEntity> root = query.from(SimpleEntity.class);
        query.where(cb.equal(root.get(SimpleEntity_.id), id));
        List<SimpleEntity> results = em.createQuery(query).getResultList();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    public String findSimpleEntityNameById(Long id) {
        CriteriaBuilder cb  = em.getCriteriaBuilder();
        CriteriaQuery<String> query = cb.createQuery(String.class);
        Root<SimpleEntity> root = query.from(SimpleEntity.class);
        query.select(root.get(SimpleEntity_.name));
        query.where(cb.equal(root.get(SimpleEntity_.id), id));
        List<String> results = em.createQuery(query).getResultList();
        return results.size() > 0 ? results.get(0) : null;
    }

    @Override
    public List<SimpleEntityDto> findSimpleEntitiesByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SimpleEntityDto> query = cb.createQuery(SimpleEntityDto.class);
        Root<SimpleEntity> root = query.from(SimpleEntity.class);
        query.select(cb.construct(SimpleEntityDto.class, root.get(SimpleEntity_.id), root.get(SimpleEntity_.name)));

        ParameterExpression<String> nameParam = cb.parameter(String.class, "name");
        query.where(cb.equal(cb.upper(root.get(SimpleEntity_.name)), cb.upper(nameParam)));

        return em.createQuery(query).setParameter(nameParam.getName(), name).getResultList();
    }

    @Override
    public List<SimpleEntity> getAllEntitiesSortedByName() {
        CriteriaBuilder cb  = em.getCriteriaBuilder();
        CriteriaQuery<SimpleEntity> query = cb.createQuery(SimpleEntity.class);
        Root<SimpleEntity> root = query.from(SimpleEntity.class);
        query.orderBy(cb.asc(root.get(SimpleEntity_.name)));
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<KeyValueEntry> findMaxValueOfEachGroup(long minValue) {
        CriteriaBuilder cb  = em.getCriteriaBuilder();
        CriteriaQuery<KeyValueEntry> query = cb.createQuery(KeyValueEntry.class);
        Root<SimpleEntity> root = query.from(SimpleEntity.class);

        Expression<Long> maxValue = cb.max(root.get(SimpleEntity_.numValue));
        query.multiselect(root.get(SimpleEntity_.someValue), maxValue);
        query.where(cb.equal(root.get(SimpleEntity_.name), "group"));
        query.groupBy(root.get(SimpleEntity_.someValue));
        query.having(cb.gt(maxValue, minValue));

        return em.createQuery(query).getResultList();
    }

}
