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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Osama Kheireddine
 */
public class sellProductStep {

    private WebDriver driver = null;

    @Given("^a user is logged in$")
    public void a_user_is_logged_in() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //Way
        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/login.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

    @When("^the user clicks the sell tab$")
    public void the_user_clicks_the_sell_tab() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//a[contains(text(),'SELL')]")).click();
    }

    @When("^the user fills out their advertisement details$")
    public void the_user_fills_out_their_advertisement_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        //upload image
        WebElement uploadElement = driver.findElement(By.id("file"));
        uploadElement.sendKeys("C:\\SwapperzWebApplication\\web\\images\\dummyPhoto.png");

        //name
        driver.findElement(By.xpath("//input[@id='productName']")).click();
        driver.findElement(By.id("productName")).sendKeys("DummyProduct");
        //product price
        driver.findElement(By.xpath("//input[@id='price']")).click();
        driver.findElement(By.id("price")).sendKeys("1.0");
        //add description
        driver.findElement(By.xpath("//textarea[@id='productDescription']")).click();
        driver.findElement(By.id("productDescription")).sendKeys("This is a dummy product, please ignore this.");
        //select colour
        Select colour = new Select(driver.findElement(By.name("colour")));
        colour.selectByIndex(0);
        //select Brand
        Select brand = new Select(driver.findElement(By.name("brand")));
        brand.selectByIndex(0);
        //select size
        Select size = new Select(driver.findElement(By.name("size")));
        size.selectByIndex(0);
        //select productCondition
        Select productCondition = new Select(driver.findElement(By.name("productCondition")));
        productCondition.selectByIndex(0);
        //select productGender
        Select productGender = new Select(driver.findElement(By.name("productGender")));
        productGender.selectByIndex(0);
        //select productCategory
        Select productCategory = new Select(driver.findElement(By.name("productCategory")));
        productCategory.selectByIndex(0);
    }

    @When("^the user clicks the sell product input$")
    public void the_user_clicks_the_sell_product_input() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//input[@value='Place Ad']")).click();
    }

    @Then("^they should have a listed advertisement$")
    public void they_should_have_a_listed_advertisement() throws Throwable {
        //GET SUCCESS MESSAGE & URL
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/ownListedProducts.jsp");
        String successMessage = "Your Ad Was Listed Successfully !!";
        String result = driver.findElement(By.xpath("//h1[@id='successMessage']")).getText();
        assertEquals(result, successMessage);
    }

    @When("^the user fills out their advertisement details with an incorrect integer$")
    public void the_user_fills_out_their_advertisement_details_with_an_incorrect_integer() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //upload image
        WebElement uploadElement = driver.findElement(By.id("file"));
        uploadElement.sendKeys("C:\\SwapperzWebApplication\\web\\images\\dummyPhoto.png");

        //name
        driver.findElement(By.xpath("//input[@id='productName']")).click();
        driver.findElement(By.id("productName")).sendKeys("DummyProduct");
        //product price
        driver.findElement(By.xpath("//input[@id='price']")).click();
        driver.findElement(By.id("price")).sendKeys("incorrectInteger");
        //add description
        driver.findElement(By.xpath("//textarea[@id='productDescription']")).click();
        driver.findElement(By.id("productDescription")).sendKeys("This is a dummy product, please ignore this.");
        //select colour
        Select colour = new Select(driver.findElement(By.name("colour")));
        colour.selectByIndex(0);
        //select Brand
        Select brand = new Select(driver.findElement(By.name("brand")));
        brand.selectByIndex(0);
        //select size
        Select size = new Select(driver.findElement(By.name("size")));
        size.selectByIndex(0);
        //select productCondition
        Select productCondition = new Select(driver.findElement(By.name("productCondition")));
        productCondition.selectByIndex(0);
        //select productGender
        Select productGender = new Select(driver.findElement(By.name("productGender")));
        productGender.selectByIndex(0);
        //select productCategory
        Select productCategory = new Select(driver.findElement(By.name("productCategory")));
        productCategory.selectByIndex(0);
    }

    @Then("^they should be an error message$")
    public void they_should_be_an_error_message() throws Throwable {
        //check the url and the error message
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/sellProduct.jsp");
        String expErrorMsg = "Please enter a valid price.";
        String result = driver.findElement(By.xpath("//div[@id='errorMessage']")).getText();
        assertEquals(expErrorMsg, result);
    }
    

}
