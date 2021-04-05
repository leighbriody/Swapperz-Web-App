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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Leigh Briody
 */
public class pickWomensCategoryStep {

    private WebDriver driver = null;

    @Given("^the user is on the index\\.jsp$")
    public void the_user_is_on_the_index_jsp() throws Throwable {
        //Way
        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/login.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/index.jsp");
    }

    @When("^the user clicks the WOMEN nav item$")

    public void the_user_clicks_the_WOMEN_nav_item() throws Throwable {

        driver.findElement(By.xpath("//a[contains(text(),'WOMEN')]")).click();

    }

    @Then("^the user should be brought to the pickWomensCategory\\.jsp page$")
    public void the_user_should_be_brought_to_the_pickWomensCategory_jsp_page() throws Throwable {

        //check we are on the mens category page url
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/pickWomensCategory.jsp");

    }

}
