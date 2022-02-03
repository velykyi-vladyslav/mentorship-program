package velykyi.vladyslav.controller;

import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import velykyi.vladyslav.dto.DateDto;
import velykyi.vladyslav.dto.EventDto;
import velykyi.vladyslav.dto.TicketDto;
import velykyi.vladyslav.dto.UserDto;
import velykyi.vladyslav.facade.BookingFacade;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.Ticket;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.EventImpl;
import velykyi.vladyslav.service.mapper.EventMapper;
import velykyi.vladyslav.service.mapper.TicketMapper;
import velykyi.vladyslav.service.mapper.UserMapper;
import velykyi.vladyslav.util.PdfReportGenerator;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("booking/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {

    private final EventMapper eventMapper;

    private final UserMapper userMapper;

    private final TicketMapper ticketMapper;

    private final BookingFacade bookingFacade;

    @ResponseStatus(OK)
    @GetMapping("event/{id}")
    public EventDto getEventById(@PathVariable long id) {
        return map(bookingFacade.getEventById(id));
    }

    @ResponseStatus(OK)
    @GetMapping("events/{title}")
    public List<EventDto> getEventsByTitle(@PathVariable String title) {
        return bookingFacade.getEventsByTitle(title, 0,0).stream()
                                                                          .map(this::map)
                                                                          .collect(Collectors.toList());
    }

    @ResponseStatus(CREATED)
    @PostMapping("create/event")
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return map(bookingFacade.createEvent(map(eventDto)));
    }

    @ResponseStatus(OK)
    @DeleteMapping("event/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        bookingFacade.deleteEvent(id);

        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(ACCEPTED)
    @PutMapping("update/event")
    public EventDto updateEvent(@RequestBody EventDto eventDto) {
        return map(bookingFacade.updateEvent(map(eventDto)));
    }

    @ResponseStatus(ACCEPTED)
    @GetMapping("eventsForDay")
    public List<EventDto> updateEvent(@RequestBody DateDto date) {
        return bookingFacade.getEventsForDay(date.getDate(), 1, 1).stream()
                                                                                   .map(this::map)
                                                                                   .collect(Collectors.toList());
    }

    @ResponseStatus(CREATED)
    @PostMapping("tickets/book")
    public TicketDto bookTicket(@RequestBody TicketDto ticketDto) {
        Ticket ticket = map(ticketDto);

        return map(bookingFacade.bookTicket(ticket.getUserId(), ticket.getEventId(), ticket.getPlace(), ticket.getCategory()));
    }

    @ResponseStatus(CREATED)
    @GetMapping(value = "tickets", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity getBookedTickets(@RequestHeader("accept") String header, @RequestBody UserDto user) {
        List<Ticket> tickets = bookingFacade.getBookedTickets(map(user),0,0);

        if (header.equals("application/pdf")){
          return getPdfOfBookedTickets(tickets);
        }
        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(map(tickets));
    }

    @ResponseStatus(CREATED)
    @GetMapping(value = "tickets/all")
    public List<TicketDto> getBookedTickets() {
        List<Ticket> tickets = bookingFacade.get();

        return map(tickets);
    }

    @ResponseStatus(CREATED)
    @PostMapping("users/create")
    public void createUser(@RequestBody UserDto userDto) {
         bookingFacade.createUser(map(userDto));
    }

    @ResponseStatus(OK)
    @GetMapping("users/{id}")
    public UserDto getUser(@PathVariable long id) {
        return map(bookingFacade.getUserById(id));
    }


    private ResponseEntity<InputStreamResource> getPdfOfBookedTickets(List<Ticket> tickets) {
        ByteArrayInputStream bis = PdfReportGenerator.ticketsReport(tickets);
        var headers = new HttpHeaders();

        headers.add("Content-Disposition", "inline; filename=ticketsreport.pdf");

        return ResponseEntity.ok()
                             .headers(headers)
                             .contentType(MediaType.APPLICATION_PDF)
                             .body(new InputStreamResource(bis));
    }

    private TicketDto map(Ticket ticket) {
        return ticketMapper.ticketToTicketDto(ticket);
    }

    private Ticket map(TicketDto ticketDto) {
        return ticketMapper.ticketDtoToTicket(ticketDto);
    }

    private List<TicketDto> map(List<Ticket> tickets) {
        return tickets.stream().map(this::map).collect(Collectors.toList());
    }

    private UserDto map(User user) {
        return userMapper.userToUserDto(user);
    }

    private User map(UserDto userDto) {
        return userMapper.userDtoToUser(userDto);
    }

    private EventDto map(Event event) {
        return eventMapper.eventToEventDto((EventImpl) event);
    }

    private Event map(EventDto eventDto) {
        return eventMapper.eventDtoToEvent(eventDto);
    }
}
