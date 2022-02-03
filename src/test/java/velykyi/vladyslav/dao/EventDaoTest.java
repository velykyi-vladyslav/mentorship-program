package velykyi.vladyslav.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.impl.EventImpl;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(locations = "classpath:test-config.xml")
@ExtendWith(SpringExtension.class)
class EventDaoTest extends BaseTest {
    private static final long EVENT_ID = 12345L;

    private static final String EVENT_TITLE = "title";

    private static final String CHANGED_TITLE = "Changed title";

    private final Event expected = buildEvent();

    @Autowired
    private EventDao eventDao;

    @BeforeEach
    void setUp() {
        eventDao.save(expected);
    }

    @AfterEach
    void deleteUnnecessary() {
        eventDao.delete(expected.getId());
    }

    @Test
    void testSaveAndRetrieveEvent() {
        Event actually = eventDao.get(expected.getId());

        assertSavingModel(expected, actually);
    }

    @Test
    void testUpdateEvent() {
        Event newEvent = buildEventWithChangedTitle();

        Event actually = eventDao.update(expected, newEvent);

        assertThat(actually.getTitle(), is("Changed title"));
    }

    @Test
    void testDeleteEvent() {
        eventDao.delete(expected.getId());

        assertNull(eventDao.get(expected.getId()));
    }

    @Test
    void getAllEvents(){
        eventDao.save(buildEventWithChangedTitle());

        List<Event> actually = eventDao.getAll();
        List<Event> expected = new ArrayList<>();
        expected.add(buildEvent());
        expected.add(buildEventWithChangedTitle());

        assertEquals(actually, expected);
    }

    private Event buildEvent() {
        return new EventImpl(EVENT_ID, EVENT_TITLE);
    }

    private Event buildEventWithChangedTitle() {
        return new EventImpl(EVENT_ID, CHANGED_TITLE);
    }
}
