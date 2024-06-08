package qa.mobile.acceptancetests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(tags = "@Escenario:E2E",
        plugin = {"pretty", "json:target/destination/cucumber.json"},
        features = "src/test/resources/features/",
        glue = "qa.mobile.steps")
public class AcceptanceTestSuite {
}
