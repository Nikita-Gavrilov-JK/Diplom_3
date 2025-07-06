package tests;

import api.ApiBaseFun;
import base.BaseTest;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.AccountPage;
import pageobject.ForgotPasswordPage;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(AllureJunit5.class)
public class LoginTest extends BaseTest {
    private String token;
    private String email;
    private final String password = "password123";

    @BeforeEach
    public void setupUser() {
        email = System.currentTimeMillis() + "@example.com";
        token = ApiBaseFun.createUser(email, password, "TestUser");
    }

    @Test
    public void loginFromMainButton() {
        new HomePage(driver)
                .open()
                .clickLogin()
                .fillEmail(email)
                .fillPassword(password)
                .clickLogin();

        // проверяем уже сразу
        assertTrue(driver.getCurrentUrl().contains("/account"));
    }

    @Test
    public void loginFromAccountButton() {
        LoginPage login = new HomePage(driver)
                .open()
                .clickAccount();
        AccountPage account = login
                .fillEmail(email)
                .fillPassword(password)
                .clickLogin();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/account"));
        assertTrue(driver.getCurrentUrl().contains("/account"));
    }

    @Test
    public void loginFromRegisterForm() {
        RegistrationPage reg = new HomePage(driver)
                .open()
                .clickLogin()
                .goToRegister();
        LoginPage login = reg.goToLogin();
        AccountPage account = login
                .fillEmail(email)
                .fillPassword(password)
                .clickLogin();
//        new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.urlContains("/account"));
        assertTrue(driver.getCurrentUrl().contains("/account"));
    }

    @Test
    public void loginFromRecoveryForm() {
        ForgotPasswordPage forgot = new HomePage(driver)
                .open()
                .clickLogin()
                .goToForgotPassword();
        LoginPage login = forgot.goToLogin();
        AccountPage account = login
                .fillEmail(email)
                .fillPassword(password)
                .clickLogin();
//        new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.urlContains("/account"));
        assertTrue(driver.getCurrentUrl().contains("/account"));
    }

    @AfterEach
    public void cleanup() {
        if (token != null) {
            ApiBaseFun.deleteUser(token);
        }
    }
}
