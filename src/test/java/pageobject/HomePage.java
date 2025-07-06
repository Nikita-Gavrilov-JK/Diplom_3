package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BasePage {
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath("//a[@href='/account']");
    private final By constructorTab = By.linkText("Конструктор");

    private final By emailWaitElement = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By logoElement = By.xpath("//div[contains(@class,'AppHeader_header__logo')]//a[@href='/']");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную")
    public HomePage open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        return this;
    }

    @Step("Нажать «Войти в аккаунт» и дождаться формы логина")
    public LoginPage clickLogin() {
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailWaitElement));
        return new LoginPage(driver);
    }

    @Step("Перейти по кнопке «Личный кабинет» и дождаться формы логина")
    public LoginPage clickAccount() {
        driver.findElement(accountButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailWaitElement));
        return new LoginPage(driver);
    }

    @Step("Нажать Конструктор")
    public ConstructorPage  clickConstructor() {
        driver.findElement(constructorTab).click();
        return new ConstructorPage(driver);
    }

    @Step("Клик по логотипу Stellar Burgers")
    public HomePage clickLogo() {
        driver.findElement(logoElement).click();
        return this;
    }
}
