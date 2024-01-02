package integration.step.verifier.customer;

import com.edu.serenitydemo.dto.request.CustomerRequest;
import com.edu.serenitydemo.dto.response.CustomerResponse;
import integration.step.common.Verifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static integration.step.common.Verifier.verifyEquals;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerDetailResponseVerifier {
    public static void verifyCreatedCustomer(CustomerRequest request,
                                             CustomerResponse response) {
        verifyEquals("partner_code", request.getFirstName(), response.getFirstName());
        verifyEquals("country_code", request.getLastName(), response.getLastName());
        verifyEquals("transaction_type", request.getDob(), response.getDob());
        verifyEquals("service_id", request.getCity(), response.getCity());
        verifyEquals("payee_user_id", request.getAddress(), response.getAddress());
        verifyEquals("payee_user_type", request.getIdentityNumber(), response.getIdentityNumber());
        verifyEquals("is_active", request.getIdentityType(), response.getIdentityType());
    }
}
