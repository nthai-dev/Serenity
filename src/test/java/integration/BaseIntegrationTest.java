package integration;

import com.edu.serenitydemo.SerenityDemoApplication;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@ExtendWith(SerenityJUnit5Extension.class)
@SpringBootTest(
        classes = SerenityDemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Import(IntegrationTestConfiguration.class)
public abstract class BaseIntegrationTest {
}
