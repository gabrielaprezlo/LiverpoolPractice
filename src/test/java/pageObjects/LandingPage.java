package pageObjects;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

import java.time.Duration;

public class LandingPage {
    private WebDriver driver;

    private String searchBarLocator = "mainSearchbar";
    private String categoriesLocator = "//span[text()='Categorías']";
    private String bellezaLocator = "//li[@data-submenu-id='CAT5020010']//a[contains(text(), 'Belleza')]";
    private String manPerfumeLocator = "//div[@class='a-thrd-catTitle']//a[contains(text(), 'Perfumes Hombre')]";

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void writeProductOnSearchBarAndClick(String product){
        driver.findElement(By.id(searchBarLocator)).sendKeys(product, Keys.ENTER);

        Allure.step("Look for product");
        AllureUtils.takeScreenshot(driver);
    }

    public void displayCategories(){
        driver.manage().window().maximize();
        WebElement categories = driver.findElement(By.xpath(categoriesLocator));
        categories.click();

        Allure.step("Click categories");
        AllureUtils.takeScreenshot(driver);
    }

    public void displayBeauty (){
        WebElement beauty = driver.findElement(By.xpath(bellezaLocator));
        Actions actions = new Actions(driver);
        actions.moveToElement(beauty).perform();

        Allure.step("Display beauty");
        AllureUtils.takeScreenshot(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement perfume = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(manPerfumeLocator)));

        perfume.click();
    }
}
