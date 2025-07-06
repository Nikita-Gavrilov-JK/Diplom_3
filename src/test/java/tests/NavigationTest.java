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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
public class NavigationTest extends BaseTest {

    private String token;
    private String email;
    private final String password = "password123";
    @BeforeEach
    public void setupUser() {
        email = System.currentTimeMillis() + "@example.com";
        token = ApiBaseFun.createUser(email, password, "NavUser");
    }

    @Test
    @DisplayName("Проверь переход по клику на «Личный кабинет»")
        public void navigateToAccount() {
        new HomePage(driver)
                .open()
                .clickAccount()
                .fillEmail(email)
                .fillPassword(password)
                .clickLogin();
        System.out.println(driver.getCurrentUrl());
            assertTrue(driver.getCurrentUrl().contains("/account"));
    }
    @Test
    @DisplayName("Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers.")
    public void navigateToConstructorAndLogoFromAccount() {
        AccountPage account = new HomePage(driver)
                .open()
                .clickLogin()
                .fillEmail(email)
                .fillPassword(password)
                .clickLogin();

        // Переход в раздел «Конструктор»
        HomePage homeFromConstructor = account.clickConstructor();
        assertTrue(driver.getCurrentUrl().matches("https://stellarburgers.nomoreparties.site/"));

        // Переход по клику на логотип Stellar Burgers
        homeFromConstructor.clickLogo();
        assertTrue(driver.getCurrentUrl().matches("https://stellarburgers.nomoreparties.site/"));
    }

    @AfterEach
    public void cleanup() {
        if (token != null) {
            ApiBaseFun.deleteUser(token);
        }
    }
}
