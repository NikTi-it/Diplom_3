package Pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private final WebDriver driver;

    private final By personalAreaButton = By.linkText("Личный Кабинет");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By makeBurgerTitle = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10']");
    private final By bunsSection = By.xpath(".//span[text()='Булки']");
    private final By saucesSection = By.xpath(".//span[text()='Соусы']");
    private final By fillingSection = By.xpath(".//span[text()='Начинки']");
    private final By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By bunImage = By.xpath(".//img[@alt = 'Краторная булка N-200i']");
    private final By sauceImage = By.xpath(".//img[@alt = 'Соус традиционный галактический']");
    private final By fillingImage = By.xpath(".//img[@alt = 'Мясо бессмертных моллюсков Protostomia']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public void profilePageClick() {
        driver.findElement(personalAreaButton).click();
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку секции 'Булочки'")
    public void bunsSectionClick() {
        driver.findElement(bunsSection).click();
    }

    @Step("Нажатие на кнопку секции 'Cоусы'")
    public void saucesSectionClick() {
        driver.findElement(saucesSection).click();
    }

    @Step("Нажатие на кнопку секции 'Начинки'")
    public void fillingsSectionClick() {
        driver.findElement(fillingSection).click();
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForLoadMainPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(makeBurgerTitle));
    }

    @Step("Проверка видимости кнопки 'Оформить заказ'")
    public Boolean placeOrderButtonIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        return driver.findElement(placeOrderButton).isDisplayed();
    }

    @Step("Проверка видимости булочки в секции 'Булочки'")
    public Boolean bunImageIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(bunImage));
        return driver.findElement(bunImage).isDisplayed();
    }

    @Step("Проверка видимости начинки в секции 'Начинки'")
    public Boolean fillingImageIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(fillingImage));
        return driver.findElement(fillingImage).isDisplayed();
    }

    @Step("Проверка видимости соуса в секции 'Соусы'")
    public Boolean sauceImageIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(sauceImage));
        return driver.findElement(sauceImage).isDisplayed();
    }
}
