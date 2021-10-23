package co.com.sofka.stepdefinition.posts;

import co.com.sofka.exceptions.posts.ValidationNotEquals;
import co.com.sofka.model.posts.PostModel;
import co.com.sofka.model.posts.comments.CommentModel;
import co.com.sofka.stepdefinition.setup.Setup;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static co.com.sofka.exceptions.posts.ValidationNotEquals.VALIDATION_IS_NOT_EQUALS;
import static co.com.sofka.questions.posts.ReturnStringValue.systemValue;
import static co.com.sofka.tasks.posts.DoGet.doGet;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

import java.lang.reflect.Type;

public class PostsCucumberStepDefinition extends Setup {

    private static final String POST_JSON = System.getProperty("user.dir") + "\\src\\test\\resources\\posts\\post.json";
    private static final String POSTCOMMENT_JSON = System.getProperty("user.dir") + "\\src\\test\\resources\\posts\\postsComments.json";
    private String bodyRequest;
    private String bodyRequestPostComment;
    private static final Gson gson = new Gson();
    private static final Type typeListaCommentPost = new TypeToken<List<CommentModel>>() {
    }.getType();
    private List<CommentModel> commentsPostEsperado;
    private List<CommentModel> commentsPostRecibido;
    private PostModel postModelEsperado;
    private PostModel postModelRecibido;

    @Given("el usuario se encuentra en la pagina de inicio del aplicativo web")
    public void elUsuarioSeEncuentraEnLaPaginaDeInicioDelAplicativoWeb() {
        setUp();
        bodyRequest = defineBodyRequest(POST_JSON);
        bodyRequestPostComment = defineBodyRequest(POSTCOMMENT_JSON);
    }

    @When("el usuario ingrese el id del post que desea consultar")
    public void elUsuarioIngreseElIdDelPostQueDeseaConsultar() {
        actor.attemptsTo(
                doGet().usingThe(RESOURCE_POST)
        );
    }

    @Then("el sistema le muestra la informacion relacionada al post con el id proporcionado")
    public void elSistemaLeMuestraLaInformacionRelacionadaAlPostConElIdProporcionado() {

        postModelEsperado = gson.fromJson(bodyRequest, PostModel.class);
        postModelRecibido = gson.fromJson(fromLastResponseBy(actor), PostModel.class);

        actor.should(

                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la consulta del post debe ser: ",
                        systemValue(postModelRecibido.toString()),
                        containsString(postModelEsperado.toString())
                )
        );
    }


    @When("el usuario ingrese el id del post del que desea obtener los comentarios")
    public void elUsuarioIngreseElIdDelPostDelQueDeseaObtenerLosComentarios() {
        actor.attemptsTo(
                doGet().usingThe(RESOURCE_POSTCOMMENT)
        );
    }

    @Then("el sistema le muestra la informacion relacionada con los comentarios del post con el id proporcionado")
    public void elSistemaLeMuestraLaInformacionRelacionadaConLosComentariosDelPostConElIdProporcionado() {

        commentsPostEsperado = gson.fromJson(bodyRequestPostComment, typeListaCommentPost);
        commentsPostRecibido = gson.fromJson(fromLastResponseBy(actor), typeListaCommentPost);

        actor.should(

                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la consulta del post debe ser: ",
                        systemValue(commentsPostRecibido.toString()),
                        containsString(commentsPostEsperado.toString())
                )
                .orComplainWith(
                        ValidationNotEquals.class,
                        String.format(VALIDATION_IS_NOT_EQUALS, commentsPostRecibido.toString())
                )
        );
    }


    private String defineBodyRequest(String json) {
        return readFile(json);
    }

}
