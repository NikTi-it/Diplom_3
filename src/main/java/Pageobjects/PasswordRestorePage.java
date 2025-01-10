package Pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasswordRestorePage {

    WebDriver driver;

    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public PasswordRestorePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка видимости кнопки 'Войти'")
    public void loginButtonIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).isDisplayed();
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void loginButtonClick() {
        driver.findElement(loginButton).click();
    }
}
