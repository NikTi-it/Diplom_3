import Pageobjects.LoginPage;
import Pageobjects.MainPage;
import Pageobjects.UserRegistrationPage;
import Serialization.User;
import Steps.UserSteps;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static Helper.Constants.*;
import static Helper.Credentials.getCredentials;
import static Helper.DriverFactory.browserDriverSetUp;
import static Helper.TestDataCreator.getNewDefaultUser;
import static java.net.HttpURLConnection.HTTP_ACCEPTED;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private final String browserType;
    private UserSteps userSteps;
    private User user;
    private String name;
    private String email;
    private String password;
    private WebDriver driver;

    public RegistrationTest(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowserType() {
        return new Object[][]{
                {CHROME_BROWSER}, {YANDEX_BROWSER}
        };
    }


    @Before
    public void before() {
        userSteps = new UserSteps();
        user = getNewDefaultUser(); //получаем тестовые данные//проверяем, что токен авторизации присутствует, пользователь успешно создан и может быть авторизован
        email = user.getEmail(); //получаем почту пользователя
        password = user.getPassword(); //получаем пароль пользователя
        name = user.getName();
    }

    @After
    public void after() {
        driver.quit();
        String token = userSteps.signInUser(getCredentials(user)).extract().body().path("accessToken");
        if (token !=null) {
            Assert.assertEquals(HTTP_ACCEPTED, userSteps.deleteUser(token).extract().statusCode());
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Позитивный тест на успешную регистрацию пользователя")
    public void registrationUserSuccessCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        UserRegistrationPage userRegistrationPage = new UserRegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.registerButtonClick();
        userRegistrationPage.inputName(name);
        userRegistrationPage.inputEmail(email);
        userRegistrationPage.inputPassword(password);
        userRegistrationPage.registrationButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(mainPage.placeOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Негативный тест на получение ошибки для некорректного пароля")
    public void incorrectPasswordErrorCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        UserRegistrationPage userRegistrationPage = new UserRegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.registerButtonClick();
        userRegistrationPage.inputName(name);
        userRegistrationPage.inputEmail(email);
        userRegistrationPage.inputPassword("123");
        userRegistrationPage.registrationButtonClick();
        Assert.assertTrue(userRegistrationPage.incorrectPasswordErrorIsVisible());
    }
}
