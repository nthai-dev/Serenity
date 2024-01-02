
package com.edu.serenitydemo.controller;

import com.edu.serenitydemo.dto.request.CustomerRequest;
import com.edu.serenitydemo.dto.response.CreateCustomerResponse;
import com.edu.serenitydemo.dto.response.CustomerResponse;
import com.edu.serenitydemo.factory.ResponseFactory;
import com.edu.serenitydemo.factory.ResponseWrapper;
import com.edu.serenitydemo.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ResponseFactory responseFactory;

    @PostMapping
    public ResponseEntity<ResponseWrapper<CreateCustomerResponse>> createCustomer(
            @RequestBody CustomerRequest customerRequest) {
        return responseFactory
                .success(customerService.createCustomer(customerRequest));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<CustomerResponse>>> getCustomerList() {
        return responseFactory.success(customerService.getCustomerList());
    }

    @GetMapping(value = "/{customer_id}")
    public ResponseEntity<ResponseWrapper<CustomerResponse>> getCustomerDetails(@PathVariable("customer_id") Long customerId) {
        return responseFactory
                .success(customerService.getCustomerDetails(customerId));
    }
}
