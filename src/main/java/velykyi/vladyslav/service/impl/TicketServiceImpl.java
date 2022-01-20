package velykyi.vladyslav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import velykyi.vladyslav.dao.TicketDao;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.Ticket;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.TicketImpl;
import velykyi.vladyslav.service.TicketService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class TicketServiceImpl implements TicketService {

    private static final long LEFT_LIMIT = 1;

    private static final long RIGHT_LIMIT = 100000L;

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket newTicket = new TicketImpl(generateId(), userId, eventId, category, place);
        ticketDao.save(newTicket);
        return newTicket;
    }

    @Override
    public List<Ticket> getBookedTickets(User user){
        return ticketDao.getAllTickets().stream()
                                        .filter(ticket -> equals(ticket.getUserId(), user.getId()))
                                        .collect(toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event){
        return ticketDao.getAllTickets().stream()
                        .filter(ticket -> equals(ticket.getEventId(), event.getId()))
                        .collect(toList());
    }

    @Override
    public boolean cancelTicket(long ticketId){
    ticketDao.delete(ticketId);
    return true;
    }

    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    private long generateId() {
        return LEFT_LIMIT + (long) (Math.random() * (RIGHT_LIMIT - LEFT_LIMIT));
    }

    private boolean equals(long n, long m) {
        return n == m;
    }
}
