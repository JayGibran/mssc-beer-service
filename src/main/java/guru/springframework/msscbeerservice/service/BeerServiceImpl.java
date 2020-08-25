package guru.springframework.msscbeerservice.service;

import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NoFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDTO;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;


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
	public BeerDTO getBeerById(UUID beerId, Boolean showInventoryOnHand) {
		if(showInventoryOnHand) {
			return beerMapper.beerToBeerDTOWithInventory(beerRepository.findById(beerId).orElseThrow(NoFoundException::new));
		}else{
			return beerMapper.beerToBeerDTO(beerRepository.findById(beerId).orElseThrow(NoFoundException::new));
		}
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

	@Override
	public BeerPagedList listBeer(String beerName, BeerStyleEnum beerStyle, Boolean showInventoryOnHand, PageRequest pageRequest) {
		BeerPagedList beerPagedList;
		Page<Beer> beerPage;
		
		if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		}else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		}else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		}else {
			beerPage = beerRepository.findAll(pageRequest);
		}
		
		
		if(showInventoryOnHand) {
			beerPagedList = new BeerPagedList(
					beerPage.getContent()
					.stream()
					.map(beerMapper::beerToBeerDTOWithInventory)
					.collect(Collectors.toList()), 
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()), 
					beerPage.getTotalElements()); 
		}else {
			beerPagedList = new BeerPagedList(
					beerPage.getContent()
					.stream()
					.map(beerMapper::beerToBeerDTO)
					.collect(Collectors.toList()), 
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()), 
					beerPage.getTotalElements()); 
		} 
		
		
		return beerPagedList;
	}

	

}
