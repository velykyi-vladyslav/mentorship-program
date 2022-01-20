package velykyi.vladyslav.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import velykyi.vladyslav.model.Ticket;
import velykyi.vladyslav.model.impl.TicketImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(locations = "classpath:test-config.xml")
@ExtendWith(SpringExtension.class)
class TicketDaoTest extends BaseTest {
    private static final long EVENT_ID = 12345L;

    private static final long TICKET_ID = 1L;

    private static final long USER_ID = 123L;

    private static final long CHANGED_USER_ID = 1L;

    private static final int PLACE = 15;

    private final Ticket expected = buildTicket();

    @Autowired
    private TicketDao ticketDao;

    @BeforeEach
    void setUp() {
        ticketDao.save(expected);
    }

    @AfterEach
    void deleteUnnecessary() {
        ticketDao.delete(expected.getId());
    }

    @Test
    void testSaveAndRetrieveEvent() {
        Ticket actually = ticketDao.get(expected.getId());

        assertSavingModel(expected, actually);
    }

    @Test
    void testUpdateEvent() {
        Ticket newTicket = buildTicketWithChangedUserId();

        Ticket actually = ticketDao.update(expected, newTicket);

        assertThat(actually.getUserId(), is(1L));
    }

    @Test
    void testDeleteEvent() {
        ticketDao.delete(expected.getId());

        assertNull(ticketDao.get(expected.getId()));
    }

    private Ticket buildTicket() {
        return new TicketImpl(TICKET_ID, EVENT_ID, USER_ID, Ticket.Category.PREMIUM, PLACE);
    }

    private Ticket buildTicketWithChangedUserId() {
        return new TicketImpl(TICKET_ID, EVENT_ID, CHANGED_USER_ID, Ticket.Category.PREMIUM, PLACE);
    }
}
