import Pageobjects.MainPage;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static Helper.Constants.*;
import static Helper.DriverFactory.browserDriverSetUp;

@RunWith(Parameterized.class)
public class ConstructorTest {

    private final String browserType;
    private WebDriver driver;

    public ConstructorTest(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowserType() {
        return new Object[][]{
                {CHROME_BROWSER}, {YANDEX_BROWSER}
        };
    }

    @After
    public void after() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка доступности перехода к секции 'Булочки'")
    @Description("Позитивный тест доступности перехода к секции 'Булочки'")
    public void bunsSectionTransferCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.fillingsSectionClick();
        mainPage.bunsSectionClick();
        Assert.assertTrue(mainPage.bunSectionActiveButtonIsVisible());
    }

    @Test
    @DisplayName("Проверка доступности перехода к секции 'Соусы'")
    @Description("Позитивный тест доступности перехода к секции 'Соусы'")
    public void sauceSectionTransferCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.saucesSectionClick();
        Assert.assertTrue(mainPage.sauceSectionButtonIsVisible());
    }

    @Test
    @DisplayName("Проверка доступности перехода к секции 'Начинки'")
    @Description("Позитивный тест доступности перехода к секции 'Начинки'")
    public void fillingSectionTransferCheck() {
        driver = browserDriverSetUp(browserType);
        MainPage mainPage = new MainPage(driver);
        driver.get(BASE_URL);
        mainPage.waitForLoadMainPage();
        mainPage.fillingsSectionClick();
        Assert.assertTrue(mainPage.fillingSectionButtonIsVisible());
    }
}