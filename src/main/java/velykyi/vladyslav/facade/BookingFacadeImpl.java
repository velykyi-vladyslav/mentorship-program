package velykyi.vladyslav.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.Ticket;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.TicketImpl;
import velykyi.vladyslav.service.EventService;
import velykyi.vladyslav.service.TicketService;
import velykyi.vladyslav.service.UserService;
import velykyi.vladyslav.model.impl.TicketsWrapper;
import javax.xml.transform.stream.StreamSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingFacadeImpl implements BookingFacade {

    private final EventService eventService;

    private final TicketService ticketService;

    private final UserService userService;

   private final Jaxb2Marshaller jaxb2Marshaller;

   private static final String FILE_NAME =
           "C:/Documents/Courses/pet-projects/mentorship-program/src/main/resources/xml/tickets.xml";

    @Override
    public void preloadTickets() throws IOException {
        FileReader fileReader = new FileReader(FILE_NAME);

        TicketsWrapper ticketsWrapper = (TicketsWrapper) jaxb2Marshaller.unmarshal(new StreamSource(fileReader));
        List<TicketImpl> tickets = ticketsWrapper.getList();

        ticketService.createTickets(map(tickets));
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    public List<Ticket> get() {
       return ticketService.getAll();
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return eventService.getEventsByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return eventService.getEventsForDay(day);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getUsersByName(name);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketService.bookTicket(userId, eventId, place, category);
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(user);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return ticketService.getBookedTickets(event);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }

    private List<Ticket> map(List<TicketImpl> tickets) {
        return tickets.stream().map(this::map).collect(Collectors.toList());
    }

    private Ticket map(TicketImpl ticket) {
        return ticket;
    }
}
