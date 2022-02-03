package velykyi.vladyslav.service.mapper;

import org.mapstruct.Mapper;
import velykyi.vladyslav.dto.TicketDto;
import velykyi.vladyslav.model.Ticket;
import velykyi.vladyslav.model.impl.TicketImpl;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDto ticketToTicketDto(Ticket ticket);

    TicketImpl ticketDtoToTicket(TicketDto ticketDto);
}
