package co.com.sofka.stepdefinition.setup;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.log4j.PropertyConfigurator;

import java.nio.charset.StandardCharsets;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;

public class Setup {
    protected static final String URL_BASE = "https://jsonplaceholder.typicode.com";
    protected static final String RESOURCE_POST = "/posts/1";
    protected static final String RESOURCE_POSTCOMMENT = "/comments?postId=1";
    protected static final String USER_DIR = System.getProperty("user.dir");

    protected Actor actor;

    private void setUpActor() {
        actor = Actor.named("Usuario1");
    }

    private void setUpActorAndApi() {
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected void setUp() {
        setUpLog4j2();
        setUpActor();
        setUpActorAndApi();
    }

    protected String fromLastResponseBy(Actor actor) {
        return new String(
                LastResponse.received().answeredBy(actor).asByteArray(),
                StandardCharsets.UTF_8
        );
    }

    protected void setUpLog4j2() {
        PropertyConfigurator.configure(USER_DIR + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }
}
