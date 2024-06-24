import Pageobjects.LoginPage;
import Pageobjects.MainPage;
import Pageobjects.ProfilePage;
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
public class ProfilePageTest {

    private final String browserType;
    private UserSteps userSteps;
    private String token;
    private String email;
    private String password;
    private WebDriver driver;

    public ProfilePageTest(String browserType) {
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
    @DisplayName("Переход по клику на «Личный кабинет»")
    @Description("Позитивный тест на переход по клику на «Личный кабинет»")
    public void mainPageToProfilePageTransferCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        mainPage.profilePageClick();
        Assert.assertTrue(profilePage.profilePageTextIsVisible());
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    @Description("Позитивный тест на переход по клику на «Конструктор»")
    public void profilePageConstructorClickTransferCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        mainPage.profilePageClick();
        profilePage.profilePageTextIsVisible();
        profilePage.constructorButtonClick();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(mainPage.placeOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Переход по клику на «Логотип»")
    @Description("Позитивный тест на переход по клику на «Логотип»")
    public void profilePageLogoClickTransferCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        mainPage.profilePageClick();
        profilePage.profilePageTextIsVisible();
        profilePage.logoButtonClick();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(mainPage.placeOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    @Description("Позитивный тест на выход по кнопке «Выйти» в личном кабинете")
    public void profilePageLogoutCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.loginButtonClick();
        loginPage.waitForLoginPageLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.loginButtonClick();
        mainPage.waitForLoadMainPage();
        mainPage.profilePageClick();
        profilePage.profilePageTextIsVisible();
        profilePage.logoutButtonClick();
        loginPage.waitForLoginPageLoad();
        Assert.assertTrue(loginPage.loginButtonIsVisible());
    }
}
