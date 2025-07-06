package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage extends BasePage {
    private final By nameField = By.name("name");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.name("Пароль");
    private final By registerButton = By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    private final By incorrectPasswordError = By.xpath("//p[contains(text(), 'Некорректный пароль')]");
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя пользователя: {name}")
    public RegistrationPage fillName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    @Step("Ввести email: {email}")
    public RegistrationPage fillEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль: {password}")
    public RegistrationPage fillPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public RegistrationPage clickRegister() {
        driver.findElement(registerButton).click();
        return this;
    }

    @Step("Нажать кнопку Зарегистрироваться и дождаться страницы логина")
    public LoginPage clickRegisterAndGoToLogin() {
        driver.findElement(registerButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));
        return new LoginPage(driver);
    }

    @Step("Получить текст ошибки некорректного пароля")
    public String getIncorrectPasswordError() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordError))
                .getText();
    }
    @Step("Перейти на страницу логина")
    public LoginPage goToLogin() {
        driver.findElement(loginLink).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return new LoginPage(driver);
    }
}

