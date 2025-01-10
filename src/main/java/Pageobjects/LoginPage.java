package Pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By loginButton = By.xpath(".//button[contains(text(),'Войти')]");
    private final By emailInput = By.xpath(".//input[@name='name']");
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");
    private final By registerButton = By.xpath("//a[@href='/register']");

    @Step("Заполнение поля 'Email'")
    public void inputEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Заполнение поля 'Пароль'")
    public void inputPassword(String password ){
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void registerButtonClick() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void forgotPasswordButtonClick() {
        driver.findElement(registerButton).click();
    }

    @Step("Ожидание отображения кнопки 'Войти'")
    public void waitForLoginPageLoad() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Проверка отображения кнопки 'Войти'")
    public boolean loginButtonIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return driver.findElement(loginButton).isDisplayed();
    }
}
