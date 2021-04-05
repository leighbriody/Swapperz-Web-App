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
public class viewWomensHoodies {

    private WebDriver driver = null;

    @Given("^the user is on the pickWomensCategory\\.jsp page$")
    public void the_user_is_on_the_pickWomensCategory_jsp_page() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/pickWomensCategory.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/pickWomensCategory.jsp");

    }

    @When("^the user clicks view womens hoodies button$")
    public void the_user_clicks_view_womens_hoodies_button() throws Throwable {

        driver.findElement(By.cssSelector(".col-md-4:nth-child(2) .btn")).click();
    }

    @Then("^the user should be able to see womens hoodies for sale$")
    public void the_user_should_be_able_to_see_womens_hoodies_for_sale() throws Throwable {
        
        //Confirm we are on the womens hoodies page
        //check we are on the mens category page url
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/productsOfCategory.jsp?categoryChosen=womens%20hoodies");

    }

}
