package velykyi.vladyslav.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import velykyi.vladyslav.dao.EventDao;
import velykyi.vladyslav.dao.TicketDao;
import velykyi.vladyslav.dao.UserDao;
import velykyi.vladyslav.exception.EventNotFoundException;
import velykyi.vladyslav.exception.TicketNotFoundException;
import velykyi.vladyslav.exception.UserNotFoundException;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Component
public class ServiceValidator {

    private final TicketDao ticketDao;

    private final UserDao userDao;

    private final EventDao eventDao;

    @Autowired
    public ServiceValidator(TicketDao ticketDao, UserDao userDao, EventDao eventDao) {
        this.ticketDao = ticketDao;
        this.userDao = userDao;
        this.eventDao = eventDao;
    }

    public void validateUser(long userId){
        validate(userDao.get(userId), Objects::isNull, UserNotFoundException::new);
    }

    public void validateTicket(long ticketId){
        validate(ticketDao.get(ticketId), Objects::isNull, TicketNotFoundException::new);
    }

    public void validateEvent(long eventId){
        validate(eventDao.get(eventId), Objects::isNull, EventNotFoundException::new);
    }

    public static <T> void validate(T value,
                              Predicate<T> invalidValueVerifier,
                              Supplier<RuntimeException> exceptionSupplier) {
        if (invalidValueVerifier.test(value)) {
            throw exceptionSupplier.get();
        }
    }
}
