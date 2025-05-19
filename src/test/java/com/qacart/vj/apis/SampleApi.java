package com.qacart.vj.apis;

import com.qacart.vj.model.User;
import com.qacart.vj.utils.ConfigUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SampleApi {

    public static void main(String[] args) {
        User user = new User();
        Response response =
        given()
            //.baseUri("https://todo.qacart.com")
            .baseUri(ConfigUtils.getInstance().getBaseUrl())
            .contentType(ContentType.JSON)
            //.body("{\"email\":\"john.smith.1825b@email.com\",\"password\":\"Simple1!\",\"firstName\":\"John\",\"lastName\":\"Smith\"}")
            .body(user)
        .when()
            .post("api/v1/users/register")
        .then()
            //.log().all();
            .extract().response();

        String access_token = response.path("access_token");
        String userID = response.path("userID");
        String firstName = response.path("Ronaldo");
        System.out.println("access_token: " + access_token);
        System.out.println("userID: " + userID);
        System.out.println("firstName: " + firstName);
    }
}