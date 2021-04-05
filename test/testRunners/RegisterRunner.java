/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testRunners;

/**
 *
 * @author Leigh Briody
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
 features = "C:/SwapperzWebApplication/test/feature/Register.feature"
 ,glue={"steps"}
 )
public class RegisterRunner {

}
