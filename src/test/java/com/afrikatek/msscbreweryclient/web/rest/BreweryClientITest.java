package com.afrikatek.msscbreweryclient.web.rest;

import com.afrikatek.msscbreweryclient.client.BreweryClient;
import com.afrikatek.msscbreweryclient.model.BeerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

/**
 * project mssc-brewery-client
 * created by murukai
 * created on 2023/02/21 at 04:27:03
 */
@SpringBootTest
public class BreweryClientITest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDTO beerDTO = breweryClient.getBeerById(UUID.randomUUID());
        Assertions.assertNotNull(beerDTO);
    }

    @Test
    void saveBeerTest() throws Exception{
        BeerDTO beerDTO = BeerDTO.builder().beerName("Example").build();
        URI uri = breweryClient.saveNewBeer(beerDTO);
    }

    @Test
    void updateBeerTest() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().beerName("Example beer").build();
        breweryClient.updateBeer(beerDTO, UUID.randomUUID());
    }

    @Test
    void deleteBeerTest() throws Exception {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

}
