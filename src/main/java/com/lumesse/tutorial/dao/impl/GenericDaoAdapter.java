package com.lumesse.tutorial.dao.impl;

import java.util.Collection;
import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.domain.Page;
import org.synyx.hades.domain.Pageable;
import org.synyx.hades.domain.Sort;
import org.synyx.hades.domain.Specification;

public class GenericDaoAdapter<T> implements GenericDao<T, Long> {

    @Override
    public Long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long count(Specification<T> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Collection<? extends T> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(T arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exists(Long arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> readAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<T> readAll(Pageable arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> readAll(Sort arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<T> readAll(Specification<T> arg0, Pageable arg1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> readAll(Specification<T> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T readByPrimaryKey(Long arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> save(Collection<? extends T> arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T save(T arg0) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T saveAndFlush(T arg0) {
        throw new UnsupportedOperationException();
    }

}
