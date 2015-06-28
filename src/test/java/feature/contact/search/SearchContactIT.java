package feature.contact.search;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Test header for search feature.
 *
 * @author Juho Perälä
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/results/search"}
        , glue = {"steps"}
)
public class SearchContactIT {
}