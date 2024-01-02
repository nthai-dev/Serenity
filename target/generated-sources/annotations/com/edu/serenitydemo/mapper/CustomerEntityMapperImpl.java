package com.edu.serenitydemo.mapper;

import com.edu.serenitydemo.dto.request.CustomerRequest;
import com.edu.serenitydemo.dto.response.CustomerResponse;
import com.edu.serenitydemo.repository.entity.Customer;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-12T16:21:48+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_292 (AdoptOpenJDK)"
)
@Component
public class CustomerEntityMapperImpl implements CustomerEntityMapper {

    @Override
    public CustomerRequest requestToEntity(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerRequest.CustomerRequestBuilder customerRequest = CustomerRequest.builder();

        customerRequest.firstName( customer.getFirstName() );
        customerRequest.lastName( customer.getLastName() );
        customerRequest.dob( customer.getDob() );
        customerRequest.address( customer.getAddress() );
        customerRequest.city( customer.getCity() );
        customerRequest.identityType( customer.getIdentityType() );
        customerRequest.identityNumber( customer.getIdentityNumber() );

        return customerRequest.build();
    }

    @Override
    public Customer requestToDto(CustomerRequest customerRequest) {
        if ( customerRequest == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setFirstName( customerRequest.getFirstName() );
        customer.setLastName( customerRequest.getLastName() );
        customer.setDob( customerRequest.getDob() );
        customer.setAddress( customerRequest.getAddress() );
        customer.setCity( customerRequest.getCity() );
        customer.setIdentityType( customerRequest.getIdentityType() );
        customer.setIdentityNumber( customerRequest.getIdentityNumber() );

        return customer;
    }

    @Override
    public CustomerResponse entityToResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse.CustomerResponseBuilder customerResponse = CustomerResponse.builder();

        if ( customer.getId() != null ) {
            customerResponse.id( customer.getId() );
        }
        customerResponse.firstName( customer.getFirstName() );
        customerResponse.lastName( customer.getLastName() );
        customerResponse.dob( customer.getDob() );
        customerResponse.address( customer.getAddress() );
        customerResponse.city( customer.getCity() );
        customerResponse.identityType( customer.getIdentityType() );
        customerResponse.identityNumber( customer.getIdentityNumber() );
        customerResponse.createdTimestamp( customer.getCreatedTimestamp() );
        customerResponse.lastUpdatedTimestamp( customer.getLastUpdatedTimestamp() );

        return customerResponse.build();
    }
}
