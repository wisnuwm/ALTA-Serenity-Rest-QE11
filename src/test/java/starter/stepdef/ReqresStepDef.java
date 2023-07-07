package starter.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.reqres.ReqresAPI;
import starter.reqres.ReqresResponses;
import starter.utils.Constants;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class ReqresStepDef {
    @Steps
    ReqresAPI reqresAPI;

    //get list users
    @Given("Get list user with valid parameter page {int}")
    public void getListUserWithValidParameterPage(int page) {
        reqresAPI.getListUsers(page);
    }

    @When("Send request get list users")
    public void sendRequestGetListUsers() {
        SerenityRest.when().get(ReqresAPI.GET_LIST_USERS);
    }

    @Then("Status code should be {int} OK")
    public void statusCodeShouldBeOK(int ok) {
        SerenityRest.then().statusCode(ok);
    }

    @And("Response body page value should be {int}")
    public void responseBodyPageValueShouldBePage(int page) {
        SerenityRest.and().body(ReqresResponses.PAGE, equalTo(page));
    }

    @And("Validate get list user JSON schema")
    public void validateGetListUserJSONSchema() {
        File json = new File(Constants.JSON_SCHEMA + "/ListUsersSchema.json");
        SerenityRest.and().assertThat().body(JsonSchemaValidator.matchesJsonSchema(json));
    }

    @And("Get log body")
    public void getLogBody() {
        SerenityRest.and().log().body();
    }

    @And("Get log all")
    public void getLogAll() {
        SerenityRest.and().log().all();
    }

    @And("Print if status code {int} OK")
    public void printIfStatusCodeOK(int ok) {
        SerenityRest.and().log().ifStatusCodeIsEqualTo(ok);
    }

    //post create new user
    @Given("Post create user with valid json")
    public void postCreateUserWithValidJson() {
        File json = new File(Constants.REQ_BODY + "/User.json");
        reqresAPI.postCreateUser(json);
    }

    @When("Send request post create user")
    public void sendRequestPostCreateUser() {
        SerenityRest.when().post(ReqresAPI.BASE_PATH_API_USERS);
    }

    @Then("Status code should be {int} created")
    public void statusCodeShouldBeCreated(int created) {
        SerenityRest.then().statusCode(created);
    }

    @And("Response body name was {string} and job was {string}")
    public void responseBodyNameWasAndJobWas(String name, String job) {
        SerenityRest.and()
                .body(ReqresResponses.NAME, equalTo(name))
                .body(ReqresResponses.JOB, equalTo(job));
    }

    //put update user
    @Given("Put update user with valid id {int} and json")
    public void putUpdateUserWithValidIdIdAndJson(int id) {
        File json = new File(Constants.REQ_BODY + "/UpdateUser.json");
        reqresAPI.putUpdateUser(id, json);
    }

    @When("Send request put update user")
    public void sendRequestPutUpdateUser() {
        SerenityRest.when().put(ReqresAPI.PUT_UPDATE_USER);
    }

    //delete user
    @Given("Delete user with valid id {int}")
    public void deleteUserWithValidIdId(int id) {
        reqresAPI.deleteUser(id);
    }

    @When("Send request delete user")
    public void sendRequestDeleteUser() {
        SerenityRest.when().delete(ReqresAPI.DELETE_USER);
    }

    @Then("Status code should be {int} No Content")
    public void statusCodeShouldBeNoContent(int noContent) {
        SerenityRest.then().statusCode(noContent);
    }
}
