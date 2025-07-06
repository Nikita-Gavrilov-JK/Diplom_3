package tests;

import api.ApiBaseFun;
import base.BaseTest;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.AccountPage;
import pageobject.HomePage;
import pageobject.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
public class ExitAccountTest extends BaseTest {
    private String token;
    private String email;
    private final String password = "password123";
    @BeforeEach
    public void setupUser() {
        email = System.currentTimeMillis() + "@example.com";
        token = ApiBaseFun.createUser(email, password, "NavUser");
    }

    @Test
    @DisplayName("Проверь выход по кнопке «Выйти» в личном кабинете.")
    public void checkExit() {
        AccountPage account = new HomePage(driver)
                .open()
                .clickAccount()
                .fillEmail(email)
                .fillPassword(password)
                .clickLogin();
        LoginPage loginPage = account.logout();
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @AfterEach
    public void cleanup() {
        if (token != null) {
            ApiBaseFun.deleteUser(token);
        }
    }
}
