package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "APISteps",
        //when it is true, it generates step def which is missing
        //when it is false, it executes the code
        dryRun = false,
        tags = " @api",
        plugin = {"pretty","html:target/cucumber.html","json:target/cucumber.json",
               //to execute failed test cases one more time
       "rerun:target/failed.txt"}

)


public class APIRunner {

}
