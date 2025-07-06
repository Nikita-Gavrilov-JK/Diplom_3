package tests;

import api.ApiBaseFun;
import base.BaseTest;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.AccountPage;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
public class RegistrationTest extends BaseTest {
    private String token;

    @Test
    public void successfulRegistration() {
        String email = System.currentTimeMillis() + "@example.com";
        String password = "password123";

        HomePage home = new HomePage(driver).open();
        LoginPage login = home.clickLogin();
        RegistrationPage reg = login.goToRegister();

        LoginPage afterRegLogin = reg
                .fillName("User")
                .fillEmail(email)
                .fillPassword(password)
                .clickRegisterAndGoToLogin();

        //System.out.println(driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().contains("/login"));

//        AccountPage account = afterRegLogin
//                .fillEmail(email)
//                .fillPassword(password)
//                .clickLogin();
//        assertTrue(driver.getCurrentUrl().contains("/account"));

        // Получаем токен через API для удаления пользователя
        token = ApiBaseFun.loginUser(email, password);
    }
    @Test
    public void invalidPassword() {
        String email = "user" + System.currentTimeMillis() + "@example.com";

        RegistrationPage reg = new HomePage(driver).open()
                .clickLogin()
                .goToRegister();

        reg.fillName("User")
                .fillEmail(email)
                .fillPassword("123")
                .clickRegister();

        assertEquals("Некорректный пароль", reg.getIncorrectPasswordError());
    }
//Некорректный пароль

    @AfterEach
    public void cleanup() {
        if (token != null) {
            ApiBaseFun.deleteUser(token);
        }
    }
}