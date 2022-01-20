package velykyi.vladyslav.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import velykyi.vladyslav.dao.EventDao;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.service.EventService;

import java.util.List;
import java.util.Date;

import static java.util.stream.Collectors.toList;


public class EventServiceImpl implements EventService {
    @Autowired
    private EventDao eventDao;

    @Override
    public Event createEvent(Event event) {
        eventDao.save(event);
        return event;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventDao.get(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        return eventDao.getAllEvents().stream()
                                      .filter(event -> event.getTitle()
                                      .equals(title)).collect(toList());
    }

    @Override
    public List<Event> getEventsForDay(Date day) {
        return eventDao.getAllEvents().stream()
                                      .filter(event -> equals(event.getDate().getDay(), day.getDay()))
                                      .collect(toList());
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDao.update(event, event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        eventDao.delete(eventId);
        return true;
    }

    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    private boolean equals(int day, int eventDay) {
        return day == eventDay;
    }
}
