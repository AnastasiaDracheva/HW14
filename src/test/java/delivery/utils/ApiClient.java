package delivery.utils;

import com.google.gson.Gson;
import delivery.api.BaseSetupApi;
import delivery.dto.LoginDto;
import delivery.dto.OrderDtoMockedBuilderAndFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiClient extends BaseSetupApi {
    public static Response getOrders(RequestSpecification spec){

        return given()
                .spec(spec)
                .log()
                .all()
                .get( "orders")
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public static String authorizeAndGetToken(String username, String password){

        String token = given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body( new Gson().toJson( new LoginDto(username,password) ) )
                .post("login/student" )
                .then()
                .log()
                .all()
                .extract()
                .response()
                .asString();
        return token;
    }


        public static Response createOrder(RequestSpecification spec, OrderDtoMockedBuilderAndFactory orderDtoMocked){

            return given()
                    .spec(spec)
                    .log()
                    .all()
                    .body(orderDtoMocked)
                    .post( "/orders")
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response();
        }

}