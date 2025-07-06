package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage extends BasePage{

    private final By logoutButton = By.xpath("//button[text()='Выход']");
    private final By constructorTab = By.xpath("//p[text()='Конструктор']");
    private final By logo = By.cssSelector(".AppHeader_header__logo__2D0X2");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Выйти из аккаунта")
    public LoginPage logout() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutButton))
                .click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));
        return new LoginPage(driver);
    }

    @Step("Перейти в Конструктор")
    public HomePage clickConstructor() {
        driver.findElement(constructorTab).click();
        return new HomePage(driver);
    }
}
