package tests;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import models.Credentials;
import models.User;
import org.junit.jupiter.api.Test;

import static constants.ReqresEndpoints.*;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static specs.BaseSpec.requestSpecification;

public class ReqresTests extends BaseTest {

    private static final String EMAIL = "eve.holt@reqres.in";

    @Test
    public void successfulRegistration() {
        step("Registering a user", () -> {
            Credentials body = new Credentials(
                    EMAIL,
                    "pistol");
            ValidatableResponse response = RestAssured.given()
                    .spec(requestSpecification)
                    .body(body)
                    .when()
                    .post(REGISTER)
                    .then()
                    .statusCode(200)
                    .body("$", hasKey("id"))
                    .body("$", hasKey("token"));
        });
    }

    @Test
    public void unsuccessfulRegistrationNoEmail() {
        step("Registering a user without email", () -> {
            Credentials body = new Credentials(
                    null,
                    "pistol");
            RestAssured.given()
                    .spec(requestSpecification)
                    .body(body)
                    .when()
                    .post(REGISTER)
                    .then()
                    .statusCode(400)
                    .body("error", is("Missing email or username"));
        });
    }

    @Test
    public void successfulLogin() {
        step("Logging in", () -> {
            Credentials body = new Credentials(
                    EMAIL,
                    "cityslicka");
            RestAssured.given()
                    .spec(requestSpecification)
                    .body(body)
                    .when()
                    .post(LOGIN)
                    .then()
                    .statusCode(200)
                    .body("$", hasKey("token"));
        });
    }

    @Test
    public void unsuccessfulLoginNoPassword() {
        step("Logging in without password", () -> {
            Credentials body = new Credentials(
                    EMAIL,
                    null);
            RestAssured.given()
                    .spec(requestSpecification)
                    .body(body)
                    .when()
                    .post(LOGIN)
                    .then()
                    .statusCode(400)
                    .body("error", is("Missing password"));
        });
    }

    @Test
    public void unsuccessfulLoginNoCredentials() {
        step("Logging in without password", () -> {
            Credentials body = new Credentials(
                    null,
                    null);
            RestAssured.given()
                    .spec(requestSpecification)
                    .body(body)
                    .when()
                    .post(LOGIN)
                    .then()
                    .statusCode(400)
                    .body("error", is("Missing email or username"));
        });
    }

    @Test
    public void createUser() {
        step("Creating a user", () -> {
            String name = "morpheus";
            String job = "leader";
            User body = new User(
                    name,
                    job);
            RestAssured.given()
                    .spec(requestSpecification)
                    .body(body)
                    .when()
                    .post(USERS)
                    .then()
                    .statusCode(201)
                    .body("name", is(name))
                    .body("job", is(job))
                    .body("$", hasKey("id"))
                    .body("$", hasKey("createdAt"));
        });
    }
}


