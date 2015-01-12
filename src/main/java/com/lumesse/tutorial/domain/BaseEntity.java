package com.lumesse.tutorial.domain;

/*
 * Copyright 2005 by MrTed All rights reserved. This software is the confidential and proprietary information of MrTed.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with MrTed.
 */

/**
 * The BaseEntity class. This abstract class should be the base for entities and let define equals(Object) and
 * hashCode() methods in one place.
 * 
 * @author Krzysztof Roksela
 */
public abstract class BaseEntity {

    public abstract Long getId();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getId() == null || !this.getClass().isInstance(obj)) {
            return false;
        }
        // now we verified whether or not class of "this" is instance
        // of object, so it's safe to cast
        BaseEntity entity = (BaseEntity) obj;
        return this.getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        }
        return super.hashCode();
    }

    /**
     * Method for generating composite hash code of two objects. Current implementation generates a sum of hash codes of
     * those objects. When one of the objects is null then it's hash code is assumed 0.
     * 
     * @param a
     *            first object.
     * @param b
     *            second object.
     * @return computer hash code. 0 when both are null.
     */
    public static int computeHashCode(Object a, Object b) {
        return (a == null ? 0 : a.hashCode()) +
                (b == null ? 0 : b.hashCode());
    }

}
