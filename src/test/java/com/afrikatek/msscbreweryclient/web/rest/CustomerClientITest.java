package com.afrikatek.msscbreweryclient.web.rest;

import com.afrikatek.msscbreweryclient.client.CustomerClient;
import com.afrikatek.msscbreweryclient.model.BeerDTO;
import com.afrikatek.msscbreweryclient.model.CustomerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

/**
 * project mssc-brewery-client
 * created by murukai
 * created on 2023/03/07 at 14:38:12
 */
@SpringBootTest
public class CustomerClientITest {

    @Autowired
    CustomerClient customerClient;
    @Test
    void getBeerById() {
        CustomerDTO customerDTO = customerClient.getCustomerById(UUID.randomUUID());
        Assertions.assertNotNull(customerDTO);
    }

    @Test
    void saveBeerTest() throws Exception{
        CustomerDTO customerDTO = CustomerDTO.builder().name("Example").build();
        URI uri = customerClient.saveNewCustomer(customerDTO);
    }

    @Test
    void updateBeerTest() throws Exception {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Example beer").build();
        customerClient.updateCustomer(customerDTO, UUID.randomUUID());
    }

    @Test
    void deleteBeerTest() throws Exception {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}
