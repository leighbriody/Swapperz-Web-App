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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Leigh Briody
 */
public class registerStep {

    private WebDriver driver = null;

    @Given("^the user is on the register\\.jsp page$")
    public void the_user_is_on_the_register_jsp_page() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "C:/SwapperzWebApplication/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://localhost:8084/SwapperzWebApplication/register.jsp";
        driver.get(baseUrl);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8084/SwapperzWebApplication/register.jsp");

    }

    @When("^i fill in my details$")
    public void i_fill_in_my_details() throws Throwable {
        //Rrgister form details

        //profile picture
        WebElement uploadElement = driver.findElement(By.id("inputProfilePic"));
        uploadElement.sendKeys("C:\\SwapperzWebApplication\\web\\images\\profilePictures\\billGates.jpg");

        //first name
        driver.findElement(By.xpath("//input[@id='inputFirstName']")).click();
        driver.findElement(By.id("inputFirstName")).sendKeys("Bill");

        //last name 
        driver.findElement(By.xpath("//input[@id='inputLastName']")).click();
        driver.findElement(By.id("inputLastName")).sendKeys("Gates");
        //email 
        driver.findElement(By.xpath("//input[@id='inputEmail']")).click();
        driver.findElement(By.id("inputEmail")).sendKeys("billg@gmail.com");

        //phone 
        driver.findElement(By.xpath("//input[@id='inputPhone']")).click();
        driver.findElement(By.id("inputPhone")).sendKeys("09838323");
        //add line 1 
        driver.findElement(By.xpath("//input[@id='inputAddressLine1']")).click();
        driver.findElement(By.id("inputAddressLine1")).sendKeys("123 the lane");

        //add line 2 
        driver.findElement(By.xpath("//input[@id='inputAddressLine2']")).click();
        driver.findElement(By.id("inputAddressLine2")).sendKeys("the drive");
        //town 
        driver.findElement(By.xpath("//input[@id='inputTown']")).click();
        driver.findElement(By.id("inputTown")).sendKeys("Drogheda");
        //county
        driver.findElement(By.id("inputCounty")).click();
        driver.findElement(By.id("inputCounty")).sendKeys("Louth");
        //country
        driver.findElement(By.xpath("//input[@id='Country']")).click();
        driver.findElement(By.id("Country")).sendKeys("Ireland");
        //gender
        Select gender = new Select(driver.findElement(By.name("gender")));
        gender.selectByIndex(1);

        //security question1
        Select securityQuestion = new Select(driver.findElement(By.name("securityQuestion1")));
        securityQuestion.selectByIndex(0);
        //awnser
        driver.findElement(By.xpath("//input[@name='answer1']")).click();
        driver.findElement(By.name("answer1")).sendKeys("rex");
        //security question 2
        Select securityQuestion2 = new Select(driver.findElement(By.name("securityQuestion2")));
        securityQuestion2.selectByIndex(1);
        //awnser 2
        driver.findElement(By.xpath("//input[@name='answer2']")).click();
        driver.findElement(By.name("answer2")).sendKeys("St Olivers");
        //security question 3
        Select securityQuestion3 = new Select(driver.findElement(By.name("securityQuestion3")));
        securityQuestion.selectByIndex(2);
        //awnser 3
        driver.findElement(By.xpath("//input[@name='answer3']")).click();
        driver.findElement(By.name("answer3")).sendKeys("awnser 3");
        //username
        driver.findElement(By.xpath("//input[@id='inputUsername']")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("billgates20");
        //password
        driver.findElement(By.xpath("//input[@id='inputPassword']")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("billgates20");
    }

    @When("^i click the register button$")
    public void i_click_the_register_button() throws Throwable {
        driver.findElement(By.xpath("//button[contains(.,'Register')]")).click();

    }

    @Then("^i should be on the login\\.jsp$")
    public void i_should_be_on_the_login_jsp() throws Throwable {

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/login.jsp");
    }

    ///Here are steps for incorrect phone number brining user to the error page
    @When("^i fill in my details with incorrect phone number$")
    public void i_fill_in_my_details_with_incorrect_phone_number() throws Throwable {
        //Rrgister form details

        //profile picture
        WebElement uploadElement = driver.findElement(By.id("inputProfilePic"));
        uploadElement.sendKeys("C:\\SwapperzWebApplication\\web\\images\\profilePictures\\billGates.jpg");

        //first name
        driver.findElement(By.xpath("//input[@id='inputFirstName']")).click();
        driver.findElement(By.id("inputFirstName")).sendKeys("Anto");

        //last name 
        driver.findElement(By.xpath("//input[@id='inputLastName']")).click();
        driver.findElement(By.id("inputLastName")).sendKeys("Gates");
        //email 
        driver.findElement(By.xpath("//input[@id='inputEmail']")).click();
        driver.findElement(By.id("inputEmail")).sendKeys("billg@gmail.com");

        //phone INCORRECT PHONE TOO LONG
        driver.findElement(By.xpath("//input[@id='inputPhone']")).click();
        driver.findElement(By.id("inputPhone")).sendKeys("09838333333323");
        //add line 1 
        driver.findElement(By.xpath("//input[@id='inputAddressLine1']")).click();
        driver.findElement(By.id("inputAddressLine1")).sendKeys("123 the lane");

        //add line 2 
        driver.findElement(By.xpath("//input[@id='inputAddressLine2']")).click();
        driver.findElement(By.id("inputAddressLine2")).sendKeys("the drive");
        //town 
        driver.findElement(By.xpath("//input[@id='inputTown']")).click();
        driver.findElement(By.id("inputTown")).sendKeys("Drogheda");
        //county
        driver.findElement(By.id("inputCounty")).click();
        driver.findElement(By.id("inputCounty")).sendKeys("Louth");
        //country
        driver.findElement(By.xpath("//input[@id='Country']")).click();
        driver.findElement(By.id("Country")).sendKeys("Ireland");
        //gender
        Select gender = new Select(driver.findElement(By.name("gender")));
        gender.selectByIndex(1);

        //security question1
        Select securityQuestion = new Select(driver.findElement(By.name("securityQuestion1")));
        securityQuestion.selectByIndex(0);
        //awnser
        driver.findElement(By.xpath("//input[@name='answer1']")).click();
        driver.findElement(By.name("answer1")).sendKeys("rex");
        //security question 2
        Select securityQuestion2 = new Select(driver.findElement(By.name("securityQuestion2")));
        securityQuestion2.selectByIndex(1);
        //awnser 2
        driver.findElement(By.xpath("//input[@name='answer2']")).click();
        driver.findElement(By.name("answer2")).sendKeys("St Olivers");
        //security question 3
        Select securityQuestion3 = new Select(driver.findElement(By.name("securityQuestion3")));
        securityQuestion.selectByIndex(2);
        //awnser 3
        driver.findElement(By.xpath("//input[@name='answer3']")).click();
        driver.findElement(By.name("answer3")).sendKeys("awnser 3");
        //username
        driver.findElement(By.xpath("//input[@id='inputUsername']")).click();
        driver.findElement(By.id("inputUsername")).sendKeys("anto123");
        //password
        driver.findElement(By.xpath("//input[@id='inputPassword']")).click();
        driver.findElement(By.id("inputPassword")).sendKeys("billgates20");
    }

    @Then("^i should still be on the register\\.jsp page$")
    public void i_should_still_be_on_the_register_jsp_page() throws Throwable {

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://localhost:8084/SwapperzWebApplication/register.jsp");
    }

    @Then("^there should be an error message$")
    public void there_should_be_an_error_message() throws Throwable {
        
        
        driver.findElement(By.xpath("//h4[contains(.,'An error occurred when registering, please try again.')]")).isDisplayed();
    }

}
