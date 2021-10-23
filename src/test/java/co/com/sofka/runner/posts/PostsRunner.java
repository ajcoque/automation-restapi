package co.com.sofka.runner.posts;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/posts/post.feature"},
        glue = {"co.com.sofka.stepdefinition.posts"},
        tags = {"not @ignore"}
)

public class PostsRunner {
}
