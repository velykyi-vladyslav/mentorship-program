package velykyi.vladyslav.it;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import velykyi.vladyslav.facade.BookingFacadeImpl;
import velykyi.vladyslav.model.Event;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.EventImpl;
import velykyi.vladyslav.model.impl.UserImpl;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"/storage-beans.xml","/service-beans.xml"})
class BookingFacadeIT {

    @Autowired
    private BookingFacadeImpl bookingFacade;

    @Test
    void testCreateUser(){
        User user = bookingFacade.createUser(new UserImpl(123L, "Jogn", "Vines"));
        assertThat(user, is(notNullValue()));
    }

    @Test
    void testCreateEvent(){
        Event event = bookingFacade.createEvent(new EventImpl(123L, "Event 1"));
        assertThat(event, is(notNullValue()));
    }
}
