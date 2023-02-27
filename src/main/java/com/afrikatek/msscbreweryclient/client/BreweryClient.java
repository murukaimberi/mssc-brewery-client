package com.afrikatek.msscbreweryclient.client;

import com.afrikatek.msscbreweryclient.model.BeerDTO;
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
 * created on 2023/02/18 at 19:52:52
 */
@Component
@ConfigurationProperties(prefix = "afrikatek.brewery")
public class BreweryClient {
    public final String BEER_PATH_V1 = "/api/v1/beer";
    private String apiHost;

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public BeerDTO getBeerById(UUID uuid){
        return restTemplate.getForObject(apiHost + BEER_PATH_V1 + "/" + uuid.toString(), BeerDTO.class);
    }

    public URI saveNewBeer(BeerDTO beerDTO) throws URISyntaxException {
        URI uri = new URI(apiHost + BEER_PATH_V1);
        return restTemplate.postForLocation(uri, beerDTO);
    }

    public void updateBeer(BeerDTO beerDTO, UUID uuid) throws URISyntaxException{
        URI uri = new URI(apiHost + BEER_PATH_V1 + "/" + uuid.toString());
        restTemplate.put(uri, beerDTO);
    }

    public void deleteBeer(UUID randomUUID) throws URISyntaxException {
        URI uri = new URI(apiHost + BEER_PATH_V1 + "/" + randomUUID.toString());
        restTemplate.delete(uri);
    }
}
