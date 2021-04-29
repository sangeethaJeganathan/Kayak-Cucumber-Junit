import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/KayakFeatures"},
                    glue={"KayakStepDefinitions","KayakHooks"},
                    plugin = {"pretty",
                                "junit:target/MyReports/report.xml","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
                    )
public class KayakRunnerTest {


}
