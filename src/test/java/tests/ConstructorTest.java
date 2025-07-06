package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pageobject.ConstructorPage;
import pageobject.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    public void navigateBetweenIngredientTabs() {

        HomePage home = new HomePage(driver).open();
        home.clickConstructor();
        ConstructorPage constructorPage = new ConstructorPage(driver);

        constructorPage.openSauces();
        assertTrue(constructorPage.isTabActive("Соусы"));

        constructorPage.openFillings();
        assertTrue(constructorPage.isTabActive("Начинки"));

        constructorPage.openBuns();
        assertTrue(constructorPage.isTabActive("Булки"));
    }
}
