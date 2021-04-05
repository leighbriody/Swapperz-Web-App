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
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Leigh Briody
 */
public class addToCartStep_1 {

    private WebDriver driver = null;

    @Given("^a user is on the login\\.jsp page$")
    public void a_user_is_on_the_login_jsp_page() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/index.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/login.jsp");
    }

    @When("^the username and password is put in correctly$")
    public void the_username_and_password_is_put_in_correctly() throws Throwable {

        driver.findElement(By.id("inputUsername")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("kyle");
        driver.findElement(By.id("inputPassword")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("kyle");

    }

    @When("^the login button is pressed$")
    public void the_login_button_is_pressed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//button[@value='login']")).click();

    }

    @When("^the MEN  nav item is clicked$")
    public void the_MEN_nav_item_is_clicked() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        //Some reason not working
        // driver.findElement(By.xpath("//a[contains(@href, 'pickMensCategory.jsp')]")).click();
        //Using a javascript based alternative instead
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement Category_Body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'pickMensCategory.jsp')]")));
        Category_Body.click();
    }

    @Then("^the pickMensCategory\\.jsp should be displayed$")
    public void the_pickMensCategory_jsp_should_be_displayed() throws Throwable {
        //check we are on the mens category page url
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/pickMensCategory.jsp");
    }
}
