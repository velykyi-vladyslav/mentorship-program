package velykyi.vladyslav.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import velykyi.vladyslav.controller.BookingController;
import velykyi.vladyslav.exception.EventNotFoundException;
import velykyi.vladyslav.exception.UserNotFoundException;
import velykyi.vladyslav.facade.BookingFacade;
import velykyi.vladyslav.it.utils.UserBuilder;
import velykyi.vladyslav.model.User;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static velykyi.vladyslav.it.utils.BookingMappingPathConstants.DELETE_EVENT;
import static velykyi.vladyslav.it.utils.BookingMappingPathConstants.USERS_URI;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingFacade bookingFacade;

    private static final String USER_NOT_FOUND_MESSAGE = "velykyi.vladyslav.exception.UserNotFoundException: User is not found";

    private static final String EVENT_NOT_FOUND_MESSAGE = "velykyi.vladyslav.exception.UserNotFoundException: User is not found";

    private static final long VALID_ID = 1;

    private static final long INVALID_ID = -1;

    private final User user = UserBuilder.getBuilder().build();

    @Test
    void testGetUserById() throws Exception {
        when(bookingFacade.getUserById(VALID_ID)).thenReturn(user);

        mockMvc.perform(get(USERS_URI + VALID_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.email", is("email")));
    }

    @Test
    void testGetUserByInvalidIdShouldBeHandledWithProperException() throws Exception {
        when(bookingFacade.getUserById(INVALID_ID)).thenThrow(new UserNotFoundException());

        mockMvc.perform(get(USERS_URI + INVALID_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(USER_NOT_FOUND_MESSAGE));
    }

    @Test
    void testDeleteEvent() throws Exception {
        when(bookingFacade.deleteEvent(VALID_ID)).thenReturn(true);

        mockMvc.perform(delete(DELETE_EVENT + VALID_ID))
               .andExpect(status().isNoContent())
               .andExpect(result -> ResponseEntity.noContent().build());
    }
}
