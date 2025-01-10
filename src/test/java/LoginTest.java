import Pageobjects.MainPage;
import Pageobjects.LoginPage;
import Pageobjects.PasswordRestorePage;
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
public class LoginTest {

    private final String browserType;
    private UserSteps userSteps;
    private String token;
    private String email;
    private String password;
    private WebDriver driver;

    public LoginTest(String browserType) {
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
        User user = getNewDefaultUser(); //получаем тестовые данные
        userSteps.createUser(user); //создаем пользователя
        token = userSteps.signInUser(getCredentials(user)).extract().body().path("accessToken"); //входим пользователем через API
        Assert.assertNotNull(token); //проверяем, что токен авторизации присутствует, пользователь успешно создан и может быть авторизован
        email = user.getEmail(); //получаем почту пользователя
        password = user.getPassword(); //получаем пароль пользователя
    }

    @After
    public void after() {
        driver.quit();
        if (token !=null) {
            Assert.assertEquals(HTTP_ACCEPTED, userSteps.deleteUser(token).extract().statusCode());
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Позитивный тест на вход по кнопке «Войти в аккаунт» на главной странице")
    public void mainPageLoginCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(mainPage.placeOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Позитивный тест на вход через кнопку «Личный кабинет»")
    public void personalAreaLoginCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.profilePageClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(mainPage.placeOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Позитивный тест на вход через кнопку в форме регистрации")
    public void registrationFormLoginCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        UserRegistrationPage userRegistrationPage = new UserRegistrationPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.registerButtonClick();
        userRegistrationPage.loginButtonClick();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(mainPage.placeOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Позитивный тест на вход через кнопку в форме восстановления пароля")
    public void restorePasswordFormLoginCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PasswordRestorePage passwordRestorePage = new PasswordRestorePage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.forgotPasswordButtonClick();
        passwordRestorePage.loginButtonIsVisible();
        passwordRestorePage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(mainPage.placeOrderButtonIsVisible());
    }
}
