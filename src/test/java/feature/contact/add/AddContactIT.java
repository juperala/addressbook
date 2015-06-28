package feature.contact.add;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Test header for add feature.
 *
 * @author Juho Perälä
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/results/add"}
        , glue = {"steps"}
)
public class AddContactIT {
}