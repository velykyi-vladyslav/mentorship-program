package velykyi.vladyslav.dao;

import org.springframework.beans.factory.annotation.Autowired;
import velykyi.vladyslav.MapStorage;
import velykyi.vladyslav.model.Ticket;

import java.util.*;

import static velykyi.vladyslav.dao.DaoKeyName.TICKET_PREFIX;

public class TicketDao extends AbstractDao<Ticket> {

    @Autowired
    protected TicketDao(MapStorage mapStorage) {
        super(mapStorage);
    }

    @Override
    public Ticket map(Object o) {
        return (Ticket) o;
    }

    @Override
    public String getPrefix() {
        return TICKET_PREFIX;
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(getStorage().values());
    }
}
