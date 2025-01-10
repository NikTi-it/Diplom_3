package Steps;

import Helper.Configuration;
import Serialization.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserSteps extends Configuration {

    private static final String REGISTER_PATH = "/auth/register";
    private static final String SIGN_IN_PATH = "/auth/login";
    private static final String USER_PATH = "/auth/user";

    @Step("Создание пользователя")
    public void createUser(User user) {
        given()
                .spec(getConfiguration())
                .log()
                .all()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then()
                .log()
                .all();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse signInUser(User user) {
        return given()
                .spec(getConfiguration())
                .log()
                .all()
                .body(user)
                .when()
                .post(SIGN_IN_PATH)
                .then()
                .log()
                .all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token){
        return given()
                .spec(getConfiguration())
                .log()
                .all()
                .header("Authorization", token)
                .when()
                .delete(USER_PATH)
                .then()
                .log()
                .all();
    }
}
