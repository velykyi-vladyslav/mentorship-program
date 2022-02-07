package velykyi.vladyslav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.dao.TicketDao;
import velykyi.vladyslav.exception.InvalidValueException;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.Ticket;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.TicketImpl;
import velykyi.vladyslav.service.TicketService;
import velykyi.vladyslav.util.ServiceValidator;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TicketServiceImpl implements TicketService {

    private static final long LEFT_LIMIT = 1;

    private static final long RIGHT_LIMIT = 100000L;

    private final TicketDao ticketDao;

    private final ServiceValidator serviceValidator;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao, ServiceValidator serviceValidator) {
        this.ticketDao = ticketDao;
        this.serviceValidator = serviceValidator;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        bookingTicketValidation(userId, eventId, place);

        Ticket newTicket = new TicketImpl(generateId(), eventId, userId, category, place);
        ticketDao.save(newTicket);
        return newTicket;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDao.getAll();
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        serviceValidator.validateUser(user.getId());

        return ticketDao.getAll().stream()
                .filter(ticket -> ticket.getUserId() == user.getId())
                .collect(toList());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        serviceValidator.validateEvent(event.getId());

        return ticketDao.getAll().stream()
                .filter(ticket -> equals(ticket.getEventId(), event.getId()))
                .collect(toList());
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        ticketDao.delete(ticketId);
        return true;
    }

    @Override
    public void createTickets(List<Ticket> tickets) {
        tickets.forEach(ticketDao::save);
    }

    private long generateId() {
        return LEFT_LIMIT + (long) (Math.random() * (RIGHT_LIMIT - LEFT_LIMIT));
    }

    private boolean equals(long n, long m) {
        return n == m;
    }

    private void bookingTicketValidation(long userId, long eventId, int place) {
        if (place <= 0) {
            throw new InvalidValueException("Place should be positive and non null, but provided:" + place);
        }
        serviceValidator.validateUser(userId);
        serviceValidator.validateEvent(eventId);
    }
}
