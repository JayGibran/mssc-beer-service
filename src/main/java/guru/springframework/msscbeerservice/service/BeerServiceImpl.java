package guru.springframework.msscbeerservice.service;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NoFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDTO;


@Service
public class BeerServiceImpl implements BeerService {
	
	private final BeerRepository beerRepository;
	
	private final BeerMapper beerMapper;
	
	@Autowired
	public BeerServiceImpl(BeerRepository beerRepository, BeerMapper beerMapper) {
		this.beerRepository = beerRepository;
		this.beerMapper = beerMapper;
	}
	
	@Override
	public BeerDTO getBeerById(UUID beerId) {
		return beerMapper.beerToBeerDTO(beerRepository.findById(beerId).orElseThrow(NoFoundException::new));
	}

	@Override
	public BeerDTO saveNewBeer(BeerDTO beerDTO) {
		return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBeer(beerDTO)));
	}

	@Override
	public BeerDTO updateBeer(UUID beerId, @Valid BeerDTO beerDTO) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NoFoundException::new);
		beer.setBeerName(beerDTO.getBeerName());
		beer.setBeerStyle(beerDTO.getBeerStyleEnum().name());
		beer.setUpc(beerDTO.getUpc());
		return beerMapper.beerToBeerDTO(beerRepository.save(beer));
	}

	

}
