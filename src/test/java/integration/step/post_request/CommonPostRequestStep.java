package integration.step.post_request;

import com.edu.serenitydemo.factory.ResponseWrapper;
import io.restassured.common.mapper.TypeRef;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static integration.step.common.Stepper.step;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonPostRequestStep {

    public static Long extractId(Response response) {
        return step("[extract] id", () -> extractJsonPath(response).getLong("data.id"));
    }

    public static <T> ResponseWrapper<T> extractGeneralResponse(Response response, TypeRef<ResponseWrapper<T>> typeRef) {
        return step("[extract] general response", () -> response.getBody().as(typeRef));
    }

    public static <T> List<T> extractGeneralResponseDataAsList(Response response, Class<T> elementClazz) {
        return step("[extract] general response data", () -> new ArrayList<>(extractJsonPath(response).getList("data", elementClazz)));
    }

    public static <T> T extractGeneralResponseData(Response response, Class<T> clazz) {
        return step("[extract] general response data", () -> extractJsonPath(response).getObject("data", clazz));
    }

    private static JsonPath extractJsonPath(Response response) {
        return response.getBody().jsonPath();
    }
}