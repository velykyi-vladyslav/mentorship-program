package velykyi.vladyslav.service.mapper;

import org.mapstruct.Mapper;
import velykyi.vladyslav.dto.EventDto;
import velykyi.vladyslav.model.impl.EventImpl;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto eventToEventDto(EventImpl event);

    EventImpl eventDtoToEvent(EventDto eventDto);
}
