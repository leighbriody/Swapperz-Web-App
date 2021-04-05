/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import static javafx.scene.input.KeyCode.F;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Osama Kheireddine
 */
public class addBrandStep {

    private WebDriver driver = null;

    @Given("^an admin is on the login\\.jsp page$")
    public void an_admin_is_on_the_login_jsp_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //Way
        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/login.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/login.jsp");
    }

    @When("^the admin logs in$")
    public void the_admin_logs_in() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//input[@id='inputUsername']")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("osama");
        driver.findElement(By.xpath("//input[@id='inputPassword']")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("osama");

        driver.findElement(By.xpath("//button[@value='login']")).click();
    }

    @When("^the admin is on the index\\.jsp page$")
    public void the_admin_is_on_the_index_jsp_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/index.jsp");
    }

    @When("^the admin clicks the ADMIN$")
    public void the_admin_clicks_the_ADMIN() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//button[@id='adminButton']")).click();
    }

    @When("^the admin clicks ADD BRAND button$")
    public void the_admin_clicks_ADD_BRAND_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//a[contains(text(),'ADD BRAND')]")).click();
    }

    @When("^the admin enters a correctly inputted brand$")
    public void the_admin_enters_a_correctly_inputted_brand() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//input[@id='newBrand']")).click();
        driver.findElement(By.id("newBrand")).sendKeys("Jordan");
    }

    @When("^the admin clicks the add brand button$")
    public void the_admin_clicks_the_add_brand_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//button[@value='addBrand']")).click();
    }

    @Then("^the admin will be notified of a successful brand insert$")
    public void the_admin_will_be_notified_of_a_successful_brand_insert() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //get the success message
        String successMessage = "New Brand Jordan Added !";
        String result = driver.findElement(By.xpath("//h4[@id='successMessage']")).getText();
        assertEquals(result, successMessage);
    }

}
