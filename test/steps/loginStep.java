/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import static org.openqa.grid.common.SeleniumProtocol.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Leigh Briody
 */
public class loginStep {

    private WebDriver driver = null;

    @Given("^the user is on the login.jsp page$")
    public void the_index_jsp_page_has_launched() throws Throwable {

        //Way
        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/login.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/login.jsp");

    }

    @When("^the username and password is correct$")
    public void the_username_and_password_is_correct() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        driver.findElement(By.xpath("//input[@id='inputUsername']")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("osama");
        driver.findElement(By.xpath("//input[@id='inputPassword']")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("osama");
    }

    @When("^the login button is clicked$")
    public void the_login_button_is_clicked() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//button[@value='login']")).click();

    }

    @Then("^the user is brought to the index\\.jsp$")
    public void the_user_is_brought_to_the_index_jsp() throws Throwable {
      
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/index.jsp");
    }

    //When the username is incorrect and the password is correct
    @When("^the username is incorrect  and password is correct$")
    public void the_username_is_incorrect_and_password_is_correct() throws Throwable {
        driver.findElement(By.xpath("//input[@id='inputUsername']")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("incorrect");
        driver.findElement(By.xpath("//input[@id='inputPassword']")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("osama");
    }

    //When the uersname is correct but the password is incorrect
    @When("^the username is correct  and password is incorrect$")
    public void the_username_is_correct_and_password_is_incorrect() throws Throwable {

        driver.findElement(By.xpath("//input[@id='inputUsername']")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("osama");
        driver.findElement(By.xpath("//input[@id='inputPassword']")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("incorrect");
    }

    @Then("^the user should still be on the login\\.jsp$")
    public void the_user_should_still_be_on_the_login_jsp() throws Throwable {
          driver.findElement(By.id("inputPassword")).isDisplayed();
          driver.findElement(By.id("inputUsername")).isDisplayed();
    }

}
