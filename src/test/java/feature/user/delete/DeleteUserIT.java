package feature.user.delete;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Test header for DeleteUser feature.
 * @author Juho Perälä
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/results" }
        , glue = { "steps" }
)
public class DeleteUserIT {
}