package com.lumesse.tutorial.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.lumesse.tutorial.BaseIntegrationTestCase;
import com.lumesse.tutorial.dao.SimpleEntityDAO;
import com.lumesse.tutorial.domain.SimpleEntity;
import com.lumesse.tutorial.dto.KeyValueEntry;
import com.lumesse.tutorial.dto.SimpleEntityDto;

public abstract class BasicDAOUsageIntegrationTest extends BaseIntegrationTestCase {

    private static final Comparator<SimpleEntity> SIMPLE_ENTITY_NAME_COMPARATOR = new Comparator<SimpleEntity>() {

        @Override
        public int compare(SimpleEntity o1, SimpleEntity o2) {
            if (o1.getName() == null) {
                return o2.getName() == null ? 0 : -1;
            }
            return o1.getName().compareTo(o2.getName());
        }

    };

    private SimpleEntityDAO dao;

    @Override
    public void setUp() {
        super.setUp();

        this.dao = getDAO();
    }

    @Test
    public void shouldReadAllEntities() {
        // when
        List<SimpleEntity> results = dao.readAll();

        // then
        assertEquals(9L, results.size());
    }

    @Test
    public void shouldFindSimpleEntityById() {
        // given
        Long id = 9999L;

        // when
        SimpleEntity entity = dao.readByPrimaryKey(id);

        // then
        assertNotNull(entity);
        assertEquals("simpleEntity1", entity.getName());
    }

    @Test
    public void shouldReturnProperName() {
        // given
        String name = "test_name";
        Long id = saveSimpleEntityWithName(name).getId();

        // when
        String foundName = dao.findSimpleEntityNameById(id);

        // then
        assertEquals(name, foundName);
    }

    @Test
    public void shouldMapResultsToDto() {
        // given
        String name = "resultDTO";
        String[] properNames = { "resultdto", "Resultdto", "RESULTDTO" };
        String[] differentNames = { "test", "someValue" };

        for (String properName : properNames) {
            saveSimpleEntityWithName(properName);
        }
        for (String differentName : differentNames) {
            saveSimpleEntityWithName(differentName);
        }

        // when
        List<SimpleEntityDto> results = dao.findSimpleEntitiesByName(name);

        // then
        assertEquals(properNames.length, results.size());
    }

    @Test
    public void shouldSortResultsByName() {
        // when
        List<SimpleEntity> results = dao.getAllEntitiesSortedByName();

        // then
        List<SimpleEntity> referencialResults = dao.readAll();

        Collections.sort(referencialResults, SIMPLE_ENTITY_NAME_COMPARATOR);

        assertEquals(referencialResults, results);
    }

    @Test
    public void shouldFindMaxValueOfEachGroup() {
        // when
        List<KeyValueEntry> results = dao.findMaxValueOfEachGroup(0L);

        // then
        assertEquals(4, results.size());
        assertEquals(4L, getValueByKey(results, "A"));
        assertEquals(2L, getValueByKey(results, "B"));
        assertEquals(3L, getValueByKey(results, "C"));
        assertEquals(1L, getValueByKey(results, "D"));
    }

    @Test
    public void shouldFindMaxValuesUsingHaving() {
        // when
        List<KeyValueEntry> results = dao.findMaxValueOfEachGroup(2L);

        // then
        assertEquals(2, results.size());
        assertEquals(4L, getValueByKey(results, "A"));
        assertEquals(3L, getValueByKey(results, "C"));
    }

    private Object getValueByKey(List<KeyValueEntry> list, Object key) {
        for (KeyValueEntry entry : list) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private SimpleEntity saveSimpleEntityWithName(String name) {
        SimpleEntity simpleEntity = new SimpleEntity();
        simpleEntity.setName(name);

        em.persist(simpleEntity);
        em.flush();
        return simpleEntity;
    }

    protected abstract SimpleEntityDAO getDAO();
}
