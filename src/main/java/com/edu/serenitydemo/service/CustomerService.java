package com.edu.serenitydemo.service;

import com.edu.serenitydemo.constants.ResponseStatusEnum;
import com.edu.serenitydemo.dto.request.CustomerRequest;
import com.edu.serenitydemo.dto.response.CreateCustomerResponse;
import com.edu.serenitydemo.dto.response.CustomerResponse;
import com.edu.serenitydemo.exception.BadRequestException;
import com.edu.serenitydemo.mapper.CustomerEntityMapper;
import com.edu.serenitydemo.repository.CustomerRepository;
import com.edu.serenitydemo.repository.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Transactional
    public CreateCustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerEntityMapper.requestToDto(customerRequest);
        Long id = customerRepository.save(customer).getId();
        return CreateCustomerResponse.builder()
                .id(id)
                .build();
    }

    public List<CustomerResponse> getCustomerList() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerEntityMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public CustomerResponse getCustomerDetails(Long customerId) {
        Customer customers = customerRepository.findById(customerId).orElseThrow(() ->
                new BadRequestException(ResponseStatusEnum.CUSTOMER_NOT_FOUND, customerId)
        );
        return customerEntityMapper.entityToResponse(customers);
    }
}
