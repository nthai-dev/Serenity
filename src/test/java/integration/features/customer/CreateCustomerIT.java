package integration.features.customer;

import com.edu.serenitydemo.dto.request.CustomerRequest;
import integration.BaseIntegrationTest;
import integration.step.common.Verifier;
import integration.step.post_request.CommonPostRequestStep;
import integration.step.request.CustomerRequestStep;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTagValuesOf;
import org.junit.jupiter.api.Test;
import randomizer.ObjectMother;

import static integration.step.common.Stepper.step;

public class CreateCustomerIT extends BaseIntegrationTest {
    @Test
    @WithTagValuesOf({
            "severity:high"
    })
    @Title("TC_C01 - [Success] Create customer successfully")
    void test__sample__deltaBiasPercentIsMissing__fail() {
        CustomerRequest customerRequest = step("Step 1: Prepare create customer request object",
                () -> ObjectMother.next(CustomerRequest.class));


        Long customerId = step("Step 2: Call API to create customer", () ->
                CustomerRequestStep.createCustomer(customerRequest,
                        Verifier::verifySuccessResponse,
                        CommonPostRequestStep::extractId));

        step("Step 3: Verify created customer successfully", () ->
                Verifier.verifyNotNull("id", customerId));
    }
}
