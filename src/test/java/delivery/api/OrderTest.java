package delivery.api;

import delivery.dto.OrderDtoMockedBuilderAndFactory;
import delivery.utils.ApiClient;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderTest extends BaseSetupApi {

    @Test
    void getOrderInformationAndCheckResponse() {

        Response response = ApiClient.getOrders(getAuthenticatedRequestSpecification() );

        Assertions.assertAll("Test description",
                () -> Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Status code is OK")
        );

    }

    @Test
    void createOrderAndCheckResponse() {
        OrderDtoMockedBuilderAndFactory orderDtoMockedBuilderAndFactory = OrderDtoMockedBuilderAndFactory.createRandomOrder();
        Response response = ApiClient.createOrder(getAuthenticatedRequestSpecification(), orderDtoMockedBuilderAndFactory);

        Assertions.assertAll("Test description",
                () -> Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Status code is OK"),
                () -> Assertions.assertNotNull(response.getBody().path("id")),
                () -> Assertions.assertNull(response.getBody().path("api-key")),
                () -> Assertions.assertNotNull(response.getStatusLine(), "status")
        );

    }
}
