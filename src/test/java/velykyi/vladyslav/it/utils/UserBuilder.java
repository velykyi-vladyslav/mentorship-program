package velykyi.vladyslav.it.utils;

import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.UserImpl;

public class UserBuilder {

    private long id = 1;

    private String name = "name";

    private String email = "email";

    public User build() {
        return new UserImpl(id, name, email);
    }

    public UserBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }
}
