package feature.contact.update;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Test header for update feature.
 *
 * @author Juho Perälä
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/results/update" }
        , glue = { "steps" }
)
public class UpdateContactIT {
}