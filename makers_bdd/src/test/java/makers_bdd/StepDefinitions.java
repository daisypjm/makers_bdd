package makers_bdd;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {

    private final WebDriver driver = new FirefoxDriver();

    @Given("I am on the Makers FAQ page")
    public void I_visit_faq_page() {
        driver.get("https://faq.makers.tech/en/knowledge");
    }

    @Given("I am on the Makers homepage")
    public void I_visit_home_page() {
        driver.get("https://makers.tech");
    }

    @When("I search for {string}")
    public void search_for(String query) throws InterruptedException {
        WebElement mainSearch = driver.findElement(By.className("kb-search__bar"));
        WebElement element = mainSearch.findElement(By.name("term"));
        element.sendKeys(query);
        element.submit();
        Thread.sleep(3000); // We should really use a dynamic wait!
    }

    @When("I click the {string} link")
    public void clickLink( String linkText) throws InterruptedException {
        WebElement link = driver.findElement(By.linkText(linkText));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", link);
        WebElement cookieBanner = driver.findElement(By.id("hs-eu-decline-button"));
        cookieBanner.click();
        Thread.sleep(3000);
        link.click();
    }

    @Then("the results header should mention {string}")
    public void checkTitle(String searchString) {
        WebElement h1 = driver.findElement(By.className("kb-search-results__heading"));
        assertTrue(h1.getText().contains(searchString));
    }

    @Then("I should be on the {string} page")
        public void titleCheck(String title) {
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains(title));

    }

    @And("the term {string} should appear in the URL")
    public void checkURL(String term) {
        String encodedTerm = URLEncoder.encode(term, StandardCharsets.UTF_8);
        String url = driver.getCurrentUrl();
        assertTrue(url.contains(encodedTerm), "Expected URL to contains: " + encodedTerm + " but got: " + url);
    }

    @After
    public void closeBrowser(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
        driver.quit();
    }
}