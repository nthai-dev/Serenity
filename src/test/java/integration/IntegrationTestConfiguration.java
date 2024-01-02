package integration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.restassured.config.ObjectMapperConfig;
import net.serenitybdd.rest.SerenityRest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.util.TestSocketUtils;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;

@TestConfiguration
@EnableAutoConfiguration
public class IntegrationTestConfiguration {
    private static final int serverPort;
    private static final ObjectMapper objectMapper;

    static {
        serverPort = TestSocketUtils.findAvailableTcpPort();
        System.setProperty("server.port", String.valueOf(serverPort));

        objectMapper = new ObjectMapper().findAndRegisterModules();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @PostConstruct
    public void setUp() {
        SerenityRest.setDefaultPort(serverPort);
        SerenityRest.setDefaultBasePath(contextPath);
        SerenityRest.setDefaultConfig(SerenityRest.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                (cls, charset) -> objectMapper
        )));
    }
}
