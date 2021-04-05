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
public class pickMensCategoryStep {

    private WebDriver driver = null;

    //Steeps
    @Given("^the user is on the index\\.jsp page$")
    public void the_user_is_on_the_index_jsp_page() throws Throwable {
        //Way
        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/login.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/index.jsp");

    }

    @When("^the MEN nav item is clicked$")
    public void the_MEN_nav_item_is_clicked() throws Throwable {

        driver.findElement(By.xpath("//a[contains(text(),'MEN')]")).click();

    }

    @Then("^the user is brought to the pickMensCategory\\.jsp page$")
    public void the_user_is_brought_to_the_pickMensCategory_jsp_page() throws Throwable {

        //check we are on the mens category page url
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/pickMensCategory.jsp");
    }
}
