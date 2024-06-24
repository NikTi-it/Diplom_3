package Pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserRegistrationPage {

    private final WebDriver driver;

    private final By nameInput = By.xpath(".//form/fieldset[1]/div/div/input");
    private final By emailInput = By.xpath(".//form/fieldset[2]/div/div/input");
    private final By passwordInput = By.xpath(".//form/fieldset[3]/div/div/input");
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By incorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public UserRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод данных в поле 'Имя'")
    public void inputName(String name) {
        driver.findElement(nameInput).sendKeys(name); // ввод нового значения из параметра
    }

    @Step("Ввод данных в поле 'Email'")
    public void inputEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод данных в поле 'Пароль'")
    public void inputPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void registrationButtonClick() {
        driver.findElement(registrationButton).click();
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Получение текста ошибки по некорректному паролю")
    public Boolean incorrectPasswordErrorIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPassword));
        return driver.findElement(incorrectPassword).isDisplayed();
    }
}
