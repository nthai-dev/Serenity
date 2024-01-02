package integration.step.common;

import com.edu.serenitydemo.constants.ResponseStatusEnum;
import com.edu.serenitydemo.factory.ResponseWrapper;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.function.Consumer;

import static integration.step.common.Stepper.step;
import static org.apache.commons.collections.CollectionUtils.isEqualCollection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.OK;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Verifier {

    public static final Consumer<Response> NOOP_VERIFIER = response -> {
    };

    public static void verifySuccessResponse(Response response) {
        verifyHttpStatusCodeEquals(response, OK);
        verifyResponseStatusEquals(response, ResponseStatusEnum.SUCCESS);
    }

    public static void verifyHttpStatusCodeEquals(Response response, HttpStatus code) {
        step(String.format("[verify] Http status code equals [%s]", code),
                () -> assertEquals(code.value(), response.statusCode()));
    }

    public static void verifyResponseStatusEquals(Response response,
                                                  ResponseStatusEnum responseStatus,
                                                  Object... statusMessageArgs) {
        verifyStatusCodeEquals(response, responseStatus.code());
        if (ArrayUtils.isNotEmpty(statusMessageArgs)) {
            verifyStatusMessageEquals(response, String.format(responseStatus.message(), statusMessageArgs));
        } else {
            verifyStatusMessageEquals(response, responseStatus.message());
        }
    }

    public static void verifyStatusCodeEquals(Response response, String code) {
        step(String.format("[verify] Status code equals [%s]", code),
                () -> assertEquals(code, response.body().as(ResponseWrapper.class).getStatusCode()));
    }

    public static void verifyStatusMessageEquals(Response response, String message) {
        step(String.format("[verify] Status message equals [%s]", message),
                () -> assertEquals(message, response.body().as(ResponseWrapper.class).getMessage()));
    }

    public static void verifyHttpStatusCodeAndResponseStatusEquals(Response response,
                                                                   HttpStatus code,
                                                                   ResponseStatusEnum responseStatus,
                                                                   Object... statusMessageArgs) {
        verifyHttpStatusCodeEquals(response, code);
        verifyResponseStatusEquals(response, responseStatus, statusMessageArgs);
    }

    public static <T> void verifyEquals(String key, T expected, T actual) {
        step(String.format("[verify] %s equals [%s]", key, expected),
                () -> assertEquals(expected, actual));
    }

    public static <T> void verifyNotEquals(String key, T expected, T actual) {
        step(String.format("[verify] %s not equals [%s]", key, expected),
                () -> assertNotEquals(expected, actual));
    }

    public static <T extends Comparable<T>> void verifyLargerThan(String key, T expected, T actual) {
        step(String.format("[verify] %s large than [%s]", key, expected),
                () -> assertTrue(actual.compareTo(expected) > 0));
    }

    public static void verifyNull(String key, Object actual) {
        step(String.format("[verify] %s is Null", key),
                () -> assertNull(actual));
    }

    public static void verifyNotNull(String key, Object actual) {
        step(String.format("[verify] %s is not Null", key),
                () -> assertNotNull(actual));
    }

    public static void verifyStartWith(String key, String prefix, String actual) {
        step(String.format("[verify] %s starts with [%s]", key, prefix),
                () -> assertTrue(actual.startsWith(prefix)));
    }

    public static <T> void verifyCollectionEquals(String key, Collection<T> expected, Collection<T> actual) {
        step(String.format("[verify] %s equals [%s]", key, expected),
                () -> assertTrue(isEqualCollection(expected, actual)));
    }

}
