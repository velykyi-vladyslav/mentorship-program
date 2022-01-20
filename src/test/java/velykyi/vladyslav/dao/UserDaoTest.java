package velykyi.vladyslav.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.UserImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(locations = "classpath:test-config.xml")
@ExtendWith(SpringExtension.class)
class UserDaoTest extends BaseTest{
    private static final long USER_ID = 12345L;

    private static final String CHANGED_NAME = "Mike Vince";

    private static final String EMAIL = "example@google.com";

    private final User expected = buildUser();

    @Autowired
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao.save(expected);
    }

    @AfterEach
    void deleteUnnecessary() {
        userDao.delete(expected.getId());
    }

    @Test
    void testSaveAndRetrieveEvent() {
        User actually = userDao.get(expected.getId());

        assertSavingModel(expected, actually);
    }

    @Test
    void testUpdateEvent() {
        User newUser = buildUserWithChangedName();

        User actually = userDao.update(expected, newUser);

        assertThat(actually.getName(), is(CHANGED_NAME));
    }

    @Test
    void testDeleteEvent() {
        userDao.delete(expected.getId());

        assertNull(userDao.get(expected.getId()));
    }

    private User buildUser() {
        return new UserImpl(USER_ID, "John Smith", EMAIL);
    }

    private User buildUserWithChangedName() {
        return new UserImpl(USER_ID, CHANGED_NAME, EMAIL);
    }
}
