package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ConstructorPage extends BasePage {
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Перейти на вкладку Булки")
    public ConstructorPage openBuns() {
        driver.findElement(bunsTab).click();
        waitUntilActive(bunsTab);
        return this;
    }

    @Step("Перейти на вкладку Соусы")
    public ConstructorPage openSauces() {
        driver.findElement(saucesTab).click();
        waitUntilActive(saucesTab);
        return this;
    }

    @Step("Перейти на вкладку Начинки")
    public ConstructorPage openFillings() {
        driver.findElement(fillingsTab).click();
        waitUntilActive(fillingsTab);
        return this;
    }

    @Step("Проверить, что вкладка {tabName} активна")
    public boolean isTabActive(String tabName) {
        By tabContainer = By.xpath(
                "//div[contains(@class,'tab_tab')][.//span[text()='" + tabName + "']]"
        );
        String classes = driver.findElement(tabContainer).getAttribute("class");
        return classes.contains("tab_tab_type_current");
    }

    private void waitUntilActive(By tabLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(
                        By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='"
                                + driver.findElement(tabLocator).getText() + "']]"),
                        "class",
                        "tab_tab_type_current"
                ));
    }
}
