package Pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {

    private final WebDriver driver;

    private final By profilePageText = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By logoutButton = By.xpath("//li[@class='Account_listItem__35dAP']/button");
    private final By constructorButton = By.xpath("//p[text()='Конструктор']");
    private final By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка видимости текста на странице профиля")
    public boolean profilePageTextIsVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(profilePageText));
        return driver.findElement(profilePageText).isDisplayed();
    }

    @Step("Нажатие на кнопку 'Выход'")
    public void logoutButtonClick() {
        driver.findElement(logoutButton).click();
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void constructorButtonClick() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на кнопку 'Логотип'")
    public void logoButtonClick() {
        driver.findElement(logoButton).click();
    }
}
