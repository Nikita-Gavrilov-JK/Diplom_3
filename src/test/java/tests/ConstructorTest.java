package tests;

import base.BaseTest;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobject.ConstructorPage;
import pageobject.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(AllureJunit5.class)
public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Проверь, что работают переходы к разделам")
    public void navigateBetweenIngredientTabs() {

        ConstructorPage constructorPage = new HomePage(driver)
                .open()
                .clickConstructor();

        constructorPage.openSauces();
        assertTrue(constructorPage.isTabActive("Соусы"));

        constructorPage.openFillings();
        assertTrue(constructorPage.isTabActive("Начинки"));

        constructorPage.openBuns();
        assertTrue(constructorPage.isTabActive("Булки"));
    }
}
