/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Osama Kheireddine
 */
public class addToCartStep {

    private WebDriver driver = null;

    @Given("^a user is logged in to the application$")
    public void a_user_is_logged_in_to_the_application() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/login.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/login.jsp");
        //login
        driver.findElement(By.xpath("//input[@id='inputUsername']")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("osama");
        driver.findElement(By.xpath("//input[@id='inputPassword']")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("osama");
        driver.findElement(By.xpath("//button[@value='login']")).click();
        //verify they are on the index page
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/index.jsp");
    }

    @When("^the user clicks the mens nav item$")
    public void the_user_clicks_the_mens_nav_item() throws Throwable {
        driver.findElement(By.xpath("//a[contains(text(),'MEN')]")).click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/pickMensCategory.jsp");
    }

    @When("^the user selects a category$")
    public void the_user_selects_a_category() throws Throwable {
        driver.findElement(By.xpath("//a[@id='selectMensHoodies']")).click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/productsOfCategory.jsp?categoryChosen=mens%20hoodies");
    }

    @When("^the user adds the item to their cart$")
    public void the_user_adds_the_item_to_their_cart() throws Throwable {
        driver.findElement(By.xpath("//a[contains(text(),'DummyProduct')]"));
        driver.findElement(By.xpath("//a[@id=addToCart]")).click();
    }

    @Then("^the user has added an item to their cart$")
    public void the_user_has_added_an_item_to_their_cart() throws Throwable {
        //go to the cart tab and view if their cart has something
//        47
        driver.findElement(By.xpath("//a[@id='cart']")).click();
        
    }
}
