package util;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.User;
import pojo.Todo;

import java.util.Arrays;
import java.util.List;

public class ApiUtils {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";

    public static List<User> fetchUsers() {
        Response response = RestAssured
            .given()
                .baseUri(BASE_URL)
            .when()
                .get("/users")
            .then()
                .statusCode(200)
                .extract()
                .response();

        return Arrays.asList(response.getBody().as(User[].class));
    }

    public static List<Todo> fetchUserTodos(int userId) {
        Response response = RestAssured
            .given()
                .baseUri(BASE_URL)
                .queryParam("userId", userId)
            .when()
                .get("/todos")
            .then()
                .statusCode(200)
                .extract()
                .response();

        return Arrays.asList(response.getBody().as(Todo[].class));
    }
}
