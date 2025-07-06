package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    /**
     * Создаёт WebDriver на основе системного свойства -Dbrowser.
     * По умолчанию запускает обычный Chrome, если указано "yandex" — Яндекс.Браузер.
     */
    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        ChromeOptions options = new ChromeOptions();
        if ("yandex".equals(browser)) {
            // путь к YandexDriver.exe
            System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
            // настоящий бинарник Yandex.Browser
            options.setBinary("C:\\Users\\nikga\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        }

        return new ChromeDriver(options);
    }
}
