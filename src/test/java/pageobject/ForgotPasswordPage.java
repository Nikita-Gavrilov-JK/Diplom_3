package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {
    private final By loginLink = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Перейти на страницу логина")
    public LoginPage goToLogin() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
