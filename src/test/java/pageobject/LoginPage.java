package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    private final By registerLink = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    private final By forgotPasswordLink = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    private final By personalAccount = By.xpath("//a[@href='/account']");
    private final By  restore = By.xpath("//button[contains(text(),'Восстановить')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести email: {email}")
    public LoginPage fillEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль: {pwd}")
    public LoginPage fillPassword(String pwd) {
        driver.findElement(passwordField).sendKeys(pwd);
        return this;
    }

    @Step("Нажать Войти и перейти в Личный кабинет")
    public AccountPage clickLogin() {
        driver.findElement(loginButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccount));
        // Переход в Личный кабинет
        driver.findElement(personalAccount).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/account"));
        return new AccountPage(driver);
    }
    @Step("Перейти на страницу регистрации")
    public RegistrationPage goToRegister() {
        driver.findElement(registerLink).click();
        return new RegistrationPage(driver);
    }

    @Step("Перейти на форму восстановления пароля")
    public ForgotPasswordPage goToForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(restore));
        return new ForgotPasswordPage(driver);
    }
}
