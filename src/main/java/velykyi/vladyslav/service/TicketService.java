package velykyi.vladyslav.service;

import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.Ticket;
import velykyi.vladyslav.model.User;

import java.util.*;

public interface TicketService {
    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    List<Ticket> getBookedTickets(User user);

    List<Ticket> getBookedTickets(Event event);

    boolean cancelTicket(long ticketId);
}
