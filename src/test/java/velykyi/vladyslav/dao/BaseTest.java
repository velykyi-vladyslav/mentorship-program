package velykyi.vladyslav.dao;

import velykyi.vladyslav.model.Model;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {

    protected void assertSavingModel(Model expected, Model actual) {
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual, expected);
        assertThat(actual, is(notNullValue()));
    }
}
