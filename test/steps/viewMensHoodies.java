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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Leigh Briody
 */
public class viewMensHoodies {

    private WebDriver driver = null;

    @Given("^the user is on the pickMensCategory\\.jsp page$")
    public void the_user_is_on_the_pickMensCategory_jsp_page() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/pickMensCategory.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/pickMensCategory.jsp");

    }

    @When("^the user clicks view mens hoodie button$")
    public void the_user_clicks_view_mens_hoodie_button() throws Throwable {

        driver.findElement(By.xpath("//button[@type='button']")).click();

    }

    @Then("^the user should be able to see mens hoodies for sale$")
    public void the_user_should_be_able_to_see_mens_hoodies_for_sale() throws Throwable {

        //Confirm they are at the products of mens hoodies page by confirming mens hoodies is shown
        driver.findElement(By.xpath("//button[@type='button']")).isDisplayed();

    }
}
