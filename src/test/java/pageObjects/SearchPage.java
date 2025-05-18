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
import java.util.ArrayList;
import java.util.List;

public class SearchPage {
    private WebDriver driver;

    private String nameResultsLocator = "span.a-title-section-leftMenu";
    private String categoriesLocator = "li > .a-category__listElement";
    private String consolasCategoriesLocator = "//a[text()='Consolas']";
    private String resultsArticlesLocator = "article.ipod-d-block";
    private String filtersLocator = "div.a-text__filter label";
    private String sizeTVLocator = "//input[contains(@id, \"variants.normalizedSize-55\")]";
    private String priceTVLocator =  "//input[contains(@id, \"variants.prices.sortPrice-10000-700000\")]";
    private String brandLocator = "//input[@id='brand-SONY']";
    private String brandInputLocator = "//input[contains(@id, \"searchBrand\")]";
    private String resultsTextLocator = ".a-plp-results-title";
    private String diorPerfumeLocator = "brand-DIOR";
    private String labelSelectedFilter = ".newSelectedFiltersContainer";

    private String expectedProduct = "Playstation";
    private String expectedProductGenre1 = "Consolas";
    private String expectedProductGenre2 = "Juegos";
    private String exactProduct = "PLAYSTATION 5";
    private String expectedFilter1 = "Tamaño";
    private String expectedFilter2 = "Precios";
    private String cleanResult = "";


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void validateTextProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement nameMainPlaystation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(nameResultsLocator)));
        String actualNameText =  nameMainPlaystation.getText();

        Assertions.assertEquals(expectedProduct, actualNameText);
    }
    ;
    public void validateCategories(){
        List<WebElement> category = driver.findElements(By.cssSelector(categoriesLocator));
        List<String> itemTexts = new ArrayList<>();

        for (WebElement itemText : category) {
            itemTexts.add(itemText.getText());
        }
        System.out.println(itemTexts);
        Assertions.assertTrue(itemTexts.contains(expectedProductGenre1) && itemTexts.contains(expectedProductGenre2));

        Allure.step("After looking up for Playstation");
        AllureUtils.takeScreenshot(driver);
    }

    public void clickOnConsolas(){
        WebElement consolas = driver.findElement(By.xpath(consolasCategoriesLocator));
        consolas.click();
    }

    public void searchInResults(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(resultsArticlesLocator)));
        List<WebElement> consolasTextGeneral = driver.findElements(By.cssSelector(resultsArticlesLocator));

        for (WebElement consoleText : consolasTextGeneral) {
            Assertions.assertTrue(consoleText.getText().contains("PLAYSTATION"));
            System.out.println(consoleText.getText());
        }

        Allure.step("After click on Consolas");
        AllureUtils.takeScreenshot(driver);

        for (WebElement consoleText : consolasTextGeneral) {
            String text = consoleText.getText().toUpperCase();
            if (text.contains(exactProduct)) {
                consoleText.click();
                System.out.println("Click en: " + text);
                break;
            }
        }
    }

    public void findCategories (){
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(filtersLocator)));

        List<WebElement> filters = driver.findElements(By.cssSelector(filtersLocator));
        List<String> itemTexts = new ArrayList<>();
        System.out.println(filters.size());

        for (WebElement filter : filters){
            itemTexts.add(filter.getText());
        }
        Assertions.assertTrue(itemTexts.contains(expectedFilter1) && itemTexts.contains(expectedFilter2));

        Allure.step("After validating categories");
        AllureUtils.takeScreenshot(driver);
    }


    public void selectFilterSizeTV(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sizeTVLocator)));
        WebElement sizeFilterCheck = driver.findElement(By.xpath(sizeTVLocator));
        sizeFilterCheck.click();

        Allure.step("Select by size");
        AllureUtils.takeScreenshot(driver);
    }

    public void selectFilterPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceTVLocator)));
        WebElement priceFilterCheck = driver.findElement(By.xpath(priceTVLocator));
        priceFilterCheck.click();

        Allure.step("Select Filter by price");
        AllureUtils.takeScreenshot(driver);
    }

    public void clickMoreBrandsFilter(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a#Marcas")));
        WebElement moreBrands = driver.findElement(By.cssSelector("a#Marcas"));
        moreBrands.click();

        Allure.step("After click on more brands on category brands");
        AllureUtils.takeScreenshot(driver);
    }

    public void selectFilterBrand() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(brandLocator)));
        WebElement priceFilterCheck = driver.findElement(By.xpath(brandLocator));
        priceFilterCheck.click();

        Allure.step("Select Filter By brand");
        AllureUtils.takeScreenshot(driver);
    }

    public void countResults(String number){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(resultsTextLocator)));
        WebElement resultsText = driver.findElement(By.cssSelector(resultsTextLocator));
        cleanResult = resultsText.getText().trim();
        cleanResult = cleanResult.replace("Productos", "").trim();
        System.out.println("Los resultados contados son: "+ cleanResult);
        Assertions.assertEquals( number, cleanResult);

        Allure.step("After validating results");
        AllureUtils.takeScreenshot(driver);
    }

    public void selectBrandDior(){
        WebElement perfume = driver.findElement(By.id(diorPerfumeLocator));
        perfume.click();

        Allure.step("Select Dior Brand");
        AllureUtils.takeScreenshot(driver);
    }

    public void validateFilterLabel(String expectedText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectedFiltersLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(labelSelectedFilter)));

        expectedText = expectedText.toUpperCase();

        String textFilter = selectedFiltersLabel.getText();
        Assertions.assertEquals(expectedText, textFilter);

        Allure.step("Final result");
        AllureUtils.takeScreenshot(driver);
    }
}
