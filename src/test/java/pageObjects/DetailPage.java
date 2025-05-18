package pageObjects;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

import java.time.Duration;

public class DetailPage {
    private WebDriver driver;

    private String titleProductLocator = "h1.a-product__information--title";
    private String priceLocator = "div.m-product__price--collection";
    private String expectedProduct = "Consola PS5 Pro de 2 TB Edición Digital";
    private String expectedPrice = "$19,299";

    public DetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void validateTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(titleProductLocator)));
        Assertions.assertEquals(expectedProduct, title.getText());

        Allure.step("Validate Title");
        AllureUtils.takeScreenshot(driver);
    }

    public void validatePrice(){
        WebElement wholePrice = driver.findElement(By.cssSelector(priceLocator));
        String priceText = wholePrice.getText().trim();
        String[] priceParts = priceText.split("\n");
        String priceClean = priceParts[0].trim();
        Assertions.assertEquals(expectedPrice, priceClean);
        System.out.println(priceClean);

        Allure.step("Validate price");
        AllureUtils.takeScreenshot(driver);
    }

}
