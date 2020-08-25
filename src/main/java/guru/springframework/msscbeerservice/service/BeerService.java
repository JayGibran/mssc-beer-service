package guru.springframework.msscbeerservice.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;

import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;

public interface BeerService {

	BeerDTO getBeerById(UUID beerId, Boolean showInventoryOnHand);

	BeerDTO saveNewBeer(BeerDTO beerDTO);

	BeerDTO updateBeer(UUID beerId, @Valid BeerDTO beerDTO);

	BeerPagedList listBeer(String beerName, BeerStyleEnum beerStyle, Boolean showInventoryOnHand, PageRequest of);

}
