package velykyi.vladyslav.service;

import velykyi.vladyslav.model.Event;

import java.util.*;

public interface EventService {
    Event createEvent(Event event);

    Event getEventById(long id);

    List<Event> getEventsByTitle(String title);

    List<Event> getEventsForDay(Date day);

    Event updateEvent(Event event);

    boolean deleteEvent(long eventId);
}
