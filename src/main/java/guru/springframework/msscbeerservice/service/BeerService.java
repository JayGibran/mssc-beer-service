package guru.springframework.msscbeerservice.service;

import java.util.UUID;

import javax.validation.Valid;

import guru.springframework.msscbeerservice.web.model.BeerDTO;

public interface BeerService {

	BeerDTO getBeerById(UUID beerId);

	BeerDTO saveNewBeer(BeerDTO beerDTO);

	BeerDTO updateBeer(UUID beerId, @Valid BeerDTO beerDTO);

}
