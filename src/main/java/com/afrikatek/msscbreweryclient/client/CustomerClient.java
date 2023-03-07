package com.afrikatek.msscbreweryclient.client;

import com.afrikatek.msscbreweryclient.model.BeerDTO;
import com.afrikatek.msscbreweryclient.model.CustomerDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * project mssc-brewery-client
 * created by murukai
 * created on 2023/03/07 at 14:25:20
 */
@Component
@ConfigurationProperties(prefix = "afrikatek.brewery")
public class CustomerClient {

    public final String CUSTOMER_PATH_V1 = "/api/v1/customer";

    private String apiHost;

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public CustomerDTO getCustomerById(UUID uuid){
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + "/" + uuid.toString(), CustomerDTO.class);
    }

    public URI saveNewCustomer(CustomerDTO customerDTO) throws URISyntaxException {
        URI uri = new URI(apiHost + CUSTOMER_PATH_V1);
        return restTemplate.postForLocation(uri, customerDTO);
    }

    public void updateCustomer(CustomerDTO customerDTO, UUID uuid) throws URISyntaxException{
        URI uri = new URI(apiHost + CUSTOMER_PATH_V1 + "/" + uuid.toString());
        restTemplate.put(uri, customerDTO);
    }

    public void deleteCustomer(UUID randomUUID) throws URISyntaxException {
        URI uri = new URI(apiHost + CUSTOMER_PATH_V1 + "/" + randomUUID.toString());
        restTemplate.delete(uri);
    }
}
