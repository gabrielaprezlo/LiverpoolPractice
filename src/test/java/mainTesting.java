import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.DetailPage;
import pageObjects.LandingPage;
import pageObjects.SearchPage;
import io.qameta.allure.junit5.AllureJunit5;


@ExtendWith(AllureJunit5.class)
public class mainTesting {
    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void searchPlayStation5 () {
        driver.get("https://www.liverpool.com.mx/tienda/home");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.writeProductOnSearchBarAndClick("playstation");

        SearchPage searchPage = new SearchPage(driver);
        searchPage.validateTextProduct();
        searchPage.validateCategories();
        searchPage.clickOnConsolas();
        searchPage.searchInResults();

        DetailPage detailPage = new DetailPage(driver);
        detailPage.validateTitle();
        detailPage.validatePrice();
    }

    @Test
    public void searchSmartTV() throws InterruptedException {
        driver.get("https://www.liverpool.com.mx/tienda/home");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.writeProductOnSearchBarAndClick("smart tv");

        SearchPage searchPage  = new SearchPage(driver);
        searchPage.findCategories();
        searchPage.selectFilterPrice();
        Thread.sleep(2000);
        searchPage.clickMoreBrandsFilter();
        Thread.sleep(2000);
        searchPage.selectFilterBrand();
        Thread.sleep(2000);
        searchPage.selectFilterSizeTV();
        Thread.sleep(2000);
        searchPage.countResults("2");
    }

    @Test
    public void searchDiorPerfum () {
        driver.get("https://www.liverpool.com.mx/tienda/home");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.displayCategories();
        landingPage.displayBeauty();

        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickMoreBrandsFilter();
        searchPage.selectBrandDior();
        searchPage.validateFilterLabel("Dior");
    }
}
