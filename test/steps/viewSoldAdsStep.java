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
public class viewSoldAdsStep {
    
    private WebDriver driver = null;
    
    @Given("^the user is logged on their profile$")
    public void the_user_is_logged_on_their_profile() throws Throwable {
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
        //now take the user to their profile
        driver.findElement(By.xpath("//a[contains(text(),'OSAMA')]")).click();
        URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/myProfile.jsp");
        //to confirm we are on the correct user profile get some information from it
        String name = "OSAMA KHEIREDDINE";
        String result = driver.findElement(By.xpath("//p[@id='fName']")).getText();
        assertEquals(name, result);
    }

    @When("^the user clicks view sold ads$")
    public void the_user_clicks_view_sold_ads() throws Throwable {
         driver.findElement(By.xpath("//a[@id='soldAdvertisements']")).click();
    }

    @Then("^the user will be able to see their sold ads$")
    public void the_user_will_be_able_to_see_their_sold_ads() throws Throwable {
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/myProfile.jsp?adds=sold");
    }
}
