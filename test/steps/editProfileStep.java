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
public class editProfileStep {

    private WebDriver driver = null;

    @Given("^a user has logged in$")
    public void a_user_has_logged_in() throws Throwable {
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

    @Given("^the user is on their profile$")
    public void the_user_is_on_their_profile() throws Throwable {
        driver.findElement(By.xpath("//a[contains(text(),'OSAMA')]")).click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/myProfile.jsp");
        //to confirm we are on the correct user profile get some information from it
        String name = "OSAMA KHEIREDDINE";
        String result = driver.findElement(By.xpath("//p[@id='fName']")).getText();
        assertEquals(name, result);
    }

    @When("^the user clicks edit profile$")
    public void the_user_clicks_edit_profile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//a[@id='editProfile']")).click();
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/editProfile.jsp");
    }

    @When("^the user supplies correct information$")
    public void the_user_supplies_correct_information() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//input[@name='fName']")).click();
        driver.findElement(By.name("fName")).clear();
        driver.findElement(By.name("fName")).sendKeys("Bill");
        driver.findElement(By.xpath("//input[@name='county']")).click();
        driver.findElement(By.name("county")).clear();
        driver.findElement(By.name("county")).sendKeys("Meath");
        driver.findElement(By.xpath("//input[@value='Change']")).click();
    }

    @Then("^the user has successfully updated their profile$")
    public void the_user_has_successfully_updated_their_profile() throws Throwable {
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/editProfile.jsp");
        //ASSERT THE SUCCESSMSG
        String successMsg = "Your details were successfully updated!";
        String result = driver.findElement(By.xpath("//h4[@id='successMessage']")).getText();
        assertEquals(successMsg, result);
    }

    @When("^the user supplies an invalid mobile$")
    public void the_user_supplies_an_invalid_mobile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.xpath("//input[@name='phone']")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("incorrectNumber");
        driver.findElement(By.xpath("//input[@name='county']")).click();
        driver.findElement(By.name("county")).clear();
        driver.findElement(By.name("county")).sendKeys("Meath");
        driver.findElement(By.xpath("//input[@value='Change']")).click();
    }

    @Then("^the user has unsuccessfully updated their profile$")
    public void the_user_has_unsuccessfully_updated_their_profile() throws Throwable {
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/editProfile.jsp");
        //ASSERT THE SUCCESSMSG
        String successMsg = "An error occurred whilst updating your details... Please try again later.";
        String result = driver.findElement(By.xpath("//h4[@id='errorMessage']")).getText();
        assertEquals(successMsg, result);
    }
}
