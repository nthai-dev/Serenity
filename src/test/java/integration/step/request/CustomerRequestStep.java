package integration.step.request;

import com.edu.serenitydemo.dto.request.CustomerRequest;
import integration.constants.Paths;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.function.Consumer;
import java.util.function.Function;

import static integration.step.common.Stepper.step;
import static net.serenitybdd.rest.SerenityRest.given;

public class CustomerRequestStep {

    public static <T> T createCustomer(CustomerRequest request,
                                       Consumer<Response> responseVerifier,
                                       Function<Response, T> responseExtractor) {
        Response response = createCustomer(request);
        responseVerifier.accept(response);
        return responseExtractor.apply(response);

    }

    public static Response createCustomer(CustomerRequest request) {
        return step("[request] Create customer profile",
                () -> given()
                        .body(request)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(Paths.URL_CUSTOMER_CREATE)
                        .thenReturn());
    }
}
