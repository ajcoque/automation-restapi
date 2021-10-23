package co.com.sofka.tasks.posts;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import java.util.Map;

public class DoGet implements Task{

    private String resource;
    private Map<String, Object> headers;
    private String bodyRequest;

    public DoGet usingThe(String resource) {
        this.resource = resource;
        return this;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
                        .with(
                                req -> req.contentType(ContentType.JSON)
                        )
        );
    }

    public static DoGet doGet(){
        return new DoGet();
    }

}
