package com.lumesse.tutorial.dao;

import java.util.List;

import org.synyx.hades.dao.GenericDao;
import org.synyx.hades.dao.Param;
import org.synyx.hades.dao.Query;

import com.lumesse.tutorial.domain.SimpleEntity;
import com.lumesse.tutorial.dto.KeyValueEntry;
import com.lumesse.tutorial.dto.SimpleEntityDto;

public interface SimpleEntityDAO extends GenericDao<SimpleEntity, Long> {

    @Query(value = "select name from SimpleEntity where id = :id")
    String findSimpleEntityNameById(@Param("id") Long id);

    @Query(value = "select new com.lumesse.tutorial.dto.SimpleEntityDto(id, name) " +
            "from SimpleEntity " +
            "where upper(name) = upper(:name)")
    List<SimpleEntityDto> findSimpleEntitiesByName(@Param("name") String name);

    @Query(value = "from SimpleEntity order by name")
    List<SimpleEntity> getAllEntitiesSortedByName();

    @Query(value = "select new com.lumesse.tutorial.dto.KeyValueEntry(someValue, max(numValue)) " +
            "from SimpleEntity " +
            "where name = 'group' " +
            "group by someValue " +
            "having max(numValue) > :minValue")
    List<KeyValueEntry> findMaxValueOfEachGroup(@Param("minValue") long minValue);
}
