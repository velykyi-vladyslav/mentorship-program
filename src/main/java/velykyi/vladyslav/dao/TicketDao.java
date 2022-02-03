package velykyi.vladyslav.dao;

import org.springframework.stereotype.Component;
import velykyi.vladyslav.MapStorage;
import velykyi.vladyslav.model.Ticket;

import static velykyi.vladyslav.dao.DaoKeyName.TICKET_PREFIX;

@Component
public class TicketDao extends AbstractDao<Ticket> {

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
}
