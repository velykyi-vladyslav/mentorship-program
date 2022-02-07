package velykyi.vladyslav.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import velykyi.vladyslav.dto.EventDto;
import velykyi.vladyslav.facade.BookingFacade;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.impl.EventImpl;
import velykyi.vladyslav.service.mapper.EventMapper;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Controller for simply implementation simple thymeleaf templates for displaying found events.
 */
@Controller
@RequestMapping("thymeleaf/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThymeleafBookingController {

    private static final String EVENTS_FILE = "events";

    private final BookingFacade bookingFacade;

    private final EventMapper eventMapper;

    /**
     * Method for getting event by provided id as path variable
     *
     * @param id    for finding event by its id
     * @param model for collecting attributes to inject into template file
     * @return html template page with changed attributes
     */
    @GetMapping("event/{id}")
    public String getEventById(@PathVariable long id, Model model) {
        model.addAttribute("event", map(bookingFacade.getEventById(id)).toString());

        return EVENTS_FILE;
    }

    /**
     * Method for getting events by provided title as path variable
     *
     * @param title for finding list of events by title
     * @param model for collecting attributes to inject into template file
     * @return html template page with changed attributes
     */
    @GetMapping("events/{title}")
    public String getEventsByTitle(@PathVariable String title, Model model) {
        model.addAttribute("events", getEventsByTitle(title));

        return EVENTS_FILE;
    }

    private EventDto map(Event event) {
        return eventMapper.eventToEventDto((EventImpl) event);
    }

    private List<EventDto> getEventsByTitle(String title) {
        return bookingFacade.getEventsByTitle(title, 0, 0).stream()
                            .map(this::map)
                            .collect(toList());
    }
}
