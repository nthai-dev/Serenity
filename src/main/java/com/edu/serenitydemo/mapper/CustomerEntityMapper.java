package com.edu.serenitydemo.mapper;


import com.edu.serenitydemo.dto.request.CustomerRequest;
import com.edu.serenitydemo.dto.response.CustomerResponse;
import com.edu.serenitydemo.repository.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerRequest requestToEntity(Customer customer);

    Customer requestToDto(CustomerRequest customerRequest);

    CustomerResponse entityToResponse(Customer customer);
}
