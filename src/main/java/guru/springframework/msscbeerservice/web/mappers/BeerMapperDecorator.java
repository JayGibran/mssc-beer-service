package guru.springframework.msscbeerservice.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.service.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDTO;

public abstract class BeerMapperDecorator implements BeerMapper {

	private BeerInventoryService beerInventoryService;

	private BeerMapper beerMapper;

	@Autowired
	public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
		this.beerInventoryService = beerInventoryService;
	}

	@Autowired
	public void setMapper(BeerMapper mapper) {
		this.beerMapper = mapper;
	}

	@Override
	public BeerDTO beerToBeerDTO(Beer beer) {
		return beerMapper.beerToBeerDTO(beer);
	}

	@Override
	public Beer beerDTOToBeer(BeerDTO beer) {
		return beerMapper.beerDTOToBeer(beer);
	}

	@Override
	public BeerDTO beerToBeerDTOWithInventory(Beer beer) {
		BeerDTO dto = beerMapper.beerToBeerDTO(beer);
		dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
		return dto;
	}

}
