package tests;

import api.ApiBaseFun;
import base.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobject.AccountPage;
import pageobject.ConstructorPage;
import pageobject.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
