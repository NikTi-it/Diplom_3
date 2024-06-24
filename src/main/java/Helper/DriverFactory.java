package Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ChromeOptions options = new ChromeOptions();

    public static WebDriver browserDriverSetUp(String browser) {

        WebDriver driver = null;
        options.addArguments("--remote-allow-origins=*");

        switch (browser) {

            case "chrome":
                driver = new ChromeDriver(options);
                break;

            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:/Users/tft19/YandexDriver/yandexdriver.exe");
                driver = new ChromeDriver(options);
                break;
        }
        return driver;
    }
}
