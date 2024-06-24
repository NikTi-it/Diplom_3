package Helper;

import Serialization.User;
import com.github.javafaker.Faker;

public class TestDataCreator {

    private static final Faker faker = new Faker();

    public static User getNewDefaultUser() {
        return new User(faker.internet().emailAddress(), faker.internet().password(), faker.funnyName().name());
    }
}
